/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.algorithms.TTEntails;
import aima.logic.prop.infrastructure.BinarySentence;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;

public class TTEntailsTest extends TestCase {
	TTEntails tte;
	public void setUp(){
		tte = new TTEntails();
	}
	public void testTTEntails() {

		 tte = new TTEntails();
		boolean b = tte.entails("(A AND B)", "A");
		assertEquals(true, b);

		b = tte.entails("(A OR B)", "A");
		assertEquals(false, b);

		b = tte.entails("((A => B) AND A)", "B");
		assertEquals(true, b);

		b = tte.entails("((A => B) AND B)", "A");
		assertEquals(false, b);

		b = tte.entails("A", "(NOT A)");
		assertEquals(false, b);

		b = tte.entails("(NOT A)", "(A)");
		assertEquals(false, b);

	}
	
	public void testAimaExample(){
		PEParser parser = new PEParser();
		Sentence r1 = (Sentence)parser.parse("(NOT P11)");
		Sentence r2 =  (Sentence)parser.parse("(B11 <=> (P12 OR P21))");
		Sentence r3 =  (Sentence)parser.parse("(B21 <=> ((P11 OR P22) OR P31))");
		Sentence r4 =  (Sentence)parser.parse("(NOT B11)");
		Sentence r5 =  (Sentence)parser.parse("(B21)");
		
		Sentence kb = AND (AND (AND (r1,r2),AND (r3,r4)),r5);
		
		
		
		//Sentence kb = (Sentence)parser.parse("((((((NOT P11) AND (P12 OR P21)) AND (B11 <=> (P12 OR P21))) AND (B21 <=> (P11 OR (P22 OR P31)))) AND (NOT B11)) AND B21)");
		assertEquals(true,tte.entails(kb.toString(),"(NOT P12)"));
		assertEquals(false,tte.entails(kb.toString(),"(P22)"));
	}
	
	private Sentence AND (Sentence one, Sentence two){
		return new BinarySentence("AND", one, two);
	}

}
