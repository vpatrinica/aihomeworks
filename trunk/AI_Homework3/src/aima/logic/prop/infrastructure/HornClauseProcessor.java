/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import java.util.HashSet;
import java.util.Set;

public class HornClauseProcessor implements PLVisitor {
	Set premises, facts, heads, clauses;

	public HornClauseProcessor() {
		premises = new HashSet();
		facts = new HashSet();
		heads = new HashSet();
		clauses = new HashSet();
	}

	public Set getClauses() {
		return clauses;
	}

	public Set getFacts() {
		return facts;
	}

	public Set getHeads() {
		return heads;
	}

	public Set getPremises() {
		return premises;
	}

	public Object visitSymbol(Symbol s) {
		facts.add(s);
		return null;
	}

	public Object visitTrueSentence(TrueSentence ts) {
		return null;
	}

	public Object visitFalseSentence(FalseSentence fs) {
		return null;
	}

	public Object visitNotSentence(UnarySentence ns) {
		premises.add(ns.getNegated());
		return null;
	}

	public Object visitBinarySentence(BinarySentence bs) {
		if (bs.getOperator().equals("=>")) {
			Sentence premise = bs.getFirst();
			premises.add(premise);
			premise.visit(this);
			Sentence head = bs.getSecond();
			heads.add(head);
		}

		return null;
	}

	public void processHornClause(Sentence hornClause) {
		premises = new HashSet();
		facts = new HashSet();
		heads = new HashSet();
		clauses = new HashSet();
		hornClause.visit(this);
	}

	public Object visitMultiSentence(MultiSentence fs) {
		return null;
	}
}