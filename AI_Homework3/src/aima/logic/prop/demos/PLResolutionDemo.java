/*
 * Created on Sep 26, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.demos;

import aima.logic.prop.algorithms.PLResolution;

public class PLResolutionDemo {
	private static PLResolution plr = new PLResolution();

	public static void main(String[] args) {
		displayResolutionResults("((B11 =>  (NOT P11)) AND B11)", "(NOT P11)");
		displayResolutionResults("((B11 <=> (P12 OR P21)) AND (NOT B11))",
				"(P12)");
	}

	private static void displayResolutionResults(String knowledgeBaseString,
			String query) {
		System.out.println("Running plResolution of query " + query
				+ " on knowledgeBase " + knowledgeBaseString + " gives "
				+ plr.plResolution(knowledgeBaseString, query));
	}

}