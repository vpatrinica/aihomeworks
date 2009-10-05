/*
 * Created on Sep 22, 2004
 *
 */
package aima.test.logictest.foltest;

import java.util.ArrayList;
import java.util.List;

import aima.search.csp.Domains;

import junit.framework.TestCase;

/**
 * @author Ravi Mohan
 *
 */
public class DomainTest extends TestCase {
	
	public void testDomain(){
		List l = new ArrayList();
		l.add("x");
		Domains d =new Domains(l);
		assertNotNull(d.getDomainOf("x"));
		assertEquals(new ArrayList(),d.getDomainOf("x"));
	}

}
