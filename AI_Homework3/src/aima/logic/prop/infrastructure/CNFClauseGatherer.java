/*
 * Created on Sep 16, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class CNFClauseGatherer implements PLVisitor {
	List clauses = new ArrayList();

	public Object visitSymbol(Symbol s) {
		return null;
	}

	public Object visitTrueSentence(TrueSentence ts) {
		return null;
	}

	public Object visitFalseSentence(FalseSentence fs) {
		return null;
	}

	public Object visitNotSentence(UnarySentence fs) {
		return null;
	}

	public Object visitBinarySentence(BinarySentence bs) {
		if (bs.isAndSentence()) {
			Sentence first = bs.getFirst();
			Sentence second = bs.getSecond();
			extractClauses(first);
			extractClauses(second);
		}
		return null;
	}

	private void extractClauses(Sentence sentence) {
		if (!(sentence instanceof BinarySentence)) {
			addToClauses(sentence);
		} else {
			extractSentenceFromBinarySentence(sentence);
		}
	}

	private void addToClauses(Sentence sentence) {
		if (!(clauses.contains(sentence))) {
			clauses.add(sentence);
		}
	}

	private void extractSentenceFromBinarySentence(Sentence sentence) {
		BinarySentence bs = (BinarySentence) sentence;
		if (((bs).isAndSentence())) {
			extractClauses((bs).getFirst());
			extractClauses((bs).getSecond());
		} else {
			addToClauses(sentence);
		}
	}

	public Object visitMultiSentence(MultiSentence fs) {
		return null;
	}

	public List getClauses(Sentence cnfSentence) {
		cnfSentence.visit(this);
		return clauses;
	}
}