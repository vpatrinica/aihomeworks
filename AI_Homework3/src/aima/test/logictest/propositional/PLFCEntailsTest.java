/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.algorithms.PLFCEntails;
import aima.logic.prop.infrastructure.BinarySentence;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;

public class PLFCEntailsTest extends TestCase {
	PEParser parser;
	PLFCEntails plfce;
	public void setUp() {
		parser = new PEParser();
		plfce = new PLFCEntails();
	}

	public void testAIMAExample() {
		Sentence s1 = (Sentence) parser.parse(" (P => Q)");
		Sentence s2 = (Sentence) parser.parse("((L AND M) => P)");
		Sentence s3 = (Sentence) parser.parse("( (B AND L) => M)");
		Sentence s4 = (Sentence) parser.parse("( (A AND P) => L)");
		Sentence s5 = (Sentence) parser.parse("((A AND B) => L)");
		Sentence s6 = (Sentence) parser.parse("(A)");
		Sentence s7 = (Sentence) parser.parse("(B)");
		
		Sentence kb = AND (s3,AND (AND (s1,s2), AND( AND(s4,s5), AND (s6,s7))));
		
		Sentence query =  (Sentence)parser.parse("Q");
		
		assertEquals(true,plfce.entails(kb.toString(),"Q"));

	}

	private Sentence AND(Sentence one, Sentence two) {
		return new BinarySentence("AND", one, two);
	}

}
