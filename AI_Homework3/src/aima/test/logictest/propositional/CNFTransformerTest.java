/*
 * Created on Sep 16, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import java.util.List;

import junit.framework.TestCase;
import aima.logic.prop.infrastructure.CNFClauseGatherer;
import aima.logic.prop.infrastructure.CNFTransformer;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;

public class CNFTransformerTest extends TestCase {
	public void testTransform() {
		PEParser parser = new PEParser();
		Sentence s = (Sentence) parser.parse("((B11 <=> (P12 OR P21)) AND (NOT B11))");
		CNFTransformer ct = new CNFTransformer();
		Sentence cnfSen = ct.toCNF(s);
		String str = " (  (  (  ( NOT B11 )  OR  ( P12 OR P21 ) ) AND  (  ( B11 OR  ( NOT P12 )  ) AND  ( B11 OR  ( NOT P21 )  ) ) ) AND  ( NOT B11 )  )";
		assertEquals(str, cnfSen.toString());

		s = (Sentence) parser.parse("(A <=> B)");
		assertEquals(" (  (  ( NOT A )  OR B ) AND  (  ( NOT B )  OR A ) )", ct.toCNF(s).toString());

		s = (Sentence) parser.parse("(NOT (NOT (NOT A)))");
		assertEquals(" ( NOT A ) ", ct.toCNF(s).toString());

		CNFClauseGatherer cg = new CNFClauseGatherer();
		List clauses = cg.getClauses(cnfSen);
		assertEquals(4, clauses.size());

		
	}

}
