/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import org.apache.log4j.Logger;

/**
 * User defined type.
 * A segment of a customer
 * 
 */

public class Segment 
{
    /** Logger */
    final static Logger logger = Logger.getLogger( Segment.class );
    
    /**
     * Name of the segment
     */
    private String name;
    /**
     * Id of the segment
     */
    private Integer id;
    
    /** VIP customers */
    public static Segment VIP = new Segment( 0, "VIP" ){
        public void someMeaningfulBusinessOperation() { logger.info( "Doing something in Segment#someMeaningfulBusinessOperation() in segment: " + getName() ); };
        };
    /** Regular customers */
    public static Segment REGULAR = new Segment( 2, "REGULAR" ){
        public void someMeaningfulBusinessOperation() { logger.info( "Doing something in Segment#someMeaningfulBusinessOperation() in segment: " + getName() ); };
        };
    
   
        
    /**
     * Creates a segment with the given name and id.
     * @param id Id of the segment.
     * @param name Name of the segment.
     */
    private Segment(Integer id, String name)
    {
        super();
        this.name = name;
        this.id = id;
    }    
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    

    /**
     * Some placeholder business method
     */
    public void someMeaningfulBusinessOperation()
    {
        logger.info( "Doing something in Segment#someMeaningfulBusinessOperation()" );
    }

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Segment [name=" + name + ", id=" + id + "]";
    }

        
    
}
