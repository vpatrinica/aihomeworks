/*
 * Created on Sep 25, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.algorithms.DPLLSatisfiable;


public class DPLLTest extends TestCase {
	DPLLSatisfiable dpll;
	public void setUp(){
		dpll= new DPLLSatisfiable();
	}
	
	public void testDpll(){
		assertEquals(true,dpll.isSatisfiable("(A AND B)"));
		assertEquals(false,dpll.isSatisfiable("(A AND (NOT A))"));
		assertEquals(true,dpll.isSatisfiable("(A AND (NOT B))"));
		assertEquals(true,dpll.isSatisfiable("(A => (NOT B))"));
		assertEquals(true,dpll.isSatisfiable("(A <=> (NOT B))"));
	}
	

}
