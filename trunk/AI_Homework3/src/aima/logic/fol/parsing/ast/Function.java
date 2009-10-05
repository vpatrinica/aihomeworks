/*
 * Created on Sep 14, 2003 by Ravi Mohan
 *  
 */
package aima.logic.fol.parsing.ast;

import java.util.ArrayList;
import java.util.List;

import aima.logic.fol.parsing.FOLVisitor;

public class Function extends Term {
	private String functionName;

	private List terms;

	public String getFunctionName() {
		return functionName;
	}

	public List getTerms() {
		return terms;
	}

	public Function(String functionName, List terms) {
		this.functionName = functionName;
		this.terms = terms;
	}

	public boolean equals(Object o) {
		try {
			Function f = (Function) o;
			boolean nameEquality = f.getFunctionName()
					.equals(getFunctionName());
			boolean termEquality = f.getTerms().equals(getTerms());
			boolean eq = nameEquality && termEquality;

			return eq;
		} catch (ClassCastException e) {

			return false;
		}
	}

	public Object accept(FOLVisitor v, Object arg) {

		return v.visitFunction(this, arg);

	}

	public String toString() {
		String pre = " " + functionName + "( ";
		String mid = "";
		for (int i = 0; i < terms.size(); i++) {
			mid += "," + ((Term) terms.get(i)).toString();
		}
		mid = mid.substring(1);
		String post = " )";
		return pre + mid + post;
	}

	public FOLNode copy() {
		List copyTerms = new ArrayList();
		for (int i = 0; i < terms.size(); i++) {
			Term t = (Term) terms.get(i);
			copyTerms.add(t.copy());
		}
		return new Function(functionName, copyTerms);
	}

}