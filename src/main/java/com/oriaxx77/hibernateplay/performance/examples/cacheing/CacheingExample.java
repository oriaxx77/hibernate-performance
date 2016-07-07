package com.oriaxx77.hibernateplay.performance.examples.cacheing;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oriaxx77.hibernateplay.performance.model.Greeting;
import com.oriaxx77.hibernateplay.performance.model.Message;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;
import com.oriaxx77.hibernateplay.performance.util.LogInfo;






/**
 * Example for L1, L2, Query caching. 
 */
@Service
@Transactional
public class CacheingExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger(CacheingExample.class);
    
    /**
     * Example for L1 caching.
     * @return return with this object for using it as a fluent API
     */
    @Transactional
	public CacheingExample l1Caching()
	{
		final Greeting greeting = new Greeting( "Hello world", "en" );
		getSession().persist( greeting );
		
		Greeting greetingAgain1 = (Greeting)getSession().load( Greeting.class, greeting.getId() );
		Greeting greetingAgain2 = (Greeting)getSession().load( Greeting.class, greeting.getId() );
		
		assert( greetingAgain1 == greetingAgain2 );
		assert( greetingAgain1 != greeting );	
		return this;
	}
	
	/**
	 * L2 Caching example. Creates a message and returns it's id.
	 * Notes:
	 * - @Cacheable(value=true) on the {@link Message}
	 * - hibernate.cache.region.factory_class
	 * - hibernate.cache.region.factory_class = org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory
     * - hibernate.cache.use_second_level_cache = true 
     * 
     * @return return with this object for using it as a fluent API
	 */
	public Long createMessage()
	{
	    Message msg = new Message( "Hello world", "en" );
        getSession().save( msg );
        logger.info( "----------------Current session cache mode: " + getSession().getCacheMode() );
        logger.info( "----------------L2 cache put after message save: " + getSessionFactory().getStatistics().getSecondLevelCachePutCount() );
        return msg.getId();
	}
	
	/**
     * Loads a message by it's id.
     * Notes:
     * - @Cacheable(value=true) on the {@link Message}
     * - hibernate.cache.region.factory_class
     * - hibernate.cache.region.factory_class = org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory
     * - hibernate.cache.use_second_level_cache = true 
     * 
     * @return return with this object for using it as a fluent API
     */
	public void loadMessage( Long messageId )
	{
	    logger.info( "----------------L2 cache hit before message load: " + getSessionFactory().getStatistics().getSecondLevelCacheHitCount() );
        Message msg = (Message)getSession().get( Message.class, messageId );
        logger.info( "----------------Message loaded: " + msg );
        logger.info( "----------------Current session cache mode: " + getSession().getCacheMode() );
        logger.info( "----------------L2 cache hit after message load: " + getSessionFactory().getStatistics().getSecondLevelCacheHitCount() );
	}
	
	/**
	 * Query cache example
	 * Note: 
	 * - hibernate.cache.use_query_cache = true
	 * 
	 * @return return with this object for using it as a fluent API
	 */
	public CacheingExample queryCacheing()
	{
	    IntStream.range(0, 5).forEach( i -> getSession().save(new Message( "msg"+ i, "EN")));
	    IntStream.range(0, 1).forEach( i -> queryForMessages() );
	    return this;
	}
	
	/**
	 * Query for EN Message objects 
	 * 
	 * @return return with this object for using it as a fluent API
	 */
	private CacheingExample queryForMessages()
	{
	    @SuppressWarnings("unchecked")
        List<Message> msgs = getSession().createQuery( "FROM Message m WHERE m.language = :language" )
                                .setParameter( "language", "EN" ).setCacheable( true ).setCacheRegion("msgQueries").list();
        
        logger.info( "Messages: " + msgs );
        LogInfo.printQueryCacheStaistics( logger, getSessionFactory() );
        return this;
	}
}
