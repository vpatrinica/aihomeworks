/*
 * Created on Sep 16, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import aima.logic.prop.infrastructure.BinarySentence;
import aima.logic.prop.infrastructure.CNFClauseGatherer;
import aima.logic.prop.infrastructure.CNFTransformer;
import aima.logic.prop.infrastructure.MultiSentence;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;
import aima.logic.prop.infrastructure.Symbol;
import aima.logic.prop.infrastructure.SymbolCollector;
import aima.logic.prop.infrastructure.UnarySentence;
import aima.util.LogicUtils;

/* Figure 7.12 in AIMA 2nd Edition */
public class PLResolution {
	public boolean plResolution(String knowledgeBase, String alpha) {
		PEParser parser = new PEParser();
		Sentence kb = (Sentence) parser.parse(knowledgeBase);
		Sentence a = (Sentence) parser.parse(alpha);
		return plResolution(kb, a);
	}

	private boolean plResolution(Sentence kb, Sentence alpha) {
		//convert (kb AND alpha) to CNF
		Sentence kbAndAlphaCNF = new CNFTransformer().toCNF(new BinarySentence(
				"AND", kb, new UnarySentence(alpha)));
		//System.out.println("CNF representation = " + kbAndAlphaCNF);
		//get CNF clauses
		List clausesList = new CNFClauseGatherer().getClauses(kbAndAlphaCNF);
		Set clausesSet = LogicUtils.listToSet(clausesList);

		Set newSet = new HashSet();
		while (true) {
			List pairs = getCombinationPairs(clausesList);
			for (int i = 0; i < pairs.size(); i++) {
				List pair = (List) pairs.get(i);
				if (neitherClauseHasComplimentaryLiterals(pair)) {
					List resolvents = plResolve(pair);
					if (LogicUtils.in(resolvents, new Symbol("null"))) {
						return true;
					}
					newSet = LogicUtils.union(newSet, LogicUtils
							.listToSet(resolvents));
				}

			}
			if (LogicUtils.intersection(newSet, clausesSet).size() == newSet
					.size()) {
				return false;
			}
			clausesSet = LogicUtils.union(clausesSet, newSet);
			clausesList = LogicUtils.setToList(clausesSet);
		}

	}

	private List plResolve(List pairOfClauses) {
		ArrayList retVal = new ArrayList();
		Sentence clause1 = (Sentence) pairOfClauses.get(0);
		Sentence clause2 = (Sentence) pairOfClauses.get(1);
		SymbolCollector sc = new SymbolCollector();

		sc.collectSymbolsFrom(clause1);
		Set clause1PositiveSymbols = sc.getPositiveSymbols();
		Set clause1NegatedSymbols = sc.getNegatedSymbols();
		Set clause1AllSymbols = sc.getSymbols();

		sc.collectSymbolsFrom(clause2);
		Set clause2PositiveSymbols = sc.getPositiveSymbols();
		Set clause2NegatedSymbols = sc.getNegatedSymbols();
		Set clause2AllSymbols = sc.getSymbols();

		Set clause1MatchedPositiveSymbols = LogicUtils.intersection(
				clause1PositiveSymbols, clause2NegatedSymbols);
		Set clause1MatchedNegativeSymbols = LogicUtils.intersection(
				clause1NegatedSymbols, clause2PositiveSymbols);

		Set clause1MatchedSymbols = LogicUtils.union(
				clause1MatchedPositiveSymbols, clause1MatchedNegativeSymbols);

		Set clause1UnmatchedPositiveSymbols = LogicUtils.difference(
				clause1PositiveSymbols, clause2NegatedSymbols);
		Set clause2UnmatchedPositiveSymbols = LogicUtils.difference(
				clause2PositiveSymbols, clause1NegatedSymbols);
		Set clause1and2UnmatchedPositiveSymbols = LogicUtils.union(
				clause1UnmatchedPositiveSymbols,
				clause2UnmatchedPositiveSymbols);

		Set clause1UnmatchedNegativeSymbols = LogicUtils.difference(
				clause1NegatedSymbols, clause2PositiveSymbols);
		Set clause2UnmatchedNegativeSymbols = LogicUtils.difference(
				clause2NegatedSymbols, clause1PositiveSymbols);
		Set clause1and2UnmatchedNegativeSymbols = LogicUtils.union(
				clause1UnmatchedNegativeSymbols,
				clause2UnmatchedNegativeSymbols);

		boolean noUnmatchedSymbols = ((clause1and2UnmatchedPositiveSymbols
				.size() == 0) && (clause1and2UnmatchedNegativeSymbols).size() == 0);
		boolean exactlyOneMatchedSymbol = (xor((clause1MatchedPositiveSymbols
				.size() == 1), (clause1MatchedNegativeSymbols.size() == 1)))
				&& (clause1AllSymbols.size() == 1)
				&& (clause2AllSymbols.size() == 1);
		if (exactlyOneMatchedSymbol) {
			retVal.add(new Symbol("null"));
			//System.out.print("Resolving " + clause1 + " + " + clause2 + " to
			// give ");
			//System.out.println("Null Clause");
		} else { //more than one matchedSymbol
			//remove one matched symbol from clause1 and clause 2
			Set positiveSymbolsToResolve = LogicUtils.union(
					clause1PositiveSymbols, clause2PositiveSymbols);
			Set negativeSymbolsToResolve = LogicUtils.union(
					clause1NegatedSymbols, clause2NegatedSymbols);
			if (clause1MatchedSymbols.size() != 0) {
				Symbol toRemove = (Symbol) clause1MatchedSymbols.iterator()
						.next();
				Set remove = new HashSet();
				remove.add(toRemove);
				positiveSymbolsToResolve = LogicUtils.difference(
						positiveSymbolsToResolve, remove);
				negativeSymbolsToResolve = LogicUtils.difference(
						negativeSymbolsToResolve, remove);
				retVal.add(createResolventExpression(positiveSymbolsToResolve,
						negativeSymbolsToResolve));
			}
		}
		return retVal;
	}

