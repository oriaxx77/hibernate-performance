/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 
 */
@Entity 
public class Greeting implements Serializable  
											  
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
//	@GenericGenerator(name = "increment", strategy = "increment")
//	@GeneratedValue(generator = "increment")
	private Long id;
	
	
	/**
	 * The greeting message.
	 */
	@Basic 
	private String message;
	
	/**
	 * Language of the greeting message.
	 */
	@Basic
	private String language;
	
	/**
	 * Creates an empty Greeting.
	 */
	public Greeting()
	{
		super();
	}

	/**
	 * Creates a Greeting with the specified attributes.
	 * @param message Message.
	 * @param language Language of the message.
	 */
	public Greeting(String message, String language)
	{
		super();
		this.message = message;
		this.language = language;
	}

	

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Greeting [id=" + id + ", message=" + message + ", language="
                + language + "]";
    }

    /**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
	
	

	
}

