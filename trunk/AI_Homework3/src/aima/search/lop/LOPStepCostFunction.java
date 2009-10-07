package aima.search.lop;



import aima.search.framework.StepCostFunction;

import aima.search.lop.StateLOP;

public class LOPStepCostFunction implements StepCostFunction
{

	public LOPStepCostFunction()
	{
	}
    int [][] A = new int [] [] {{0, 16, 15, 12, 5}, {14, 0, 4, 16, 1}, {15, 26, 0, 19, 2}, {18, 14, 11, 0, 12}, {25, 29, 28, 18, 0}};
	
	public Double calculateStepCost(Object fromState, Object toState, String action)
		{
			StateLOP currentSt = (StateLOP) fromState;
			
			if (currentSt.list.length == 0) return Double.valueOf(0.0);
			StateLOP nextSt = (StateLOP) toState;
			System.out.println("Cost between: "+ (currentSt.index-1) + " to " + (nextSt.index-1) + " = " + A[currentSt.index -1 ][nextSt.index -1] + "\n");
			
			return Double.valueOf(1.0*(A[currentSt.index-1][nextSt.index-1]));
		}
	}
	
	

