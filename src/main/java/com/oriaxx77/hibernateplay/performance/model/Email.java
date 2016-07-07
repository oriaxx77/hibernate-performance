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
 * Emails of the {@link Person}
 * 
 *
 */
@Entity
public class Email implements Serializable
{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/** Id */
	@Id
	@GeneratedValue
	private Long id;
	
	/** Owner */
	@ManyToOne
	private Person person;
	
	/** Content */
	private String email;
	
	/** Constructor */
	public Email()
	{
		
	}
	
	/**
	 * Constructor with email content
	 * @param email content of the email
	 */
	public Email( String email)
	{
		this.email = email;
	}

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
	 * @return the person
	 */
	public Person getPerson()
	{
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person)
	{
		this.person = person;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	
	

}
