/*
 * Created on Sep 21, 2004
 *
 */
package aima.test.search.csp;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import aima.search.csp.Assignment;

/**
 * @author Ravi Mohan
 *  
 */
public class AssignmentTest extends TestCase {
	private Assignment assignment;

	public void setUp() {
		List set = new ArrayList();
		set.add("x");
		set.add("y");
		assignment = new Assignment(set);
	}
	
	public void testAssignmentCompletion(){
		assertFalse(assignment.isComplete());
		assignment.setAssignment("x","Ravi");
		assertFalse(assignment.isComplete());
		assignment.setAssignment("y","AIMA");
		assertTrue(assignment.isComplete());
		assignment.remove("x");
		assertFalse(assignment.isComplete());
	}
	
	public void testAssignmentDefaultVariableSelection(){
		assertEquals("x",assignment.selectFirstUnassignedVariable());
		assignment.setAssignment("x","Ravi");
		assertEquals("y",assignment.selectFirstUnassignedVariable());
		assignment.setAssignment("y","AIMA");
		assertEquals(null,assignment.selectFirstUnassignedVariable());
	}
	
	
	
	
}