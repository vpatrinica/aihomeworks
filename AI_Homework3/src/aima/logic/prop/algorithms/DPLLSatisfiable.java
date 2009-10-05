/*
 * Created on Sep 25, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import aima.logic.prop.infrastructure.CNFClauseGatherer;
import aima.logic.prop.infrastructure.CNFTransformer;
import aima.logic.prop.infrastructure.Model;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;
import aima.logic.prop.infrastructure.SentenceEvaluator;
import aima.logic.prop.infrastructure.Symbol;
import aima.logic.prop.infrastructure.SymbolCollector;
import aima.util.LogicUtils;

/* Figure 7.16 in AIMA 2nd Edition */

public class DPLLSatisfiable {
	Set clauses;

	List symbols;

	public DPLLSatisfiable() {
		clauses = new HashSet();
		symbols = new ArrayList();
	}

	public boolean isSatisfiable(String propositionalSentence) {
		initialize(propositionalSentence);

		return dpll(clauses, symbols, new Model());
	}

	public boolean dpll(Set clauses, List symbols, Model model) {
		if (checkIfAllClausesTrue(clauses, model)) {
			return true;
		}
		if (checkIfSomeClauseIsFalse(clauses, model)) {
			return false;
		}
		SymbolValuePair pureSVP = findPureSymbol(symbols, clauses, model);
		if (!(pureSVP == null)) {
			return dpll(clauses, symbols, model.extend(pureSVP.getSymbol(),
					pureSVP.getValue()));
		}
		SymbolValuePair unitClauseSVP = findUnitClause(symbols, clauses, model);
		if (!(unitClauseSVP == null)) {
			return dpll(clauses, symbols, model.extend(unitClauseSVP
					.getSymbol(), unitClauseSVP.getValue()));
		}
		Symbol p = (Symbol) LogicUtils.first(symbols);
		List rest = LogicUtils.rest(symbols);
		return (dpll(clauses, rest, model.extend(p, true)) || dpll(clauses,
				rest, model.extend(p, false)));
	}

	private void initialize(String propositionalSentence) {
		clauses = new HashSet();
		symbols = new ArrayList();
		CNFTransformer cnft = new CNFTransformer();
		Sentence s_cnf = cnft.toCNF((Sentence) new PEParser()
				.parse(propositionalSentence));
		CNFClauseGatherer cnfcg = new CNFClauseGatherer();
		cnfcg.getClauses(s_cnf);
		clauses = LogicUtils.listToSet(cnfcg.getClauses(s_cnf));
		symbols = extractSymbols(s_cnf);
	}

	private List extractSymbols(Sentence s_cnf) {
		SymbolCollector sc = new SymbolCollector();
		sc.collectSymbolsFrom(s_cnf);
		return LogicUtils.setToList(sc.getSymbols());
	}

	private Set extractPositiveSymbols(Sentence s_cnf) {
		SymbolCollector sc = new SymbolCollector();
		sc.collectSymbolsFrom(s_cnf);
		return sc.getPositiveSymbols();
	}

	private Set extractNegativeSymbols(Sentence s_cnf) {
		SymbolCollector sc = new SymbolCollector();
		sc.collectSymbolsFrom(s_cnf);
		return sc.getNegatedSymbols();
	}

	private boolean checkIfAllClausesTrue(Set clauses, Model model) {
		boolean retVal = true;
		Iterator i = clauses.iterator();
		while (i.hasNext()) {
			Sentence clause = (Sentence) i.next();
			if (!(isClauseTrueInModel(clause, model))) {
				retVal = false;
				break;
			}
		}
		return retVal;

	}

	private boolean checkIfSomeClauseIsFalse(Set clauses, Model model) {
		boolean retVal = false;
		Iterator i = clauses.iterator();
		while (i.hasNext()) {
			Sentence clause = (Sentence) i.next();
			if ((isClauseFalseInModel(clause, model))) {
				retVal = true;
				break;
			}
		}
		return retVal;

	}

	private class SymbolValuePair {
		private Symbol symbol;

