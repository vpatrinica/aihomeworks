/*
 * Created on Sep 26, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.demos;

import aima.logic.prop.algorithms.TTEntails;
import aima.logic.prop.infrastructure.BinarySentence;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;

public class TTEntailsDemo {
	private static TTEntails tte = new TTEntails();

	public static void main(String[] args) {

		PEParser parser = new PEParser();
		Sentence r1 = (Sentence) parser.parse("(NOT P11)");
		Sentence r2 = (Sentence) parser.parse("(B11 <=> (P12 OR P21))");
		Sentence r3 = (Sentence) parser
				.parse("(B21 <=> ((P11 OR P22) OR P31))");
		Sentence r4 = (Sentence) parser.parse("(NOT B11)");
		Sentence r5 = (Sentence) parser.parse("(B21)");
		Sentence kb = AND(AND(AND(r1, r2), AND(r3, r4)), r5);

		System.out.println("KnowledgeBsse consists of sentences");
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
		System.out.println(" " + r5);
		System.out.println();

		displayTTEntails(kb.toString(), "(NOT P12)");
		displayTTEntails(kb.toString(), "(P22)");

	}

	private static Sentence AND(Sentence one, Sentence two) {
		return new BinarySentence("AND", one, two);
	}

	private static void displayTTEntails(String kbs, String s) {
		System.out.println(" ttentails (\"" + s + "\" ) returns "
				+ tte.entails(kbs, s));
	}

}