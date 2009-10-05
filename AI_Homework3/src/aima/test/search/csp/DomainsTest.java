
package aima.test.search.csp;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import aima.search.csp.Domains;

/**
 * @author Ravi Mohan
 *
 */

public class DomainsTest extends TestCase {
	private Domains domains;
	public void setUp(){
		List vars = new ArrayList();
		vars.add("x");
		domains = new Domains(vars);
	}
	
	public void testEmptyDomains(){
		assertEquals(new ArrayList(),domains.getDomainOf("x")); 
	}
	public void testNonEmptyDomains(){
		List dom = new ArrayList();
		dom.add("Ravi");
		assertEquals(new ArrayList(),domains.getDomainOf("x")); 
		domains.addToDomain("x","Ravi");
		assertEquals(dom,domains.getDomainOf("x")); 
		domains.removeFromDomain("x","Ravi");
		assertEquals(new ArrayList(),domains.getDomainOf("x")); 
		
	}
}
