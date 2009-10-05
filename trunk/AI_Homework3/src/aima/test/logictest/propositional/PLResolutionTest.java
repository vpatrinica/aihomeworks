/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.algorithms.PLResolution;

public class PLResolutionTest extends TestCase {

	public void testPLResolution() {
		PLResolution plr = new PLResolution();

		boolean b = plr.plResolution("((B11 =>  (NOT P11)) AND B11)", "(P11)");
		assertEquals(false, b);

		b = plr.plResolution("(A AND B)", "B");
		assertEquals(true, b);

		b = plr.plResolution("((B11 =>  (NOT P11)) AND B11)", "(NOT P11)");
		assertEquals(true, b);

		b = plr.plResolution("((B11 =>  (NOT P11)) AND B11)", "(NOT B11)");
		assertEquals(false, b);

		b = plr.plResolution("(A <=>  B)", "X");
		assertEquals(false, b);

		b = plr.plResolution("((B11 <=> (P12 OR P21)) AND (NOT B11))", "(NOT P11)");
		assertEquals(false, b);

		b = plr.plResolution("((B11 <=> (P12 OR P21)) AND (NOT B11))", "X");
		assertEquals(false, b);
	}

}
