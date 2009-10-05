/*
 * Created on Sep 14, 2003 by Ravi Mohan
 *
 */
package aima.logic.fol.parsing.ast;

import aima.logic.fol.parsing.FOLVisitor;

public class Constant extends Term {
	private String value;

	public Constant(String s) {
		value = s;
	}
	public String getValue() {
		return value;
	}
	public boolean equals(Object o) {
		try {
			Constant c = (Constant) o;
			return (c.getValue().equals(getValue()));
		} catch (ClassCastException e) {

			return false;
		}
	}
	public int hashCode() {
		return 0;
	}
	public boolean renamingEquals(Sentence s) {
		return equals(s);
	}
	public String toString() {
		return value;
	}
	public Object accept(FOLVisitor v,Object arg) {
		return v.visitConstant(this,arg);
	}
	public Object clone() {
		return new Constant(value);
	}
	public FOLNode copy() {
		return  new Constant(value);
	}

}
