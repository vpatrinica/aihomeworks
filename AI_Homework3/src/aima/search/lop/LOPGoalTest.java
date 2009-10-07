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
		if (currentRating.list.length == nLen)
			{
			System.out.println("Solution state:");
			for (int index = 0; index < currentRating.list.length; index++)
			{
				System.out.print(currentRating.list[index]);
			}
			System.out.println("\n");
			currentRating.getCost();
			System.out.println("The solution cost is: " + currentRating.cost + " \n");
			}
		return (currentRating.list.length == nLen);
	}

}