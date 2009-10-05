/**
 * 
 */
package aima.search.lop;

/**
 * @author Anton
 *
 */
public class StateLOP {
	
	public int index;
	public int[] list;
	public StateLOP(int[] lst, int k){
		
		index = k;	
		list = lst.clone();
		
	}
	public StateLOP(){
	};
	
}
