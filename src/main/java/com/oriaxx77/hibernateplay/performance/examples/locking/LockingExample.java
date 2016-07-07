/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.locking;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oriaxx77.hibernateplay.performance.model.Toy;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;

/**
 * Locking examples. Use this from your browser through the {@link LockingResource}.
 * <ul>
 * <li>Create nemo: http://127.0.0.1:8080/locking/createNemo</li>
 * <li>Load nemo: http://127.0.0.1:8080/locking/loadNemo</li>
 * <li>Optimistic locking: http://127.0.0.1:8080/locking/optimisticLocking </li>
 * </ul>
 * Check the logs to see what happens.
 * 
 */
@Service
public class LockingExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( LockingExample.class );
    
    
    /**
     * Updates the {@link Toy} nemo. It loads with optimistic locking no wait.
     * @param waitTimeout time before updating nemo in millis
     * @param committimeout time before "commiting" nemo in millis
     * @throws InterruptedException 
     */
    @Transactional
    public void optimisticLocking( int waitTimeout, int commitTimeout ) throws InterruptedException
    {
        logger.info( Thread.currentThread().getName() + " querying for nemo" );
        Toy nemo = (Toy)getSession().createCriteria( Toy.class ).add( Restrictions.eq( "name" , "Nemo" ) )
                        .setLockMode( LockMode.OPTIMISTIC ).setTimeout( Session.LockRequest.PESSIMISTIC_NO_WAIT ).list().get(0);
        logger.info( Thread.currentThread().getName() + " updating nemo: " + nemo );
        Thread.sleep( waitTimeout );
        nemo.setStockNo( nemo.getStockNo() + 1 );
        getSession().update( nemo );
        logger.info( Thread.currentThread().getName() + " commiting nemo: " + nemo );
        Thread.sleep( commitTimeout );
        logger.info( Thread.currentThread().getName() + " nemo commited: " + nemo );
    }
    
    /**
     * Ceates the {@link Toy} nemo with stockNo 5 and name Nemo.
     */
    @Transactional
    public void createNemo()
    {
        Toy nemo = new Toy();
        nemo.setName( "Nemo" );
        nemo.setStockNo( 5 );
        getSession().save( nemo );
        logger.info( Thread.currentThread().getName() + " nemo created: " + nemo );
    }
    
    
    /**
     * Loads the {@link Toy} named Nemo
     * @throws InterruptedException 
     */
    @Transactional
    public void loadNemo() 
    {
        Toy nemo = (Toy)getSession().createCriteria( Toy.class ).add( Restrictions.eq( "name" , "Nemo" ) ).list().get(0);
        logger.info( Thread.currentThread().getName() + " nemo in db: " + nemo );
    }
}
