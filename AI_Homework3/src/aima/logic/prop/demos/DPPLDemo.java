/*
 * Created on Sep 26, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.demos;

import aima.logic.prop.algorithms.DPLLSatisfiable;

public class DPPLDemo {
	private static DPLLSatisfiable dpll = new DPLLSatisfiable();

	public static void main(String[] args) {
		displayDPLLSatisfiableStatus("( A AND B )");
		displayDPLLSatisfiableStatus("( A AND (NOT A) )");
		//displayDPLLSatisfiableStatus("((A OR (NOT A)) AND (A OR B))");
	}

	public static void displayDPLLSatisfiableStatus(String query) {
		if (dpll.isSatisfiable(query)) {
			System.out.println(query + " is  (DPLL) satisfiable");
		} else {
			System.out.println(query + " is NOT (DPLL)  satisfiable");
		}
	}

}