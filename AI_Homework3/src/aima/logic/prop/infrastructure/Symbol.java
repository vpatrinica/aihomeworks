/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

public class Symbol extends AtomicSentence {
	private String value;

	public Symbol(String value) {
		this.value = value;
	}

	public boolean equals(Object o) {
		try {
			Symbol sym = (Symbol) o;
			return (sym.getValue().equals(getValue()));
		} catch (ClassCastException e) {
			return false;
		}
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return getValue();
	}

	public Object visit(PLVisitor plv) {
		return plv.visitSymbol(this);
	}

}