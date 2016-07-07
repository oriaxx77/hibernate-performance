/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.nativequery;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.springframework.stereotype.Service;

import com.oriaxx77.hibernateplay.performance.model.Message;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;
import com.oriaxx77.hibernateplay.performance.util.LogInfo;

/**
 * Contains native query examples.
 * 
 *
 */
@Service
public class NativeQueryExample extends HibernateExampleBase
{
    /** Logger */
    private final static Logger logger = Logger.getLogger(NativeQueryExample.class);
    
    /**
     * Runs some native queries.
     */
    @Transactional
    public NativeQueryExample runNativeQuery()
    {
       // NOTE: in real life only the Msg save should be transactioned.
       // NOTE: if you move it to it's own method and annotate it with @Transactional, the method should be public
       //       because of the Srping AOP.
       IntStream.range(0, 5).forEach( i -> {
          getSession().save( new Message( "Msg" + i , "EN" ) );
       });
        
       // NOTE: In most of the cases this should not be transactioned.
       List<?> rawMsgs = getSession()
                         .createSQLQuery( "SELECT id, message FROM Message WHERE language=:language " )
                         .setString( "language" , "EN" )
                         .list();

       rawMsgs.stream().forEach( record -> 
       // logger.info( Arrays.toString( (Object[])record ) ) );
       logger.info( "---- " + ((Object[])record)[0].getClass()+ " ---- " + ((Object[])record)[1].getClass() ) );
       LogInfo.printQueryCacheStaistics( logger, getSessionFactory() );
       return this;
    }
    
    /**
     * Runs some native queries with bean transformers.
     */
    @Transactional
    public NativeQueryExample runNativeQueryWithTransformer() throws NoSuchMethodException
    {
       // NOTE: in real life only the Msg save should be transactioned.
       // NOTE: if you move it to it's own method and annotate it with @Transactional, the method should be public
       //       because of the Srping AOP.
       IntStream.range(0, 5).forEach( i -> {
          getSession().save( new Message( "Msg" + i , "EN" ) );
       });
       
       
        
       // NOTE: In most of the cases this should not be transactioned.
       List<?> rawMsgs = getSession()
                         .createSQLQuery( "SELECT id as id, message as msg FROM Message WHERE language=:language " )
                         .setString( "language" , "EN" )
                         .setResultTransformer( new AliasToBeanConstructorResultTransformer( Msg.class.getConstructor( BigInteger.class, String.class)) )
                         .list();

       rawMsgs.stream().forEach( record -> logger.info( record ) );
       LogInfo.printQueryCacheStaistics( logger, getSessionFactory() );
       return this;
    }
 
    
}
