/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.algorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import aima.logic.prop.infrastructure.CNFClauseGatherer;
import aima.logic.prop.infrastructure.HornClauseProcessor;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;
import aima.logic.prop.infrastructure.Symbol;
import aima.logic.prop.infrastructure.SymbolCollector;

/* Figure 7.14 in AIMA 2nd Edition */
public class PLFCEntails {
	List clauses;

	Hashtable inferred, count;

	List agenda;

	public PLFCEntails() {
		clauses = new ArrayList();
		agenda = new ArrayList();
		inferred = new Hashtable();
		count = new Hashtable();
	}

	public boolean entails(String knowledgeBase, String query) {
		PEParser p = new PEParser();
		Sentence kb = (Sentence) p.parse(knowledgeBase);
		Sentence q = (Sentence) p.parse(query);
		return entails(kb, q);

	}

	private boolean entails(Sentence knowledgeBase, Sentence query) {
		initialize(knowledgeBase, query);
		while (agenda.size() != 0) {
			Symbol p = (Symbol) agenda.remove(0);
			while (!(((Boolean) inferred.get(p)).booleanValue())) {
				inferred.put(p, new Boolean(true));
				for (int i = 0; i < clauses.size(); i++) {
					Sentence clause = (Sentence) clauses.get(i);
					if (appearsInPremise(clause, p)) {
						int clauseCount = ((Integer) count.get(clause))
								.intValue();
						clauseCount -= 1;
						count.put(clause, new Integer((clauseCount)));
						if (clauseCount == 0) {
							HornClauseProcessor hcp = new HornClauseProcessor();
							hcp.processHornClause(clause);
							Symbol head = (Symbol) hcp.getHeads().iterator()
									.next();
							if (head.equals(query)) {
								return true;
							}
							agenda.add(0, head);
						}

					}
				}

			}
		}
		return false;
	}

	public void initialize(Sentence knowledgeBase, Sentence query) {
		CNFClauseGatherer cgath = new CNFClauseGatherer();
		clauses = cgath.getClauses(knowledgeBase);
		HornClauseProcessor hcp = new HornClauseProcessor();

		for (int i = 0; i < clauses.size(); i++) {
			Sentence clause = (Sentence) clauses.get(i);

			hcp.processHornClause(knowledgeBase);
			SymbolCollector sc = new SymbolCollector();
			sc.collectSymbolsFrom(clause);
			Set allSymbols = sc.getSymbols();
			if (allSymbols.size() == 1) { //fact
				agenda.add(allSymbols.iterator().next());
				count.put(clause, new Integer(0));
			}
			Set negatives = sc.getNegatedSymbols();
			hcp.processHornClause(clause);
			Set premises = hcp.getPremises();
			Iterator iter = premises.iterator();
			while (iter.hasNext()) {
				sc.collectSymbolsFrom((Sentence) iter.next());
				Set premiseSymbols = sc.getSymbols();
				count.put(clause, new Integer(premiseSymbols.size()));
			}

			iter = allSymbols.iterator();
			while (iter.hasNext()) {
				Symbol sym = (Symbol) iter.next();
				if (inferred.get(sym) == null) {
					inferred.put(sym, Boolean.FALSE);
				}
			}
			Set positives = sc.getPositiveSymbols();

		}

	}

	public boolean appearsInPremise(Sentence hornClause, Symbol sym) {
		boolean retVal = false;
		HornClauseProcessor hcp = new HornClauseProcessor();
		hcp.processHornClause(hornClause);
		Set premises = hcp.getPremises();
		Iterator i = premises.iterator();
		while (i.hasNext()) {
			Sentence premise = (Sentence) i.next();
			SymbolCollector sc = new SymbolCollector();
			sc.collectSymbolsFrom(premise);
			Set premiseSymbols = sc.getSymbols();
			Iterator symIter = premiseSymbols.iterator();
			while (symIter.hasNext()) {
				Symbol asym = (Symbol) symIter.next();
				if (asym.equals(sym)) {
					retVal = true;
					break;
				}
			}
		}
		return retVal;
	}

}