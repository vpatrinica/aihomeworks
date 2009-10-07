/**
 * 
 */
package aima.search.lop;
<<<<<<< .mine
import 

=======
import java.util.List;
import java.util.ArrayList;
>>>>>>> .r8
/**
 * @author Anton
 *
 */
public class StateLOP {
	
	int [][] A = new int [] [] {{0, 16, 15, 12, 5}, {14, 0, 4, 16, 1}, {15, 26, 0, 19, 2}, {18, 14, 11, 0, 12}, {25, 29, 28, 18, 0}};
	
	public int index;
<<<<<<< .mine
	public int cost;
	public int[] list;
	public StateLOP(int[] lst, int k){
=======
	public int cost = 0;
	public List list = new ArrayList();
	public StateLOP(List lst, int k, int myCost){
>>>>>>> .r8
		
		index = k;	
		cost = myCost;
		list.addAll(lst);
		
	}
<<<<<<< .mine
	public getCost()
	{
		index i = 0;
		
		for (index = 0; (index<list.length - 1); index++)
		{
			
		}
	}
	public StateLOP(){
	};
	
=======
	public int getCost()
	{
		for (int index = 0; index<(list.size() - 1 ); index++)
		{
			cost+=A[(int)(list.get(index))][list.get(index+1)];
			return cost;
		}
	}
>>>>>>> .r8

}
