/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.statelesssession;


import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.hibernate.StatelessSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oriaxx77.hibernateplay.performance.examples.statelesssession.StatlessSessionOperation;
import com.oriaxx77.hibernateplay.performance.model.Child;
import com.oriaxx77.hibernateplay.performance.model.Parent;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;






/**
 * Example for demonstrating the stateless session. 
 */
@Service
@Transactional
public class StatelessSessionExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( StatelessSessionExample.class);
    
    /**
     * Creates some parents and children with {@link StatelessSession}
     * @return
     */
    public StatelessSessionExample createParentWithChildren()
    {
        logger.info( "Creating parents with stateless session" );
        doStatelessSessionOperation( session -> { 
            IntStream.range(0, 10 ).forEach( i -> {
              Parent parent = new Parent();
              IntStream.range(0, 3).forEach( j -> parent.addChild( new Child( i + "" ) ));
              session.insert( parent );
            });
            return null; });
        return this;
    }
    
    
   
    /**
     * Performs an operation with a statless session.
     * @param operation operation.
     */
    private void doStatelessSessionOperation( StatlessSessionOperation operation )
    {
        StatelessSession session = getSessionFactory().openStatelessSession();
        try
        {
            operation.perform( session );
        }
        finally
        {
            session.close();
        }
    }
    
    
    
}
