/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

public class TrueSentence extends AtomicSentence {

	public String toString() {
		return "TRUE";
	}

	public Object visit(PLVisitor plv) {
		return plv.visitTrueSentence(this);
	}
}