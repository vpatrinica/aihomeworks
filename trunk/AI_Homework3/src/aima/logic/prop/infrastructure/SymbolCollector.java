/*
 * Created on Sep 16, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymbolCollector implements PLVisitor {
	private Set symbols;

	private Set negatedSymbols;

	private Set positiveSymbols;

	public SymbolCollector() {
		this.symbols = new HashSet();
		this.negatedSymbols = new HashSet();
		this.positiveSymbols = new HashSet();
	}

	public Set getNegatedSymbols() {
		return negatedSymbols;
	}

	public Set getSymbols() {
		return symbols;
	}

	public Object visitSymbol(Symbol s) {

		symbols.add(s);
		//positiveSymbols.add(s);
		return null;
	}

	public Object visitTrueSentence(TrueSentence s) {
		return symbols;
	}

	public Object visitFalseSentence(FalseSentence s) {
		return symbols;
	}

	public Object visitBinarySentence(BinarySentence s) {
		Set symbolsCollected = (Set) symbols;
		symbolsCollected = (Set) s.getFirst().visit(this);
		symbolsCollected = (Set) s.getSecond().visit(this);

		if (s.getFirst() instanceof Symbol) {
			positiveSymbols.add(s.getFirst());
		}
		if (s.getSecond() instanceof Symbol) {
			positiveSymbols.add(s.getSecond());
		}

		return null;
	}

	public Object visitNotSentence(UnarySentence s) {
		Set symbolsCollected = (Set) symbols;
		if (s.getNegated() instanceof Symbol) {
			negatedSymbols.add(s.getNegated());

		}
		symbolsCollected = (Set) s.getNegated().visit(this);
		return null;
	}

	public Object visitMultiSentence(MultiSentence s) {
		Set symbolsCollected = (Set) symbols;
		List l = s.getSentences();
		for (int i = 0; i < l.size(); i++) {
			Sentence sentence = null;
			try {
				sentence = ((Sentence) l.get(i));
				if (sentence instanceof Symbol) {
					positiveSymbols.add(sentence);
				}
				symbolsCollected = (Set) sentence.visit(this);
			} catch (ClassCastException e) {
				// TODO Auto-generated catch block
				System.out.println(l.get(i).getClass());
				e.printStackTrace();
			}

		}
		return null;
	}

	public void collectSymbolsFrom(Sentence s) {
		symbols = new HashSet();
		positiveSymbols = new HashSet();
		negatedSymbols = new HashSet();
		s.visit(this);
	}

	public Set getPositiveSymbols() {
		if ((positiveSymbols.size() == 0) && (negatedSymbols.size() == 0)
				&& (symbols.size() != 0)) {
			positiveSymbols = symbols;
		}
		return positiveSymbols;
	}

}