/*
 * Created on Sep 18, 2004
 *
 */
package aima.logic.fol.parsing.ast;

import aima.logic.fol.parsing.FOLVisitor;

/**
 * @author Ravi Mohan
 *  
 */
public class ParanthizedSentence implements Sentence {
	Sentence paranthized;
	protected ParanthizedSentence(){
		
	}
	public Sentence getParanthized() {
		return paranthized;
	}

	public ParanthizedSentence(Sentence sen) {
		paranthized = sen;
	}

	public boolean equals(Object o) {
		try {
			ParanthizedSentence ps = (ParanthizedSentence) o;
			return (ps.paranthized.equals(paranthized));
		} catch (ClassCastException e) {
			return false;
		}
	}
	public String toString(){
		String pre = " ( ";
		String mid= paranthized.toString();
		String post = " ) ";
		return pre + mid +post;
	}

	
	public Object accept(FOLVisitor v,Object arg) {
		return v.visitParanthizedSentence(this,arg);

	}
	
	public FOLNode copy(){
		return new ParanthizedSentence((Sentence)paranthized.copy());
	}

}