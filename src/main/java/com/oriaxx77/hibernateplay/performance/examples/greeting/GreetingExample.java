/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.greeting;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.oriaxx77.hibernateplay.performance.model.Greeting;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;

/**
 * Sample service to check if the project works. 
 * No interface for this service. This is just a demo so it is fine, is it? :)
 * Focus on Hibernate.
 * Use the {{@link #greet(String)} method and check the STDOUT.
 * You should see someting like that: <i>Saved greeting: Greeting [id=1, message=Hello M.J.!, language=HU]</i>
 */
@Service
public class GreetingExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger(GreetingExample.class);
    
    
    
    
    
    /**
     * Greet a person passed via the name parameter.
     * @param name name of the person to greet.
     */
    @Transactional
    public void greet( String name )
    {
        Greeting greeting = new Greeting( "Hello " + name + "!", "HU" );
        getSession().save( greeting );
        logger.info( "Saved greeting: " + greeting );
        logger.info( "Transaction actrive : " + TransactionSynchronizationManager.isActualTransactionActive() );
    }
}
