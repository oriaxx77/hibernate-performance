/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * Employee of a company.
 * 
 */
@Entity 
public class Employee implements Serializable  
                                              
{
    /**
     * Serial version id for Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * Primary key.
     */

    @GenericGenerator(
        name = "sequenceGenerator",
        strategy = "enhanced-sequence",
        parameters = {
        @org.hibernate.annotations.Parameter(
                name = "optimizer",
                value = "pooled-lo"),
        @org.hibernate.annotations.Parameter(
                name = "initial_value",
                value = "1"),
        @org.hibernate.annotations.Parameter(
                name = "increment_size",
                value = "50"
        )
        }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator")

    @Id
    private Long id;
    
    /**
     * Company of the employee.
     */
    @ManyToOne
    private Company company;
    
    
    /**
     * Creates an empty Company.
     */
    public Employee()
    {
        super();
    }
    

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }


    /**
     * @param company the company to set
     */
    public void setCompany(Company company)
    {
        this.company = company;
    }


    /**
     * @return the company
     */
    public Company getCompany()
    {
        return company;
    }

        
   
    
    
}

