package aima.search.lop;

//import java.util.List; 
import aima.search.lop.StateLOP;

import aima.search.framework.GoalTest;


public class LOPGoalTest implements GoalTest
{
		
	public int nLen = 5;

	public boolean isGoalState(Object state)
	{
		StateLOP currentRating = (StateLOP) state;
		if (currentRating.list.size() == nLen)
			{
			System.out.println("Solution state:");
			for (int index = 0; index < currentRating.list.size(); index++)
			{
				System.out.print(currentRating.list.get(index));
			}
			System.out.println("\n");
			}
		return (currentRating.list.size() == nLen);
	}

}