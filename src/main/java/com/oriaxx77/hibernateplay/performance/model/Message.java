/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity 
@Cacheable(value=true) // Note: second level cached
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Message implements Serializable  
											  
{
	/**
	 * Serial version id for Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;

	/**
	 * The hello message.
	 */
	@Basic 
	private String message;
	
	/**
	 * Language of the hello message.
	 */
	@Basic
	private String language;
	
	/**
	 * Creates an empty Hello.
	 */
	public Message()
	{
		super();
	}

	/**
	 * Creates a Hello with the specified attributes.
	 * @param message Message.
	 * @param language Language of the message.
	 */
	public Message(String message, String language)
	{
		super();
		this.message = message;
		this.language = language;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString()
    {
        return "Message [id=" + id + ", message=" + message + ", language="
                + language + "]";
    }

    /**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}
	
	

	
}

