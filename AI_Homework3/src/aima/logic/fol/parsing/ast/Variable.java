/*
 * Created on Sep 14, 2003 by Ravi Mohan
 *  
 */
package aima.logic.fol.parsing.ast;

import aima.logic.fol.parsing.FOLVisitor;

public class Variable extends Term {
	private String value;

	public Variable(String s) {
		value = s.trim();
	}

	public String getValue() {
		return value;
	}

	public boolean equals(Object o) {

		try {
			Variable v = (Variable) o;
			return (v.getValue().equals(getValue()));
		} catch (ClassCastException e) {

			return false;
		}
	}

	public int hashCode() {
		return 0;
	}

	public String toString() {
		return value;
	}

	public Object accept(FOLVisitor v,Object arg) {
		return v.visitVariable(this,arg);
	}

	public Object clone() {
		return new Variable(value);
	}

	public FOLNode copy() {
		return new Variable(value);
	}

	public void setValue(String value) {
		this.value =value.trim();
		
	}
}