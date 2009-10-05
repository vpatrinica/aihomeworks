package aima.search.lop;


import aima.search.framework.HeuristicFunction;

import java.util.List;

public class LOPHeuristicFunction implements HeuristicFunction {
	
	public int nLen = 5;
	public int minDist = 10;

	public int getHeuristicValue(Object state) {
		List currentNode = (List) state;
		return (nLen - currentNode.size())*minDist;

	}

	

}