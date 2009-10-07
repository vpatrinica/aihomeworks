package aima.search.lop;
import aima.search.lop.StateLOP;

import aima.search.framework.HeuristicFunction;

//import java.util.List;

public class LOPHeuristicFunction implements HeuristicFunction {
	
	


	public int getHeuristicValue(Object state) {
		StateLOP currentNode = (StateLOP) state;
		return (currentNode.nLen - currentNode.list.length)*currentNode.minDist;

	}

	

}