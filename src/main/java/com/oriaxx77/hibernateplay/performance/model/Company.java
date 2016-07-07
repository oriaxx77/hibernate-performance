/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * A simple company entity.
 * 
 */
@Entity 
public class Company implements Serializable  
											  
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
     * Name of the entity
     */
    private String name;
	
    
    /**
     * Employees of the entity
     */
    @OneToMany( cascade={CascadeType.ALL},
                mappedBy="company",
                fetch=FetchType.LAZY)
    private Set<Employee> employees = new HashSet<Employee>();
    
    
	/**
	 * Creates an empty Company.
	 */
	public Company()
	{
		super();
	}
	
	
	
	/**
	 * Creates a company with the given name.
	 * @param name Name of the company
	 */
    public Company(String name)
    {
        super();
        this.name = name;
    }




    /**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

    	
	/**
	 * Adds an employee to this company
	 * @param empoyee Employee to add.
	 */
	public void addEmployee(Employee employee )
	{
	    employee.setCompany( this );
	    this.employees.add( employee );
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

