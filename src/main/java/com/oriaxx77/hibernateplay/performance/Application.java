package com.oriaxx77.hibernateplay.performance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.oriaxx77.hibernateplay.performance.examples.batching.BatchProcessingExample;
import com.oriaxx77.hibernateplay.performance.examples.cacheing.CacheingExample;
import com.oriaxx77.hibernateplay.performance.examples.collections.CollectionsExample;
import com.oriaxx77.hibernateplay.performance.examples.customtype.CustomTypeExample;
import com.oriaxx77.hibernateplay.performance.examples.fetching.FetchingExample;
import com.oriaxx77.hibernateplay.performance.examples.greeting.GreetingExample;
import com.oriaxx77.hibernateplay.performance.examples.nativequery.NativeQueryExample;
import com.oriaxx77.hibernateplay.performance.examples.reportquery.ReportQueryExample;
import com.oriaxx77.hibernateplay.performance.examples.statelesssession.StatelessSessionExample;

/**
 * Spring Boot application. Static entry point of this app.
 * It runs the examples.
 * 
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})
//If you see HttpMapperProperties deprecation warning 
//You can safely ignore this warning as it will be fixed in IDE-1366 for release STS 3.7.0.
//http://stackoverflow.com/questions/28818178/class-org-springframework-boot-autoconfigure-web-httpmapperproperties-is-marke
public class Application
{
    
    
    
    
    
	/**
	 * Static entry point of the app.
	 * @param args Command line argumemnts. Use it to pass arguments to the Spring boot app.
	 */
	public static void main(String[] args) throws Exception
	{
	    
	    
	    
		ConfigurableApplicationContext ctx = SpringApplication.run( Application.class, args );
		
		GreetingExample greetingExample = ctx.getBean( GreetingExample.class );
		greetingExample.greet( "M.J." );
		
		FetchingExample fetchingExample = ctx.getBean( FetchingExample.class );
		fetchingExample.createAndListPersons();
		
		CacheingExample cachingExample = ctx.getBean( CacheingExample.class );
		cachingExample.l1Caching()
		              .queryCacheing()
		              .queryCacheing();
		Long msgId = cachingExample.createMessage();
		cachingExample.loadMessage( msgId ); // L2 cache hit -> 0
		cachingExample.loadMessage( msgId ); // L2 cache hit -> 1
		
		NativeQueryExample nativeQueryExample = ctx.getBean( NativeQueryExample.class );
		nativeQueryExample.runNativeQuery()
		                  .runNativeQueryWithTransformer();
		
		
		ReportQueryExample reportQueryExample = ctx.getBean( ReportQueryExample.class );
		reportQueryExample.runReportQueries();
		
		BatchProcessingExample batchProcessingExample = ctx.getBean( BatchProcessingExample.class );
		batchProcessingExample.doDMLStyleOperations()
		                      .doBatchOperations();
		
		
		CollectionsExample collectionsExample = ctx.getBean( CollectionsExample.class );
		collectionsExample.createParentWithChildren()
		                  .updateSomeChildren()
		                  .removeSomeChildren()
		                  .addSomeChildren()
		                  .removeAllChildrenAndAddSomenew();
		
		
		StatelessSessionExample statelessSessionExample = ctx.getBean( StatelessSessionExample.class );
		statelessSessionExample.createParentWithChildren();
		
		CustomTypeExample customTypeExample = ctx.getBean( CustomTypeExample.class );
		customTypeExample.createSomePeople()
		                 .loadVIPs();
	}
}
