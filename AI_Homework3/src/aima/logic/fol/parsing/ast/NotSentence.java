/*
 * Created on Sep 14, 2003 by Ravi Mohan
 *
 */
package aima.logic.fol.parsing.ast;

import aima.logic.fol.parsing.FOLVisitor;

public class NotSentence implements Sentence {
	private Sentence negated;

	public NotSentence(Sentence negated) {
		this.negated = negated;
	}
	public Sentence getNegated() {
		return negated;
	}
	public FOLNode copy() {
		return new NotSentence((Sentence)negated.copy());
	}



	public boolean equals(Object o) {
		try {
			NotSentence ns = (NotSentence) o;
			return (ns.negated.equals(negated));
		} catch (ClassCastException e) {
			return false;
		}
	}
	public Object accept(FOLVisitor v,Object arg) {
		return v.visitNotSentence(this,arg);
	}
	public String toString(){
		return " NOT "+negated.toString();
	}



}
