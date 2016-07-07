/**
 * 
 */
package com.oriaxx77.hibernateplay.performance.examples.customtype;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.oriaxx77.hibernateplay.performance.model.Person;
import com.oriaxx77.hibernateplay.performance.model.Segment;
import com.oriaxx77.hibernateplay.performance.model.SegmentType;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;
/**
 * Example for demoing user defined types.
 * See {@link SegmentType}.
 */
@Service
public class CustomTypeExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( CustomTypeExample.class );
    
    
    /**
     * Creates Mozart and Bartok as VIP and Jozsi as REGULAR person
     * @return CustomTypeExample return with this object for using it as a fluent API
     */
    @Transactional
    public CustomTypeExample createSomePeople()
    {
        Person mozart = new Person( "Mozart" );
        mozart.setSegment( Segment.VIP );
        getSession().save( mozart );
        
        Person bartok = new Person( "Bartok" );
        bartok.setSegment( Segment.VIP );
        getSession().save( bartok );
        
        Person jozsi = new Person( "Jozsi" );
        bartok.setSegment( Segment.REGULAR );
        getSession().save( bartok );
        
        logger.info( "People created: " + mozart + ", " + bartok + ", " + jozsi);
        return this;
    }
    
    /**
     * Retrieves all VIPs and logs them.
     * @return @return CustomTypeExample return with this object for using it as a fluent API
     */
    @SuppressWarnings("unchecked") // tested
    @Transactional
    public CustomTypeExample loadVIPs()
    {
        getSession().createCriteria( Person.class )
                .add( Restrictions.eq( "segment" , Segment.VIP ))
                .list().stream().forEach( vip -> {
                    logger.info( vip );
                    ((Person)vip).getSegment().someMeaningfulBusinessOperation();
                }); ;
        
        return this;
    }
    
    
        
}
