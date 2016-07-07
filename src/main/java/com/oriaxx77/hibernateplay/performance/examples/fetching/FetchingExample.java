/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.fetching;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oriaxx77.hibernateplay.performance.model.Email;
import com.oriaxx77.hibernateplay.performance.model.Person;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;

/**
 * Service to play with the fetching strategies.
 * Use the {{@link #createAndListPersons()} to create some {@link Person} with {@link Email} objects
 * and list them. See the STDOUT to see the queries. See the Person#emails @OneToMany mappings in the {@link Person} class
 */
@Service
public class FetchingExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( FetchingExample.class );
    
    
    /**
     * Creates and list persons.
     */
    @Transactional
    public void createAndListPersons()
    {
        IntStream.range(0, 10).forEach( i -> {
            Person p = new Person();
            IntStream.range(0, 10).forEach(  j -> p.addEmail( new Email() ) );
            logger.info( "Saving : " + p );
            getSession().save( p );
        } );
        
        
        
        @SuppressWarnings("unchecked")
        List<Person> persons = getSession().createQuery( "FROM Person" ).list(); 
        logger.info( "Iterating persons" );
        persons.stream().forEach( person -> {
            logger.info( person );
            logger.info( "-- Iterate emails: --");
            person.getEmails().stream().forEach( email ->  logger.info( email ) );
            
        });
    }
}
