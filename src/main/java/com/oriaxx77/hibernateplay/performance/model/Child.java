/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



/**
 * 
 *
 */
@Entity
public class Child implements Serializable
{
    
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /** Id */
    @Id
    @GeneratedValue
    private Long id;
    
    /** Name of the child */
    private String name;
    
     
    
    /**
     * 
     */
    @ManyToOne
    private Parent parent;

    /**
     * Constructor
     */
    public Child()
    {}
    
    /**
     * Constructor
     * @param name Name of this child
     */
    public Child( String name )
    {
        this.name = name;
    }
    
    /**
     * @return the parent
     */
    public Parent getParent()
    {
        return parent;
    }


    /**
     * @param parent the parent to set
     */
    public void setParent(Parent parent)
    {
        this.parent = parent;
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


   
    
    
     
}
