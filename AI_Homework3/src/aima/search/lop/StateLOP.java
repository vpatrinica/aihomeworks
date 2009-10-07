/**
 * 
 */
package aima.search.lop;

public class StateLOP {
int [][] A = new int [] [] {{0, 16, 15, 12, 5}, {14, 0, 4, 16, 1}, {15, 26, 0, 19, 2}, {18, 14, 11, 0, 12}, {25, 29, 28, 18, 0}};
	
	public int index;
	public int cost;
	public int[] list = new int[]{};

	public StateLOP(int[] lst, int k){

		index = k;	
		cost = 0;
		list = (int []) lst.clone();
		
	}
	
	
//	public StateLOP(int[] lst, int k, int myCost){
//
//		index = k;	
//		cost = myCost;
//		list = (int []) lst.clone();
//		
//	}

	public StateLOP(){};

	public void getCost()
	{
		int index;
		
		for (index = (list.length-1); index>0; index--)
		{
			//System.out.println(index);
			cost+=A[list[index]-1][list[index-1]-1];
		}
	}



}
