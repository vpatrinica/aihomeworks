/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import aima.logic.common.Visitor;

public interface PLVisitor extends Visitor {
	public Object visitSymbol(Symbol s);

	public Object visitTrueSentence(TrueSentence ts);

	public Object visitFalseSentence(FalseSentence fs);

	public Object visitNotSentence(UnarySentence fs);

	public Object visitBinarySentence(BinarySentence fs);

	public Object visitMultiSentence(MultiSentence fs);
}