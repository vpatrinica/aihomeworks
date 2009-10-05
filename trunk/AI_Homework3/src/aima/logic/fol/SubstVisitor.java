/*
 * Created on Sep 20, 2004
 *
 */
package aima.logic.fol;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import aima.logic.fol.parsing.AbstractFOLVisitor;
import aima.logic.fol.parsing.FOLParser;
import aima.logic.fol.parsing.ast.QuantifiedSentence;
import aima.logic.fol.parsing.ast.Sentence;
import aima.logic.fol.parsing.ast.Variable;
import aima.util.LogicUtils;

/**
 * @author Ravi Mohan
 *  
 */
public class SubstVisitor extends AbstractFOLVisitor {
	Sentence substitutedSentence = null;

	Sentence originalSentence = null;

	private FOLParser parser;

	public SubstVisitor(FOLParser parser) {
		super(parser);
	}

	public Object visitVariable(Variable variable, Object arg) {
		String value = variable.getValue();
		Properties substs = (Properties) arg;
		if (substs.keySet().contains(value)) {
			String key = variable.getValue();
			return new Variable(substs.getProperty(key));
		}
		return variable;

	}

	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		Properties props = (Properties) arg;
		Sentence quantified = sentence.getQuantified();
		Sentence quantifiedAfterSubs = (Sentence) quantified.accept(this, arg);
		Set sentenceVariables = LogicUtils.listToSet(sentence
				.getVariablesAsString());
		Set unmatchedVariables = LogicUtils.difference(sentenceVariables, props
				.keySet());
		//		System.out.println("senArs = "+sentenceVariables);
		//		System.out.println("props = "+props.keySet());
		//		System.out.println("umatched = "+unmatchedVariables+"\n");

		if (!(unmatchedVariables.isEmpty())) {
			List variables = LogicUtils.setToList(unmatchedVariables);
			QuantifiedSentence sen = new QuantifiedSentence(sentence
					.getQuantifier(), variables, quantifiedAfterSubs);
			//System.out.println(sen);
			return sen;
		} else {
			return recreate(quantifiedAfterSubs);
		}

	}

	public Sentence getSubstitutedSentence(Sentence beforeSubst, Properties p) {
		return recreate(beforeSubst.accept(this, p));

	}

}