/*
 * Created on Sep 2, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.infrastructure.Model;
import aima.logic.prop.infrastructure.Symbol;

public class ModelTest extends TestCase {
	private Model m;
	public void setUp() {
		m = new Model();
	}
	public void testEmptyModel() {
		assertEquals(null, m.getStatus(new Symbol("P")));
	}
	public void testExtendModel() {
		String p = "P";
		m = m.extend(new Symbol(p), true);
		assertEquals(Boolean.TRUE, m.getStatus(new Symbol("P")));
		p = "Q";
		assertEquals(Boolean.TRUE, m.getStatus(new Symbol("P")));
	}
}
