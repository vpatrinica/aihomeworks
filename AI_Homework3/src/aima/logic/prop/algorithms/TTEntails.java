/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.algorithms;

import java.util.List;
import java.util.Set;

import aima.logic.prop.infrastructure.Model;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;
import aima.logic.prop.infrastructure.SentenceEvaluator;
import aima.logic.prop.infrastructure.Symbol;
import aima.logic.prop.infrastructure.SymbolCollector;
import aima.util.LogicUtils;

/* Figure 7.10 in AIMA 2nd Edition */
public class TTEntails {
	public boolean entails(String knowledgeBase, String alpha) {
		PEParser parser = new PEParser();
		Sentence kb = (Sentence) parser.parse(knowledgeBase);
		Sentence a = (Sentence) parser.parse(alpha);
		return entails(kb, a);

	}

	private boolean entails(Sentence knowledgeBase, Sentence alpha) {
		List symbols = getSymbolsFrom(knowledgeBase, alpha);
		return checkAll(knowledgeBase, alpha, symbols, new Model())
				.booleanValue();
	}

	private Boolean checkAll(Sentence knowledgeBase, Sentence alpha,
			List symbols, Model model) {
		if (symbols.size() == 0) {
			return handleNoMoreSymbols(knowledgeBase, alpha, model);
		} else {
			Symbol p = (Symbol) LogicUtils.first(symbols);
			List rest = LogicUtils.rest(symbols);
			return new Boolean((checkAll(knowledgeBase, alpha, rest, model
					.extend(p, true))).booleanValue()
					&& (checkAll(knowledgeBase, alpha, rest, model.extend(p,
							false))).booleanValue());
		}
	}

	private Boolean handleNoMoreSymbols(Sentence knowledgeBase, Sentence alpha,
			Model model) {
		Object knowledgeBaseValue = plTrue(knowledgeBase, model);

		if ((knowledgeBaseValue != null)
				&& (knowledgeBaseValue.equals(Boolean.TRUE))) {
			//System.out.println("KB is true");
			Object alphaValue = plTrue(alpha, model);
			if (alphaValue == null) {
				return Boolean.FALSE;
			} else {
				return (Boolean) alphaValue;
			}
		} else {
			return Boolean.TRUE;
		}
	}

	private List getSymbolsFrom(Sentence knowledgeBase, Sentence alpha) {
		SymbolCollector sc = new SymbolCollector();
		sc.collectSymbolsFrom(knowledgeBase);
		Set knowledgBaseSymbols = sc.getSymbols();
		sc.collectSymbolsFrom(alpha);
		Set alphaSymbols = sc.getSymbols();
		return LogicUtils.setToList(LogicUtils.union(knowledgBaseSymbols,
				alphaSymbols));
	}

	private Object plTrue(Sentence s, Model m) {
		SentenceEvaluator se = new SentenceEvaluator(m);
		return se.evaluate(s, m);

	}

}