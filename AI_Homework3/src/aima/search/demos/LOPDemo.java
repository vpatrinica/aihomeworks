package aima.search.demos;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;


import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.AStarSearch;
import aima.search.lop.LOPSuccessorFunction;
import aima.search.lop.LOPGoalTest;
import aima.search.lop.LOPHeuristicFunction;
import aima.search.lop.StateLOP;
import aima.search.lop.LOPStepCostFunction;


public class LOPDemo {
	
	public static void main(String[] args) {

		newLinearOrderingProblem();
	
	}

	private static void newLinearOrderingProblem()
	{

		nLOPAStar();
	}
	
	private static void nLOPAStar() 
	{
		
	
		System.out.println("\nLinear Ordering Demo AStar Search -->");
		try
		{
			List myInitialState = new ArrayList();
			myInitialState.add("1");
			StateLOP initialState = new StateLOP(myInitialState, 1, 0);
			Problem problem = new Problem(initialState,
					new LOPSuccessorFunction(),
					new LOPGoalTest(),
					new LOPStepCostFunction(),
					new LOPHeuristicFunction());
			Search search = new AStarSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void printInstrumentation(Properties properties) {
		Iterator keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = (String) actions.get(i);
			System.out.println(action);
		}
	}

}