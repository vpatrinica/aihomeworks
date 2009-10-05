/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

public class FalseSentence extends AtomicSentence {
	public String toString() {
		return "FALSE";
	}

	public Object visit(PLVisitor plv) {
		return plv.visitFalseSentence(this);
	}
}