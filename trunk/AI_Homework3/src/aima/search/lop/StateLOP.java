/**
 * 
 */
package aima.search.lop;

public class StateLOP {
	
	public int [][] A;
	public int nLen;
	public int minDist;
	
	public int index;
	public int cost;
	public int[] list = new int[]{};

	public StateLOP(int[] lst, int k, int [][] aMatrix, int aLen, int aMinDist){

		index = k;	
		cost = 0;
		list = (int []) lst.clone();
		A = (int [][])aMatrix.clone();
		nLen = aLen;
		minDist = aMinDist;
		
	}
	

	public void generateMatrix()
	{
		A = new int [] [] {{0, 16, 15, 12, 5}, {14, 0, 4, 16, 1}, {15, 26, 0, 19, 2}, {18, 14, 11, 0, 12}, {25, 29, 28, 18, 0}};
		nLen = 5;
		minDist = 10;
		
	}
	
	public StateLOP(){
		
		generateMatrix();
		
	};

	public void getCost()
	{
		int nLen = 5;
		int index, jndex;
		
		for (index = (list.length-1); index>0; index--)
		{
			//System.out.println(index);
			System.out.println("Calculating for node: " + list[index]);
			System.out.println("the \"neighbours are\":");
			for (jndex = index-1; jndex>=0; jndex--){
				System.out.println("Adding cost from node :" + list[index] + " to node " + list[jndex] + " = "+ A[list[index]-1][list[jndex] -1]);
			    cost+=A[list[index]-1][list[jndex] -1];
			}
		}
	}



}
