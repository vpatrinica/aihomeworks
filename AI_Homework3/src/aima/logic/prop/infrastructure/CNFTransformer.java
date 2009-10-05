/*
 * Created on Sep 16, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class CNFTransformer implements PLVisitor {

	public Object visitSymbol(Symbol s) {

		return new Symbol(s.getValue());
	}

	public Object visitTrueSentence(TrueSentence s) {
		return new TrueSentence();
	}

	public Object visitFalseSentence(FalseSentence s) {
		return new FalseSentence();
	}

	public Object visitNotSentence(UnarySentence s) {
		if (s.getNegated() instanceof UnarySentence) {
			return ((UnarySentence) s.getNegated()).getNegated();
		} else if (s.getNegated() instanceof BinarySentence) {
			BinarySentence bs = (BinarySentence) s.getNegated();
			if (bs.getOperator().equals("AND")) {
				return new BinarySentence("OR", new UnarySentence((Sentence) bs
						.getFirst().visit(this)), new UnarySentence(
						(Sentence) bs.getSecond().visit(this)));
			} else if (bs.getOperator().equals("OR")) {
				return new BinarySentence(
						"AND",
						new UnarySentence((Sentence) bs.getFirst().visit(this)),
						new UnarySentence((Sentence) bs.getSecond().visit(this)));
			} else {
				throw new RuntimeException(
						"CNF Transformer :- Visiting NOT node and encountered binary sentence other than AND or OR "
								+ bs.getOperator());
			}
		} else if (s.getNegated() instanceof MultiSentence) {
			MultiSentence ms = (MultiSentence) s.getNegated();
			List sentences = ms.getSentences();
			List transformedSentences = new ArrayList();
			for (int i = 0; i < sentences.size(); i++) {
				Sentence sen = (Sentence) sentences.get(i);
				transformedSentences.add(sen.visit(this));
			}
			if (ms.getOperator().equals("OR")) {
				return new MultiSentence("AND", transformedSentences);
			} else if (ms.getOperator().equals("AND")) {
				return new MultiSentence("OR", transformedSentences);
			} else {
				throw new RuntimeException(
						"CNF Transformer :- Visiting MULTI node and encounteredoperator  other than AND or OR "
								+ ms.getOperator());
			}
		} else {
			return new UnarySentence(s.getNegated());
		}
	}

	public Object visitMultiSentence(MultiSentence s) {

		return s;
	}

	public Object visitBinarySentence(BinarySentence s) {
		if (s.isImplication()) {
			return eliminateImplication(s);
		} else if (s.isBiconditional()) {
			return eliminateBiConditional(s);

		} else if (s.isOrSentence()) {
			if (((s.getSecond() instanceof BinarySentence) && ((BinarySentence) s
					.getSecond()).isAndSentence())
					|| ((s.getFirst() instanceof BinarySentence) && ((BinarySentence) s
							.getFirst()).isAndSentence())) {
				return distributeOrOverAnd(s);
			} else {
				return defaultBinarySentenceTransform(s);
			}

		} else {
			return defaultBinarySentenceTransform(s);
		}

	}

	private BinarySentence distributeOrOverAnd(BinarySentence s) {
		BinarySentence andClause = null;
		Sentence other = null;
		if ((s.getSecond() instanceof BinarySentence)
				&& ((BinarySentence) s.getSecond()).isAndSentence()) {
			andClause = (BinarySentence) s.getSecond();
			other = s.getFirst();
		} else {
			andClause = (BinarySentence) s.getFirst();
			other = s.getSecond();
		}
		return new BinarySentence("AND", new BinarySentence("OR", other,
				andClause.getFirst()), new BinarySentence("OR", other,
				andClause.getSecond()));
	}

	private BinarySentence defaultBinarySentenceTransform(BinarySentence s) {
		return new BinarySentence(s.getOperator(), (Sentence) s.getFirst()
				.visit(this), (Sentence) s.getSecond().visit(this));
	}

	private BinarySentence eliminateImplication(BinarySentence s) {
		return new BinarySentence("OR", new UnarySentence((Sentence) s
				.getFirst().visit(this)), (Sentence) s.getSecond().visit(this));
	}

	private BinarySentence eliminateBiConditional(BinarySentence s) {
		return (BinarySentence) new BinarySentence("AND", new BinarySentence(
				"=>", (Sentence) s.getFirst().visit(this), (Sentence) s
						.getSecond().visit(this)), new BinarySentence("=>",
				(Sentence) s.getSecond().visit(this), (Sentence) s.getFirst()
						.visit(this)));
	}

	public Sentence toCNF(Sentence s) {
		Sentence toTransform = s;
		while (!(toTransform.equals(toTransform.visit(this)))) {
			toTransform = (Sentence) toTransform.visit(this);
		}
		return toTransform;
	}
}