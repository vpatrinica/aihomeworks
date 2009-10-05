/*
 * Created on Sep 21, 2004
 *
 */
package aima.logic.demos;

import aima.logic.fol.demos.FolDemo;
import aima.logic.prop.demos.DPPLDemo;
import aima.logic.prop.demos.PLFCEntailsDemo;
import aima.logic.prop.demos.PLResolutionDemo;
import aima.logic.prop.demos.TTEntailsDemo;
import aima.logic.prop.demos.WalkSATDemo;

/**
 * @author Ravi Mohan
 *  
 */
public class LogicDemo {
	public static void main(String[] args) {
		//propostional
		DPPLDemo.main(null);
		PLFCEntailsDemo.main(null);
		PLResolutionDemo.main(null);
		TTEntailsDemo.main(null);
		WalkSATDemo.main(null);

		//firstorder
		FolDemo.main(null);
	}

}