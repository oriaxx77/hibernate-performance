/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.reportquery;

/**
 * A msg class used in report queries.
 * 
 */
public class Msg
{
    /** Id */
    private long id;
    /** Msg text */
    private String msg;
    /**
     * Constructor
     * @param id Id
     * @param msg msg text
     */
    public Msg( long id, String msg )
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

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @return the msg
     */
    public String getMsg()
    {
        return msg;
    }
    
    
}
