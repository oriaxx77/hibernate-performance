/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.locking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oriaxx77.hibernateplay.performance.model.Toy;

/**
 * Locking examples. Use this from your browser through the {@link LockingResource}.
 * <ul>
 * <li>Create nemo: http://127.0.0.1:8080/locking/createNemo</li>
 * <li>Load nemo: http://127.0.0.1:8080/locking/loadNemo</li>
 * <li>Optimistic locking: http://127.0.0.1:8080/locking/optimisticLocking </li>
 * </ul>
 * Check the logs to see what happens.
 * NOTE: it does not follow REST rules tightly
 * 
 */
@RestController
@RequestMapping( "/locking" )
public class LockingResource
{
    /**
     * Locking examples
     */
    private LockingExample lockingExample;
    
    
    /**
     * Constructor.
     * @param lockingService Locking examples
     */
    @Autowired
    public LockingResource(LockingExample lockingExample)
    {
        super();
        this.lockingExample = lockingExample;
    }



    /**
     * Ceates the {@link Toy} nemo with stockNo 5 and name Nemo.
     */
    @RequestMapping( method=RequestMethod.GET, value="/createNemo" )
    public void createNemo()
    {
        lockingExample.createNemo();
    }
    
    /**
     * Updates the {@link Toy} nemo. It loads with optimistic locking no wait.
     * Waits 10 sec before updating and 10 sec before commiting the change.
     * @throws InterruptedException 
     */
    @RequestMapping( method=RequestMethod.GET, value="/optimisticLocking" )
    public void optimisticLocking() throws InterruptedException
    {
        lockingExample.optimisticLocking( 10000, 10000 );
    }
    
    /**
     * Loads the {@link Toy} named Nemo
     * @throws InterruptedException 
     */
    @RequestMapping( method=RequestMethod.GET, value="/loadNemo" )
    public void loadNemo() throws InterruptedException
    {
        lockingExample.loadNemo();
    }
}
