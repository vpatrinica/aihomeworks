/*
 * Created on Sep 26, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.demos;

import aima.logic.prop.algorithms.WalkSAT;
import aima.logic.prop.infrastructure.Model;

public class WalkSATDemo {
	private static WalkSAT walkSAT = new WalkSAT();

	public static void main(String[] args) {
		displayWalkSATresults(100, 0.5, "( A AND B)");
		//displayWalkSATresults(100,0.5,"( (A AND B) AND (NOT C))");
	}

	private static void displayWalkSATresults(int maxFlips,
			double probabilityOfRandomWalk, String query) {
		String resultString = " NOT FOUND ";
		Model result = walkSAT.findModelFor(query, maxFlips,
				probabilityOfRandomWalk);
		if (result != null) {
			resultString = result.toString();
		}
		System.out.println(" WALKSAT on " + query + " with a maximum of "
				+ maxFlips + " flips  and probability "
				+ probabilityOfRandomWalk + " returned " + resultString);
	}
}