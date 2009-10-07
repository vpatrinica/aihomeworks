package aima.search.demos;

import java.util.Iterator;
import java.util.List;

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

	public static void newLinearOrderingProblem()
	{

		nLOPAStar();
	}
	
	public static void nLOPAStar() 
	{
		
	
		System.out.println("\nLinear Ordering Demo AStar Search -->");
		try
		{
			
			int i;
			StateLOP generator = new StateLOP();
			int maxCost = 0;
			int [] maxSolution = new int [generator.nLen];
			for (i = 1; i<=generator.nLen; i++)
			{
				
			
				int [] initialPoint = new int [] {i};
				StateLOP initialState = new StateLOP(initialPoint, i, generator.A, generator.nLen, generator.minDist);
				Problem problem = new Problem(initialState,
						new LOPSuccessorFunction(),
						new LOPGoalTest(),
						new LOPStepCostFunction(),
						new LOPHeuristicFunction());
				Search search = new AStarSearch(new GraphSearch());
				SearchAgent agent = new SearchAgent(problem, search);
				System.out.println("The cool cost: "+ initialState.cost);
				
				int index, jndex;
				int tempCost = 0;
				int [] tempArray = new int[generator.nLen];
				tempArray[0] = i;
				List tempList = agent.getActions();
				for (index = 0; index<tempList.size(); index++ )
					tempArray[index+1] = Integer.valueOf(tempList.get(index).toString()).intValue();
				
				for (index = (tempArray.length-1); index>0; index--)
				{
					//System.out.println(index);
					//System.out.println("Calculating for node: " + list[index]);
					//System.out.println("the \"neighbours are\":");
					for (jndex = index-1; jndex>=0; jndex--){
						//System.out.println("Adding cost from node :" + list[index] + " to node " + list[jndex] + " = "+ A[list[index]-1][list[jndex] -1]);
					    tempCost+=generator.A[tempArray[index]-1][tempArray[jndex] -1];
					}
				}
				
				if (maxCost< tempCost)
				{
					maxCost = tempCost;
					maxSolution = (int [])tempArray.clone();
				}
				
			
			}
			
			System.out.println("The fucking best cost is : "+ maxCost);
			System.out.println("The ultimate solution:");
			for (i = (maxSolution.length - 1); i>=0; i--)
				System.out.print(maxSolution[i]);
			System.out.println();
			
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