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
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Person entity.
 * Take a look at the {@link #emails}'s mapping
 * 
 * 
 *
 */
@Entity
public class Person implements Serializable
{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Primary key
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
     * Segment of the user.
     * Note: user defined type
     */
    private Segment segment;
	
// 1. SELECT
//	@Fetch(FetchMode.SELECT)
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="person",fetch=FetchType.LAZY)

	
// 2. BATCH SELECT
  @Fetch(FetchMode.SELECT)
  @OneToMany(cascade=CascadeType.ALL,mappedBy="person",fetch=FetchType.LAZY)
  @BatchSize(size=3)

	
// 3. SUBSELECT
//    @Fetch(FetchMode.SELECT)
//    @OneToMany(cascade=CascadeType.ALL,mappedBy="person",fetch=FetchType.LAZY)
//    @BatchSize(size=3)
//
	private Set<Email> emails = new HashSet<Email>();
	
    /**
     * Name of the person.
     */
	private String name;
	
	/**
	 * Creates a person.
	 */
	public Person()
	{
		
	}
	
	/**
	 * Creates a person with the given name.
	 * @param name
	 */
	public Person( String name )
	{
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
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the Emails
	 */
	public Set<Email> getEmails()
	{
		return emails;
	}

	/**
	 * @param Emails the Emails to set
	 */
	public void setEmails(Set<Email> emails)
	{
		this.emails = emails;
	}
	
	/**
	 * Adds the specified email to this user.
	 * @param email Email to add.
	 */
	public void addEmail( Email email )
	{
		email.setPerson( this );
		this.emails.add( email );
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
     * @return the segment
     */
    public Segment getSegment()
    {
        return segment;
    }

    /**
     * @param segment the segment to set
     */
    public void setSegment(Segment segment)
    {
        this.segment = segment;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Person [id=" + id + ", segment=" + segment + ", name=" + name + "]";
    }
	

	
}
