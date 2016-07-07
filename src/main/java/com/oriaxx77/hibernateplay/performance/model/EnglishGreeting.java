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



/**
 * English greeting. It only contains a message.
 * Used in the {@link BatchProcessingExample}.
 * 
 */
@Entity 
public class EnglishGreeting implements Serializable  
{
	/**
	 * Serial version id for Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;

	/**
	 * The greeting message.
	 */
	@Basic 
	private String message;
	
	
	
	/**
	 * Creates an empty OtherGreeting.
	 */
	public EnglishGreeting()
	{
		super();
	}

	/**
	 * Creates a OtherGreeting with the specified attributes.
	 * @param message Message.
	 * 
	 */
	public EnglishGreeting(String message)
	{
		super();
		this.message = message;
	}

	

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "OtherGreeting [id=" + id + ", message=" + message +  "]";
    }

    /**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}
	
	

	
}

