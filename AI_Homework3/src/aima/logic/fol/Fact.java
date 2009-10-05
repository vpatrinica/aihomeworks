/*
 * Created on Sep 22, 2004
 *
 */
package aima.logic.fol;

import aima.logic.fol.parsing.ast.FOLNode;
import aima.logic.fol.parsing.ast.Sentence;

/**
 * @author Ravi Mohan
 *  
 */
public class Fact {

	private Sentence original;

	public Fact(Sentence sentence) {
		this.original = sentence;
	}

	public String toString() {
		return original.toString();
	}

	public FOLNode predicate() {
		return original;
	}

	public boolean equals(Object o) {
		try {
			Fact f = (Fact) o;
			return f.predicate().equals(predicate());
		} catch (ClassCastException e) {
			return false;
		}
	}

}