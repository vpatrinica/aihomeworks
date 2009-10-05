/*
 * Created on Sep 2, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import java.util.Hashtable;
import java.util.Iterator;

public class Model {

	Hashtable h = new Hashtable();

	public Boolean getStatus(Symbol symbol) {
		Object status = h.get(symbol.getValue());
		if (status != null) {
			return (Boolean) status;
		}
		return null;
	}

	public Model extend(Symbol symbol, boolean b) {
		Model m = new Model();
		String s = symbol.getValue();
		Iterator i = this.h.keySet().iterator();
		while (i.hasNext()) {
			Object key = i.next();
			Object value = h.get(key);
			String newKey = new String(((String) key).toCharArray());
			if (value == null) {
				throw new RuntimeException();
			}
			m.h.put(key, value);
		}
		m.h.put(s, new Boolean(b));
		return m;
	}

	public void print() {
		Iterator i = h.keySet().iterator();
		while (i.hasNext()) {
			Object key = i.next();
			Object value = h.get(key);
			System.out.print(key + " = " + value + " ");
			//System.out.print (key +" = " +((Boolean)value).booleanValue());
		}
		System.out.println();
	}

	public boolean isClauseTrue(Sentence clause) {
		SentenceEvaluator sEval = new SentenceEvaluator(this);
		Object result = sEval.evaluate(clause, this);
		return (result == null) ? false : ((Boolean) result).booleanValue();

	}

	public Model flip(Symbol s) {
		if (isTrue(s)) {
			return extend(s, false);
		}
		if (isFalse(s)) {
			return extend(s, true);
		}
		return this;
	}

	public String toString() {
		return h.toString();
	}

	private boolean isTrue(Symbol s) {
		Object o = h.get(s.getValue());
		return (o != null) ? ((Boolean) o).booleanValue() : false;

	}

	private boolean isFalse(Symbol s) {

		Object o = h.get(s.getValue());
		return (o != null) ? ((Boolean) o).booleanValue() : false;

	}

	private boolean isUnknown(Symbol s) {
		Object o = h.get(s.getValue());
		return (o == null);

	}
}