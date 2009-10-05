/*
 * Created on Sep 25, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.algorithms;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import aima.logic.prop.infrastructure.CNFClauseGatherer;
import aima.logic.prop.infrastructure.CNFTransformer;
import aima.logic.prop.infrastructure.Model;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;
import aima.logic.prop.infrastructure.Symbol;
import aima.logic.prop.infrastructure.SymbolCollector;
import aima.util.LogicUtils;

/* Figure 7.17 in AIMA 2nd Edition */
public class WalkSAT {
	private Model myModel;

	private Random random = new Random();

	public Model findModelFor(String logicalSentence, int numberOfFlips,
			double probabilityOfRandomWalk) {
		myModel = new Model();
		Sentence s = (Sentence) new PEParser().parse(logicalSentence);
		CNFTransformer transformer = new CNFTransformer();
		CNFClauseGatherer clauseGatherer = new CNFClauseGatherer();
		SymbolCollector sc = new SymbolCollector();
		sc.collectSymbolsFrom(s);
		List symbols = LogicUtils.setToList(sc.getSymbols());
		Random r = new Random();
		for (int i = 0; i < symbols.size(); i++) {
			Symbol sym = (Symbol) symbols.get(i);
			myModel = myModel.extend(sym, LogicUtils.randomBoolean());
		}
		List clauses = clauseGatherer.getClauses(transformer.toCNF(s));

		for (int i = 0; i < numberOfFlips; i++) {
			if (getNumberOfClausesSatisfiedIn(LogicUtils.listToSet(clauses),
					myModel) == clauses.size()) {
				return myModel;
			}
			Sentence clause = (Sentence) clauses.get(random.nextInt(clauses
					.size()));
			sc.collectSymbolsFrom(clause);
			List symbolsInClause = LogicUtils.setToList(sc.getSymbols());
			if (random.nextDouble() >= probabilityOfRandomWalk) {
				Symbol randomSymbol = (Symbol) symbolsInClause.get(random
						.nextInt(symbolsInClause.size()));
				myModel = myModel.flip(randomSymbol);
			} else {
				Symbol symbolToFlip = getSymbolWhoseFlipMaximisesSatisfiedClauses(
						LogicUtils.listToSet(clauses), symbolsInClause, myModel);
				myModel = myModel.flip(symbolToFlip);
			}

		}
		return null;
	}

	private Symbol getSymbolWhoseFlipMaximisesSatisfiedClauses(Set clauses,
			List symbols, Model model) {
		if (symbols.size() > 0) {
			Symbol retVal = (Symbol) symbols.get(0);
			int maxClausesSatisfied = 0;
			for (int i = 0; i < symbols.size(); i++) {
				Symbol sym = (Symbol) symbols.get(i);
				if (getNumberOfClausesSatisfiedIn(clauses, model.flip(sym)) > maxClausesSatisfied) {
					retVal = sym;
					maxClausesSatisfied = getNumberOfClausesSatisfiedIn(
							clauses, model.flip(sym));
				}
			}
			return retVal;
		} else {
			return null;
		}

	}

	private List extractSymbols(Sentence sentence) {
		SymbolCollector sc = new SymbolCollector();
		sc.collectSymbolsFrom(sentence);
		return LogicUtils.setToList(sc.getSymbols());
	}

	private int getNumberOfClausesSatisfiedIn(Set clauses, Model model) {
		int retVal = 0;
		Iterator i = clauses.iterator();
		while (i.hasNext()) {
			Sentence s = (Sentence) i.next();
			if (model.isClauseTrue(s)) {
				retVal += 1;
			}
		}
		return retVal;
	}
}