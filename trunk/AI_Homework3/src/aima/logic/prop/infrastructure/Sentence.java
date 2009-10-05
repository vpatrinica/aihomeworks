/*
 * Created on Sep 15, 2003 by Ravi Mohan
 *  
 */
package aima.logic.prop.infrastructure;

import aima.logic.common.ParseTreeNode;

public abstract class Sentence implements ParseTreeNode {

	public abstract Object visit(PLVisitor plv);

	public int hashCode() {
		return 0;
	}

}