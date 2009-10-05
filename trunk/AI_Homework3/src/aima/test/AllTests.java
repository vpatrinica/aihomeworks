package aima.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import aima.test.coretest.XYEnvironmentTest;
import aima.test.coretest.XYLocationTest;
import aima.test.gametest.TicTacToeTest;
import aima.test.logictest.LogicTests;

import aima.test.search.Search2Tests;

import aima.test.tvenvironmenttest.ModelBasedTVEVaccumAgentTest;
import aima.test.tvenvironmenttest.TrivialVaccumEnvironmentTest;

public class AllTests {

	public static void main(String args[]) {
		junit.textui.TestRunner.main(new String[] { AllTests.class.getName()});
	}
	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTest(new TestSuite(ModelBasedTVEVaccumAgentTest.class));
	;
		suite.addTest(new TestSuite(TrivialVaccumEnvironmentTest.class));
		suite.addTest(new TestSuite(XYEnvironmentTest.class));
		suite.addTest(new TestSuite(XYLocationTest.class));

		suite.addTest(new TestSuite(TicTacToeTest.class));
		
		

		suite.addTest(LogicTests.suite());
		suite.addTest(Search2Tests.suite());

		return suite;
	}

}
