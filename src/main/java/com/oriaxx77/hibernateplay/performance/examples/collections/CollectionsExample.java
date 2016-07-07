package com.oriaxx77.hibernateplay.performance.examples.collections;

import java.util.Date;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oriaxx77.hibernateplay.performance.model.Child;
import com.oriaxx77.hibernateplay.performance.model.Parent;
import com.oriaxx77.hibernateplay.performance.util.HibernateExampleBase;






/**
 * Example for demonstrating the importance of the collection type selection. 
 * See the {@link Parent} entity, specifically, the children collection.
 */
@Service
@Transactional
public class CollectionsExample extends HibernateExampleBase
{
    /** Logger */
    final static Logger logger = Logger.getLogger( CollectionsExample.class);
    
    /**
     * Creates a {@link Parent} with some {@link Child}
     * @return this instance to use it fluently
     */
    public CollectionsExample createParentWithChildren()
    {
        logger.info( "Creating parent" );
        Parent parent = new Parent();
        parent.addChild( new Child( "A" + new Date() ) );
        parent.addChild( new Child( "B" + new Date() ) );
        parent.addChild( new Child( "C" + new Date() ) );
        getSession().save( parent );
        return this;
    }
    
    /**
     * Adds some {@link Child} to the first loaded {@link Parent}
     * @return this instance to use it fluently
     */
    public CollectionsExample addSomeChildren()
    {
        logger.info( "Adding some children" );
        Parent parent = (Parent)getSession().createCriteria( Parent.class ).list().get(0);
        parent.addChild( new Child( "A" + new Date() ) );
        parent.addChild( new Child( "B" + new Date() ) );
        parent.addChild( new Child( "C" + new Date() ) );
        getSession().saveOrUpdate( parent );
        return this;
    }
    
    /**
     * Removes some children from the first loaded {@link Parent}
     * @return this instance to use it fluently
     */
    public CollectionsExample removeSomeChildren()
    {
        logger.info( "Removing some children" );
        Parent parent = (Parent)getSession().createCriteria( Parent.class ).list().get(0);
        parent.removeChild( parent.getChildren().get(1) );
        return this;
    }
    
    /**
     * Updates some children of the first retrieved {@link Parent}
     * @return this instance to use it fluently
     */
    public CollectionsExample updateSomeChildren()
    {
        logger.info( "Updating some children" );
        Parent parent = (Parent)getSession().createCriteria( Parent.class ).list().get(0);
        IntStream.range(1, 3).forEach( i -> {
            Child child = parent.getChildren().get( i );
            child.setName( child.getName().toUpperCase() );
        } );
        
        return this;
    }
    
    /**
     * Removes all children and creates some new one for the 
     * first loaded {@link Parent}
     * @return this instance to use it fluently
     */
    public CollectionsExample removeAllChildrenAndAddSomenew()
    {
        logger.info( "Removing all children and adding some new" );
        Parent parent = (Parent)getSession().createCriteria( Parent.class ).list().get(0);
        parent.getChildren().clear();
        IntStream.range(1, 4).forEach( i -> {
            parent.addChild( new Child( "" + i + "" + new Date() ) );  
        } );
        
        return this;
    }
   
    
    
	
}
