/*
 * Created on Sep 20, 2004
 *
 */
package aima.test.logictest.foltest;

import junit.framework.TestCase;
import aima.logic.fol.parsing.ast.Variable;

/**
 * @author Ravi Mohan
 *
 */
public class NodeEqualityTest extends TestCase {
	public void testVariableEqualityTest(){
		Variable v1 = new Variable("x");
		Variable v2 = new Variable("x");
		Variable v3 = new Variable("y");
		assertEquals(v1,v2);
		
		v1.setValue("y");
		assertEquals(v1,v3);
		
		assertEquals(v1,v1);
		
	}

}
