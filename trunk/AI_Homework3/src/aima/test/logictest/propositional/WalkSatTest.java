/*
 * Created on Sep 25, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.algorithms.WalkSAT;
import aima.logic.prop.infrastructure.Model;


public class WalkSatTest extends TestCase {
	public void testWalkSat(){
		WalkSAT walkSAT = new WalkSAT();
		Model m = walkSAT.findModelFor("( A AND B )",1000,0.5);
		if (m==null){
			System.out.println("failure");
		}
		else{
			m.print();
		}
	}

}
