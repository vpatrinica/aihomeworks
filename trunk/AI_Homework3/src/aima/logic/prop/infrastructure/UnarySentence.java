/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

public class UnarySentence extends ComplexSentence {
	private Sentence negated;

	public Sentence getNegated() {
		return negated;
	}

	public UnarySentence(Sentence negated) {
		this.negated = negated;
	}

	public boolean equals(Object o) {

		try {
			UnarySentence ns = (UnarySentence) o;
			return (ns.negated.equals(negated));
		} catch (ClassCastException e) {

			return false;
		}
	}

	public String toString() {
		return " ( NOT " + negated.toString() + " ) ";
	}

	public Object visit(PLVisitor plv) {
		return plv.visitNotSentence(this);
	}
}