	private Sentence createResolventExpression(Set positiveSymbols,
			Set negativeSymbols) {

		Set symbols = new HashSet();
		boolean symbolSelected = false;
		Iterator i = positiveSymbols.iterator();
		while (i.hasNext()) {
			symbols.add(i.next());
		}
		i = negativeSymbols.iterator();
		while (i.hasNext()) {
			symbols.add(new UnarySentence((Sentence) i.next()));
		}
		if (symbols.size() == 0) {
			return null;
		}
		if (symbols.size() == 1) {
			Iterator iter = symbols.iterator();
			Object obj = iter.next();
			if (obj instanceof Symbol) {
				return (Symbol) obj;
			} else {

				return (UnarySentence) obj;
			}
		}
		if (symbols.size() == 2) {
			Iterator iter = symbols.iterator();
			Object obj1 = iter.next();
			Object obj2 = iter.next();
			return new BinarySentence("OR", (Sentence) obj1, (Sentence) obj2);
		}
		return new MultiSentence("OR", LogicUtils.setToList(symbols));
	}

	private List getCombinationPairs(List clausesList) {
		int odd = clausesList.size() % 2;
		int midpoint = 0;
		if (odd == 1) {
			midpoint = (clausesList.size() / 2) + 1;
		} else {
			midpoint = (clausesList.size() / 2);
		}

		List pairs = new ArrayList();
		for (int i = 0; i < clausesList.size(); i++) {
			for (int j = i; j < clausesList.size(); j++) {
				List pair = new ArrayList();
				Object first = clausesList.get(i);
				Object second = clausesList.get(j);

				if (!(first.equals(second))) {
					pair.add(first);
					pair.add(second);
					pairs.add(pair);
				}
			}
		}
		return pairs;
	}

	public boolean xor(boolean one, boolean two) {
		return (one == true) ? ((two == false) ? true : false)
				: ((two == true) ? true : false);

	}

	private boolean neitherClauseHasComplimentaryLiterals(List pairOfClauses) {
		ArrayList retVal = new ArrayList();
		Sentence clause1 = (Sentence) pairOfClauses.get(0);
		Sentence clause2 = (Sentence) pairOfClauses.get(1);
		SymbolCollector sc = new SymbolCollector();

		sc.collectSymbolsFrom(clause1);
		Set clause1PositiveSymbols = sc.getPositiveSymbols();
		Set clause1NegatedSymbols = sc.getNegatedSymbols();
		Set clause1AllSymbols = sc.getSymbols();

		sc.collectSymbolsFrom(clause2);
		Set clause2PositiveSymbols = sc.getPositiveSymbols();
		Set clause2NegatedSymbols = sc.getNegatedSymbols();
		Set clause2AllSymbols = sc.getSymbols();
		return (((LogicUtils.intersection(clause1PositiveSymbols,
				clause1NegatedSymbols)).size() == 0) && ((LogicUtils
				.intersection(clause2PositiveSymbols, clause2NegatedSymbols))
				.size() == 0));
	}

}