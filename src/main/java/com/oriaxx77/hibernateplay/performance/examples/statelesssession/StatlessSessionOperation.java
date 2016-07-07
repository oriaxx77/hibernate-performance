/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.statelesssession;

import org.hibernate.StatelessSession;

/**
 * Template for performing operations with {@link StatelessSession}
 * 
 */
public interface StatlessSessionOperation
{
    /**
     * Performs an operation with the provided session.
     * @param session StatelessSession
     * @return Result of the operation, if any.
     */
    Object perform( StatelessSession session );
}