		private boolean value;

		public Symbol getSymbol() {
			return symbol;
		}

		public boolean getValue() {
			return value;
		}

		SymbolValuePair(Symbol s, boolean b) {
			symbol = s;
			value = b;
		}
	}

	private SymbolValuePair findPureSymbol(List Symbols, Set clauses, Model m) {

		Iterator clauseIterator = clauses.iterator();
		Set allPositiveSymbols = new HashSet();
		Set allNegativeSymbols = new HashSet();
		while (clauseIterator.hasNext()) {
			Sentence s = (Sentence) clauseIterator.next();
			allPositiveSymbols = LogicUtils.union(allPositiveSymbols,
					(extractPositiveSymbols(s)));
			allNegativeSymbols = LogicUtils.union(allNegativeSymbols,
					(extractNegativeSymbols(s)));
		}

		clauseIterator = clauses.iterator();
		Set pureSymbols = new HashSet();
		while (clauseIterator.hasNext()) {
			Sentence s = (Sentence) clauseIterator.next();
			if (!(isClauseTrueInModel(s, m))) {
				List clauseSymbols = extractSymbols(s);
				for (int i = 0; i < clauseSymbols.size(); i++) {
					Symbol sym = (Symbol) clauseSymbols.get(i);
					if ((allPositiveSymbols.contains(sym))
							&& (!(allNegativeSymbols.contains(sym)))) {
						return new SymbolValuePair(sym, true);
					}
					if ((allNegativeSymbols.contains(sym))
							&& (!(allPositiveSymbols.contains(sym)))) {
						return new SymbolValuePair(sym, false);
					}
				}
			}
		}
		return null;
	}

	private SymbolValuePair findUnitClause(List Symbols, Set clauses, Model m) {
		SymbolValuePair retVal = null;
		Iterator clauseIterator = clauses.iterator();
		while (clauseIterator.hasNext()) {
			Sentence s = (Sentence) clauseIterator.next();
			Set clauseSymbols = LogicUtils.listToSet(extractSymbols(s));
			Set clausePositiveSymbols = extractPositiveSymbols(s);
			Set clauseNegativeSymbols = extractNegativeSymbols(s);
			if (clauseSymbols.size() == 1) {
				Symbol sym = (Symbol) clauseSymbols.iterator().next();
				if (clausePositiveSymbols.contains(sym)) {
					return new SymbolValuePair(sym, true);
				}
				if (clauseNegativeSymbols.contains(sym)) {
					return new SymbolValuePair(sym, false);
				}
			} else {
				Set symbolsNotAssignedFalseInModel = new HashSet();
				Iterator iter = clauseSymbols.iterator();
				while (iter.hasNext()) {
					Symbol sym = (Symbol) iter.next();
					Object symValue = m.getStatus(sym);
					if (symValue != null) {
						if (((Boolean) symValue).booleanValue() == true) {
							symbolsNotAssignedFalseInModel.add(sym);
						}

					} else {
						symbolsNotAssignedFalseInModel.add(sym);
					}
				}
				if (symbolsNotAssignedFalseInModel.size() == 1) {
					Symbol sym = (Symbol) symbolsNotAssignedFalseInModel
							.iterator().next();
					if (isClauseTrueInModel(s, m.extend(sym, true))) {
						return new SymbolValuePair(sym, true);
					}
					if (isClauseTrueInModel(s, m.extend(sym, false))) {
						return new SymbolValuePair(sym, false);
					}
				}

			}
		}
		return null;

	}

	private boolean isClauseTrueInModel(Sentence clause, Model model) {
		SentenceEvaluator sEval = new SentenceEvaluator(model);
		Object result = sEval.evaluate(clause, model);
		return (result == null) ? false : ((Boolean) result).booleanValue();

	}

	private boolean isClauseFalseInModel(Sentence clause, Model model) {
		SentenceEvaluator sEval = new SentenceEvaluator(model);
		Object result = sEval.evaluate(clause, model);
		return (result == null) ? false : (!((Boolean) result).booleanValue());

	}

}