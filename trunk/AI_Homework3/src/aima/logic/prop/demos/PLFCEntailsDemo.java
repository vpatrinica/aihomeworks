/*
 * Created on Sep 26, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.demos;

import aima.logic.prop.algorithms.PLFCEntails;
import aima.logic.prop.infrastructure.BinarySentence;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;

public class PLFCEntailsDemo {
	private static PEParser parser = new PEParser();

	private static PLFCEntails plfce = new PLFCEntails();

	public static void main(String[] args) {
		Sentence s1 = (Sentence) parser.parse(" (P => Q)");
		Sentence s2 = (Sentence) parser.parse("((L AND M) => P)");
		Sentence s3 = (Sentence) parser.parse("( (B AND L) => M)");
		Sentence s4 = (Sentence) parser.parse("( (A AND P) => L)");
		Sentence s5 = (Sentence) parser.parse("((A AND B) => L)");
		Sentence s6 = (Sentence) parser.parse("(A)");
		Sentence s7 = (Sentence) parser.parse("(B)");

		System.out.println("Example from  page 220 of AIMA 2nd Edition");
		System.out.println("KnowledgeBsse consists of sentences");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		System.out.println(" " + s6);
		System.out.println(" " + s7);
		System.out.println();

		Sentence kb = AND(s3, AND(AND(s1, s2), AND(AND(s4, s5), AND(s6, s7))));

		displayPLFCEntailment(kb.toString(), "Q");
	}

	private static Sentence AND(Sentence one, Sentence two) {
		return new BinarySentence("AND", one, two);
	}

	private static void displayPLFCEntailment(String kbs, String qs) {
		System.out.println("Running PLFCEntailment on knowledge base  " + kbs
				+ " with query " + qs + " gives " + plfce.entails(kbs, qs));
	}
}