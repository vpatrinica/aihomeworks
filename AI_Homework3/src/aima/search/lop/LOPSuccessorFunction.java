/*
 * Created on Sep 12, 2004
 *
 */
package aima.search.lop;

import java.util.ArrayList;
import java.util.List;


import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;


public class LOPSuccessorFunction implements SuccessorFunction {

	public List getSuccessors(Object state) {
		StateLOP currentSt  = (StateLOP) state;
		//System.out.println("CurrentState:"+currentSt.list.toString()+"\n");
		int index = 0;
		System.out.println("Again Current state:");
		for (index = 0; index < currentSt.list.length; index++)
		{
			System.out.print(currentSt.list[index]);
		}
		System.out.println("\n");
		int [] tempArray = new int [(currentSt.list.length + 1)];
		for (int jndex = 0; jndex < currentSt.list.length; jndex++)
		{
			tempArray[jndex] = currentSt.list[jndex];
		}
		tempArray[currentSt.list.length] = 0;
		System.out.println("Successors of : " + currentSt.index + ":\n");
		List successors = new ArrayList();
		
		int [] Mylist = new int[currentSt.nLen];
		for(int min = 0; min < currentSt.nLen; min ++){
			Mylist[min] = min + 1;
		}
		
		for (index = 0; index < currentSt.list.length; index++)
		{
			Mylist[currentSt.list[index] - 1] = 0;
		}
		for (index = 0; index < currentSt.nLen; index++)
		{
			
			if (Mylist[index] != 0)
			{
				StateLOP nextSt = new StateLOP(tempArray, Mylist[index], currentSt.A, currentSt.nLen, currentSt.minDist);
						
				nextSt.list[currentSt.list.length] = Mylist[index];
				System.out.print(Mylist[index]);
			    successors.add(new Successor(String.valueOf(Mylist[index]), nextSt));
			}
		}
		
		return successors;
	}

	

}