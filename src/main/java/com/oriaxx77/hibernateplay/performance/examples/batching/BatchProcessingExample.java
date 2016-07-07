/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.batching;

import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;

import com.oriaxx77.hibernateplay.performance.model.Company;
import com.oriaxx77.hibernateplay.performance.model.Employee;
import com.oriaxx77.hibernateplay.performance.model.Greeting;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;
/**
 * Hibernate Batch processing example.
 * See {@link #doDMLStyleOperations()} for DML style operations.
 * See {@link #doBatchOperations()}} for batch processing.
 */
@Service
public class BatchProcessingExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( BatchProcessingExample.class );
    
    
    /**
     * Hibernate DML style operations.
     * @return return with this object for using it as a fluent API
     */
    @Transactional
    public BatchProcessingExample doDMLStyleOperations()
    {
        // Create some Greetings
        IntStream.range( 1, 6).forEach( i -> getSession().save( new Greeting( "msg" +i, "EN" ) ));
        // DML some insert on EnglishGreeting
        int insertCount = getSession()
                                    .createQuery( "INSERT INTO EnglishGreeting( message ) SELECT g.message FROM Greeting g WHERE language = 'EN' " )
                                    .executeUpdate();
        logger.info( "EnglishGreeting insert count: " + insertCount );
        
        // DML update on Greetings
        int updateCount = getSession()
                            .createQuery( "UPDATE Greeting SET language=:newLanguage WHERE language=:oldLanguage" )
                            .setString("newLanguage", "US" )
                            .setString( "oldLanguage" , "EN" )
                            .executeUpdate();
        logger.info( "Greeting update count: " + updateCount );
        
        // DML delete on EnglishGreetings
        int deleteCount = getSession()
                            .createQuery( "DELETE EnglishGreeting" )
                            .executeUpdate();
        logger.info( "EnglishGreeting delete count: " + deleteCount );
        return this;
    }
    
    
    
    /**
     * Hibernate batch insert and update.
     * It requires the following hibernate properties:
     * - hibernate.jdbc.batch_size = 10 - 50
     * - hibernate.order_inserts = true
     * - hibernate.order_updates = true
     * - hibernate.jdbc.batch_versioned_data = true //In this case check your Driver's executeBatch() corecteness
     * @return return with this object for using it as a fluent API
     */ 
    @Transactional
    public BatchProcessingExample doBatchOperations()
    {
        // Companys batch insert 
        for ( int i = 0; i < 10; i++ ) 
        {
            logger.info( "Batch insert counter: " + i);
            Company company = new Company( "Company" + i );
            IntStream.range(0, 3).forEach( j -> company.addEmployee( new Employee() ) );
            getSession().save( company );
            if ( (i+1) % 5 == 0 ) // Same as the JDBC batch size 
            { 
                //flush a batch of inserts and release memory:
                getSession().flush();
                getSession().clear();
                logger.info( "Batch insert flush" );
            }
        }     
        
        // Company batch update
        @SuppressWarnings("unchecked")
        List<Company> companies = getSession()
                                        .createCriteria( Company.class ).list();
        for ( int i = 0; i < companies.size(); i++ ) 
        {
            logger.info( "Batch update counter: " + i);
            Company company = companies.get(i);
            company.setName( company.getName().toUpperCase());
            getSession().saveOrUpdate( company );
            if ( (i+1) % 5 == 0 ) // Same as the JDBC batch size 
            { 
                //flush a batch of inserts and release memory:
                getSession().flush();
                getSession().clear();
                logger.info( "Batch update flush" );
            }
        }   

        
        // Delete the companies
        logger.info( "Employee count before company delete: " + getSession().createCriteria( Employee.class )
                .setProjection( Projections.rowCount()).uniqueResult() ); 
        
        for ( int i = 0; i < companies.size() ; i++ )
        {
            logger.info( "Batch delete counter: " + i);
            Company company = companies.get( i );
            getSession().delete( company );
            if ( (i+1) % 5 == 0 ) // Same as the JDBC batch size 
            { 
                //flush a batch of inserts and release memory:
                getSession().flush();
                getSession().clear();
                logger.info( "Batch delete flush" );
            }

        }
        
        logger.info( "Employee count after company delete: " + getSession().createCriteria( Employee.class )
                            .setProjection( Projections.rowCount()).uniqueResult() ); 
        
        
        return this;
    }
    
}
