/*
 * Created on Sep 20, 2004
 *
 */
package aima.test.utiltest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import aima.util.LogicUtils;

/**
 * @author Ravi Mohan
 *  
 */
public class SetTest extends TestCase {
	Set s1, s2;

	public void setUp() {
		s1 = new HashSet();
		s1.add(new Integer(1));
		s1.add(new Integer(2));
		s1.add(new Integer(3));
		s1.add(new Integer(4));

		s2 = new HashSet();
		s2.add(new Integer(4));
		s2.add(new Integer(5));
		s2.add(new Integer(6));
	}
	public void testUnion(){
		Set union = LogicUtils.union(s1,s2);
		assertEquals(6,union.size());
		assertEquals(4,s1.size());
		assertEquals(3,s2.size());
		
		s1.remove(new Integer(1));
		assertEquals(6,union.size());
		assertEquals(3,s1.size());
		assertEquals(3,s2.size());
	}
	
	public void testIntersection(){
		Set intersection = LogicUtils.intersection(s1,s2);
		assertEquals(1,intersection.size());
		assertEquals(4,s1.size());
		assertEquals(3,s2.size());
		
		s1.remove(new Integer(1));
		assertEquals(1,intersection.size());
		assertEquals(3,s1.size());
		assertEquals(3,s2.size());
	}
	public void testDifference(){
		Set difference = LogicUtils.difference(s1,s2);
		assertEquals(3,difference.size());
		assertTrue(difference.contains(new Integer(1)));
		assertTrue(difference.contains(new Integer(2)));
		assertTrue(difference.contains(new Integer(3)));
		
	}
	
	public void testDifference2(){
		Set one = new HashSet();
		Set two = new HashSet();
		one.add(new Integer(1));
		two.add(new Integer(1));
		Set difference = LogicUtils.difference(one,two);
		assertTrue(difference.isEmpty());
		
	}
	public void testListRemove(){
		List one = new ArrayList();		
		one.add(new Integer(1));
		assertEquals(1,one.size());
		one.remove(0);
		assertEquals(0,one.size());
		
	}

}