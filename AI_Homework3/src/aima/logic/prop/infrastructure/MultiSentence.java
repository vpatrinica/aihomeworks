/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import java.util.List;

public class MultiSentence extends ComplexSentence {
	private String operator;

	private List sentences;

	public MultiSentence(String operator, List sentences) {
		this.operator = operator;
		this.sentences = sentences;
	}

	public String getOperator() {
		return operator;
	}

	public List getSentences() {
		return sentences;
	}

	public boolean equals(Object o) {
		try {
			MultiSentence sen = (MultiSentence) o;
			return ((sen.getOperator().equals(getOperator())) && (sen
					.getSentences().equals(getSentences())));
		} catch (ClassCastException e) {
			return false;
		}
	}

	public String toString() {
		String part1 = "( " + getOperator() + " ";
		for (int i = 0; i < getSentences().size(); i++) {
			part1 = part1 + sentences.get(i).toString() + " ";
		}
		return part1 + " ) ";
	}

	public Object visit(PLVisitor plv) {
		return plv.visitMultiSentence(this);
	}

}