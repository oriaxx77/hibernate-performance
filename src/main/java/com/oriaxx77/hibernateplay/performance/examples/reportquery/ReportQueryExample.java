/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.reportquery;
/**
 * 
 */


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.oriaxx77.hibernateplay.performance.model.Message;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;
import com.oriaxx77.hibernateplay.performance.util.LogInfo;

/**
 * Report query examples. See {@link #runReportQueries()}.
 * 
 */
@Service
public class ReportQueryExample extends HibernateExampleBase
{
    /** logger */
    final static Logger logger = Logger.getLogger(ReportQueryExample.class);
    
    
    /**
     * Runs some report queries.
     */
    @Transactional
    public void runReportQueries()
    {
       createMsgs();
       queryForRawMsgs();
       queryForMsgs(  );
    }
    
    /** Creates some message to query */
    @Transactional // It must be public because of the Spring AOP.
    public void createMsgs()
    {
        IntStream.range(0, 5).forEach( i -> {
            getSession().save( new Message( "Msg" + i , "EN" ) );
         });
    }
    
    
    /**
     * Query message records. The records are Object[] objects.
     */
    private void queryForRawMsgs()
    {
        List<?> rawMsgs = getSession()
                .createQuery( " SELECT id, message FROM Message WHERE language=:language "  )
                .setString( "language" , "EN" )
                .list();

        rawMsgs.stream().forEach( record -> 
        logger.info( Arrays.toString( (Object[])record ) ) );
        LogInfo.printQueryCacheStaistics( logger, getSessionFactory() );
        
    }
    
    /**
     * Query message records.
     * The records are {@link Msg} objects
     */
    private void queryForMsgs()
    {
        List<?> rawMsgs = getSession()
                .createQuery( " SELECT new com.oriaxx77.hibernateplay.performance.examples.reportquery.Msg(id, message) FROM Message WHERE language=:language "  )
                .setString( "language" , "EN" )
                .list();

        rawMsgs.stream().forEach( msg -> logger.info( msg ) );
        LogInfo.printQueryCacheStaistics( logger, getSessionFactory() );
    }
    
}
