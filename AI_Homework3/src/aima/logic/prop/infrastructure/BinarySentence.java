/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

public class BinarySentence extends ComplexSentence {
	private String operator;

	private Sentence first;

	private Sentence second;

	public BinarySentence(String operator, Sentence first, Sentence second) {
		this.operator = operator;
		this.first = first;
		this.second = second;

	}

	public Sentence getFirst() {
		return first;
	}

	public String getOperator() {
		return operator;
	}

	public Sentence getSecond() {
		return second;
	}

	public boolean equals(Object o) {
		try {
			BinarySentence bs = (BinarySentence) o;
			return ((bs.getOperator().equals(getOperator()))
					&& (bs.getFirst().equals(first)) && (bs.getSecond()
					.equals(second)));

		} catch (ClassCastException e) {
			return false;
		}
	}

	public String toString() {
		return " ( " + first.toString() + " " + operator + " "
				+ second.toString() + " )";
	}

	public Object visit(PLVisitor plv) {
		return plv.visitBinarySentence(this);
	}

	public boolean isOrSentence() {
		return (getOperator().equals("OR"));
	}

	public boolean isAndSentence() {
		return (getOperator().equals("AND"));
	}

	public boolean isImplication() {
		return (getOperator().equals("=>"));
	}

	public boolean isBiconditional() {
		return (getOperator().equals("<=>"));
	}
}