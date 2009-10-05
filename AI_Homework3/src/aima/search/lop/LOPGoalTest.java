package aima.search.lop;

import java.util.List; 

import aima.search.framework.GoalTest;


public class LOPGoalTest implements GoalTest
{
		
	public int nLen = 5;

	public boolean isGoalState(Object state)
	{
		List currentRating = (List) state;
		
		return (currentRating.size() == nLen);
	}

}