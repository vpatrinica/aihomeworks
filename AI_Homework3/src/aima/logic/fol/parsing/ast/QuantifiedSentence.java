/*
 * Created on Sep 18, 2004
 *
 */
package aima.logic.fol.parsing.ast;

import java.util.ArrayList;
import java.util.List;

import aima.logic.fol.parsing.FOLVisitor;

/**
 * @author Ravi Mohan
 *  
 */
public class QuantifiedSentence implements Sentence {
	private String quantifier;
	private List variables;
	private Sentence quantified;

	public QuantifiedSentence(String quantifier, List variables,
			Sentence quantified) {
		this.quantifier = quantifier;
		this.variables = variables;
		this.quantified = quantified;
	}
	public boolean equals(Object o) {
		try {
			QuantifiedSentence cs = (QuantifiedSentence) o;
			return (
				(cs.quantifier.equals(quantifier))
					&& (variables.equals(variables))
					&& (quantified.equals(quantified)));
		} catch (ClassCastException e) {
			return false;
		}
	}
	public String toString() {
		String pre = quantifier + " ";
		for (int i = 0; i < variables.size(); i++) {
			pre = pre + variables.get(i).toString() + " ";
		}
		pre += " ";
		String post = " " + quantified.toString() + "  ";
		return pre + post;
	}

	public Object accept(FOLVisitor v,Object arg) {

		return v.visitQuantifiedSentence(this,arg);

	}
	
	public FOLNode copy(){
		List copyVars = new ArrayList();
		for (int i=0;i<variables.size();i++){
			Variable v = (Variable)variables.get(i);
			copyVars.add(v.copy());
		}
		return new QuantifiedSentence(quantifier,copyVars,(Sentence)quantified.copy());
	}

	public Sentence getQuantified() {
		return quantified;
	}
	public List getVariables() {
		return variables;
	}
	public void setVariables(List variables) {
		this.variables = variables;
	}
	public String getQuantifier() {
		return quantifier;
	}
	public List getVariablesAsString() {
		List ret= new ArrayList();
		for (int i=0;i<variables.size();i++){
			Variable var = (Variable)variables.get(i);
			ret.add(var.getValue());
		}
		return ret;
	}
}