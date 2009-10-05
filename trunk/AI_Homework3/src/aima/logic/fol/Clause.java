/*
 * Created on Sep 22, 2004
 *
 */
package aima.logic.fol;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import aima.logic.fol.parsing.FOLParser;
import aima.logic.fol.parsing.ast.Predicate;
import aima.logic.fol.parsing.ast.Variable;
import aima.search.csp.Domains;

/**
 * @author Ravi Mohan
 *  
 */
public class Clause {

	private Predicate predicate;

	private Domains domain;

	private FOLParser parser;

	List variableNames;

	public Clause(Predicate predicate, FOLParser parser) {

		this.predicate = predicate;
		this.parser = parser;
		variableNames = new VariableCollector(parser)
				.getAllVariableNames(predicate);

		domain = new Domains(variableNames);
	}

	public String toString() {
		return predicate.toString();
	}

	public void populateDomainsFrom(Fact fact) {
		Unifier unifier = new Unifier(parser);
		Hashtable result = unifier.unify(predicate, fact.predicate(),
				new Hashtable());
		if (result != null) { //unification succesful
			Iterator iter = result.keySet().iterator();
			while (iter.hasNext()) {
				Variable key = (Variable) iter.next();
				String name = key.getValue();
				domain.addToDomain(name, result.get(key));
			}
		}

	}

	public Domains domain() {

		return domain;
	}

	public boolean contains(String variable) {
		return variableNames.contains(variable);
	}
	public boolean hasValueFor(String variable) {
		return (contains(variable)) && (domain.getDomainOf(variable).size()>0);
	}
	public List valuesFor(String variable) {
		return domain.getDomainOf(variable);
	}
}