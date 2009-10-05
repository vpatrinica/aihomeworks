/*
 * Created on Sep 22, 2004
 *
 */
package aima.logic.fol;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aima.logic.fol.parsing.FOLParser;
import aima.logic.fol.parsing.ast.ConnectedSentence;
import aima.logic.fol.parsing.ast.Predicate;
import aima.logic.fol.parsing.ast.Sentence;
import aima.util.LogicUtils;

/**
 * @author Ravi Mohan
 *  
 */
public class Rule {

	private ConnectedSentence original;

	private List clauses;

	Predicate conclusion;

	private FOLParser parser;

	private List variables;

	public Rule(Sentence sentence, FOLParser parser) {
		this.original = (ConnectedSentence) sentence;
		this.parser = parser;
		this.variables = new VariableCollector(parser)
				.getAllVariableNames(sentence);
		clauses = new ArrayList();
		conclusion = (Predicate) original.getSecond();
		List predicates = breakIntoPredicates(original);
		for (int i = 0; i < predicates.size(); i++) {
			Predicate predicate = (Predicate) predicates.get(i);

			if (!(predicate.equals(conclusion))) {
				Clause clause = new Clause(predicate, parser);
				clauses.add(clause);
			}

		}

	}

	private List breakIntoPredicates(ConnectedSentence sentence) {
		return new PredicateCollector().getPredicates(sentence);
	}

	public int numClauses() {
		return clauses.size();
	}

	public Clause clause(int i) {
		return (Clause) clauses.get(i);
	}

	public Predicate conclusion() {
		return conclusion;
	}

	public void initializeAllClauseDomainsFrom(List facts) {
		for (int i = 0; i < clauses.size(); i++) {
			Clause c = (Clause) clauses.get(i);
			for (int j = 0; j < facts.size(); j++) {
				Fact fact = (Fact) facts.get(j);
				c.populateDomainsFrom(fact);
			}
		}

	}

	public List clausesContaining(String variable) {
		List containingClauses = new ArrayList();

		for (int i = 0; i < clauses.size(); i++) {
			Clause c = (Clause) clauses.get(i);
			if (c.contains(variable)) {
				containingClauses.add(c);
			}
		}
		return containingClauses;
	}

	public boolean triggerable() {

		for (int i = 0; i < variables.size(); i++) {
			String variable = (String) variables.get(0);
			boolean occursCheck = variableHasBindingsInContainingClauses(variable);
			boolean atLeastOneCommonValue = variableHasAtLeastOneCommonValue(variable);
			if ((!(occursCheck)) && (!(atLeastOneCommonValue))) {
				return false;
			}
		}
		return true;
	}

	private boolean variableHasAtLeastOneCommonValue(String variable) {
		List containingClauses = clausesContaining(variable);
		return (!(commonValuesInContainingClauses(variable).size() == 0));
	}

	public List commonValuesInContainingClauses(String variable) {
		Set valueSet = null;
		List containingClauses = clausesContaining(variable);
		for (int i = 0; i < containingClauses.size(); i++) {
			Clause clause = (Clause) containingClauses.get(i);
			if (!(clause.valuesFor(variable).isEmpty())) {
				Set set = LogicUtils.listToSet(clause.valuesFor(variable));
				if (valueSet == null) {
					valueSet = set;
				} else {
					valueSet = LogicUtils.intersection(valueSet, set);
				}
			}
		}
		if (valueSet == null) {
			return new ArrayList();
		} else {
			return LogicUtils.setToList(valueSet);
		}

	}

	private boolean variableHasBindingsInContainingClauses(String variable) {

		List containingClauses = clausesContaining(variable);
		for (int i = 0; i < containingClauses.size(); i++) {
			Clause clause = (Clause) containingClauses.get(i);
			if (!clause.hasValueFor(variable)) {
				return false;
			}
		}
		return true;
	}
}