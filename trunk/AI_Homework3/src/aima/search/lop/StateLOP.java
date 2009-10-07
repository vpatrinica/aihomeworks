package aima.search.lop;

import java.util.Random;

public class StateLOP {
	
	public int [][] A;
	public int nLen;
	public int minDist;
	public int nParticipants;
	
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
		//A = new int [] [] {{0, 16, 15, 12, 5}, {14, 0, 4, 16, 1}, {15, 26, 0, 19, 2}, {18, 14, 11, 0, 12}, {25, 29, 28, 18, 0}};
		nLen = 5;
		minDist = 10;
		nParticipants = 30;
				
		Random gen = new Random();
		
		A = new int[nLen][nLen];
		for(int i = 0; i < nLen; i++){
			for(int j = 0; j <= i; j++){
				if(i == j){
					A[i][j] = 0;
				}else{
					A[i][j] = gen.nextInt(nParticipants + 1);  
					A[j][i] = nParticipants - A[i][j]; 
						
				}
			}
		}	
		System.out.println("The Matrix is: ");
		for(int k = 0; k < nLen; k++){
			for(int l = 0; l < nLen; l++){
				System.out.print(A[k][l] + "  ");
			}
			System.out.print("\n");
		}
		
		
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
