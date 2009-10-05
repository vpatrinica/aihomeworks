/*
 * Created on Sep 18, 2004
 *
 */
package aima.logic.fol;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ravi Mohan
 *  
 */
public class FOLDomain {
	private Set constants, functions, predicates;

	public FOLDomain(Set constants, Set functions, Set predicates) {
		this.constants = constants;
		this.functions = functions;
		this.predicates = predicates;
	}

	public FOLDomain() {
		this.constants = new HashSet();
		this.functions = new HashSet();
		this.predicates = new HashSet();
	}

	public Set getConstants() {
		return constants;
	}

	public Set getFunctions() {
		return functions;
	}

	public Set getPredicates() {
		return predicates;
	}

	public void addConstant(String constant) {
		constants.add(constant);
	}

	public void addFunction(String function) {
		functions.add(function);
	}

	public void addPredicate(String predicate) {
		predicates.add(predicate);
	}
}