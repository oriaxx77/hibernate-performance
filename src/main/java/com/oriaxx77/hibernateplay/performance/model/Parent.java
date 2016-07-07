/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/**
 * 
 *
 */
@Entity
public class Parent implements Serializable
{
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /** Id */
    @Id
    @GeneratedValue
    private Long id;
    
    /** Children */
    // Ordered list -> it needs to maintain the order column :(
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
    @OrderColumn // Remove it and it does not maintain order column.
    private List<Child> children = new ArrayList<Child>();
    
    
        
    /** 
     * Returns with the children
     * @return
     */
    public List<Child> getChildren() 
    {
        return children;
    }
     
    /**
     * Adds the specified child to the children collections.
     * @param child Child to add.
     */
    public void addChild(Child child) 
    {
        children.add( child) ;
        child.setParent( this );
    }
    
    /**
     * Removes the child from the child collection.
     * @param child
     */
    public void removeChild(Child child) 
    {
        children.remove(child);
        child.setParent(null);
    }

   
    
    
}
