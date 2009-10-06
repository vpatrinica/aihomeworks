/**
 * 
 */
package aima.search.lop;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Anton
 *
 */
public class StateLOP {
	
	int [][] A = new int [] [] {{0, 16, 15, 12, 5}, {14, 0, 4, 16, 1}, {15, 26, 0, 19, 2}, {18, 14, 11, 0, 12}, {25, 29, 28, 18, 0}};
	
	public int index;
	public int cost = 0;
	public List list = new ArrayList();
	public StateLOP(List lst, int k, int myCost){
		
		index = k;	
		cost = myCost;
		list.addAll(lst);
		
	}
	public int getCost()
	{
		for (int index = 0; index<(list.size() - 1 ); index++)
		{
			cost+=A[(int)(list.get(index))][list.get(index+1)];
			return cost;
		}
	}
}
