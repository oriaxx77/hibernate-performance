/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.oriaxx77.hibernateplay.performance.examples.locking.LockingExample;

/**
 * Toy entity used in the {@link LockingExample}
 * 
 */
@Entity
public class Toy implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer stockNo;
    @Version
    private Long version = 0L;
    private String name;
    
    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    /**
     * @return the stockNo
     */
    public Integer getStockNo()
    {
        return stockNo;
    }
    /**
     * @param stockNo the stockNo to set
     */
    public void setStockNo(Integer stockNo)
    {
        this.stockNo = stockNo;
    }
    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }
    /**
     * @param version the version to set
     */
    public void setVersion(Long version)
    {
        this.version = version;
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
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Toy [id=" + id + ", stockNo=" + stockNo + ", version="
                + version + ", name=" + name + "]";
    }
    
    
    
    
    
}
