/*
 * Created on Sep 24, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

public class SentenceEvaluator implements PLVisitor {
	private Model model;

	public SentenceEvaluator(Model model) {
		this.model = model;
	}

	public Object visitSymbol(Symbol s) {
		return model.getStatus(s);
	}

	public Object visitTrueSentence(TrueSentence ts) {
		return Boolean.TRUE;
	}

	public Object visitFalseSentence(FalseSentence fs) {

		return Boolean.FALSE;
	}

	public Object visitNotSentence(UnarySentence fs) {
		Object negatedValue = fs.getNegated().visit(this);
		if (negatedValue != null) {
			return new Boolean(!((Boolean) negatedValue).booleanValue());
		} else {
			return null;
		}
	}

	public Object visitBinarySentence(BinarySentence bs) {
		Object firstValue = bs.getFirst().visit(this);
		Object secondValue = bs.getSecond().visit(this);
		if ((firstValue == null) || (secondValue == null)) { //strictly not
															 // true for or/and
															 // -FIX later
			return null;
		} else {
			String operator = bs.getOperator();
			if (operator.equals("AND")) {
				return evaluateAnd((Boolean) firstValue, (Boolean) secondValue);
			}
			if (operator.equals("OR")) {
				return evaluateOr((Boolean) firstValue, (Boolean) secondValue);
			}
			if (operator.equals("=>")) {
				return evaluateImplied((Boolean) firstValue,
						(Boolean) secondValue);
			}
			if (operator.equals("<=>")) {
				return evaluateBiConditional((Boolean) firstValue,
						(Boolean) secondValue);
			}
			return null;
		}
	}

	public Boolean evaluateAnd(Boolean firstValue, Boolean secondValue) {
		if ((firstValue.equals(Boolean.TRUE))
				&& (secondValue.equals(Boolean.TRUE))) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Boolean evaluateOr(Boolean firstValue, Boolean secondValue) {
		if ((firstValue.equals(Boolean.TRUE))
				|| (secondValue.equals(Boolean.TRUE))) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Boolean evaluateImplied(Boolean firstValue, Boolean secondValue) {
		if ((firstValue.equals(Boolean.TRUE))
				|| (secondValue.equals(Boolean.FALSE))) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	public Boolean evaluateBiConditional(Boolean firstValue, Boolean secondValue) {
		if (firstValue.equals(secondValue)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Object visitMultiSentence(MultiSentence fs) {
		return null; //TODO do multisentence model evaluation
	}

	public Object evaluate(Sentence sentence, Model m) {
		this.model = m;
		return sentence.visit(this);

	}
}