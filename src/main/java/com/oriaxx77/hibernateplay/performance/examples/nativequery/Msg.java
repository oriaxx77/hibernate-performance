/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.nativequery;

import java.math.BigInteger;

/**
 * A msg class used in native queries.
 * 
 */
public class Msg
{
    /** Id */
    private BigInteger id;
    /** Msg text */
    private String msg;
    
    
    /**
     * Constructor
     * @param id Id
     * @param msg msg text
     */
    public Msg( BigInteger id, String msg )
    {
        this.id = id;
        this.msg = msg; 
    }
    
    /**
     * Returns with the string representation of this object.
     * @return the string representation of this object
     */
    @Override
    public String toString()
    {
        return "Msg [id=" + id + ", msg=" + msg + "]";
    }

        
    
    
    
}
