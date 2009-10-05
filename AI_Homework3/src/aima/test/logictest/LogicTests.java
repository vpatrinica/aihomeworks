/*
 * Created on Aug 30, 2003 by Ravi Mohan
 *  
 */
package aima.test.logictest;

import junit.framework.Test;
import junit.framework.TestSuite;
import aima.test.logictest.foltest.DLKnowledgeBaseTest;
import aima.test.logictest.foltest.DomainTest;
import aima.test.logictest.foltest.FOLLexerTest;
import aima.test.logictest.foltest.FOLParserTest;
import aima.test.logictest.foltest.FOLSubstTest;
import aima.test.logictest.foltest.UnifierTest;
import aima.test.logictest.foltest.VariableCollectorTest;
import aima.test.logictest.propositional.CNFTransformerTest;
import aima.test.logictest.propositional.ModelTest;
import aima.test.logictest.propositional.PLResolutionTest;
import aima.test.logictest.propositional.SymbolCollectorTest;
import aima.test.logictest.propositional.TTEntailsTest;
import aima.test.logictest.propositional.parser.PELexerTest;
import aima.test.logictest.propositional.parser.PEParser2Test;
import aima.test.utiltest.SetTest;

public class LogicTests {
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		//propositional tests
		suite.addTest(new TestSuite(PELexerTest.class));
		suite.addTest(new TestSuite(PEParser2Test.class));
		suite.addTest(new TestSuite(PLResolutionTest.class));
		suite.addTest(new TestSuite(ModelTest.class));
		suite.addTest(new TestSuite(CNFTransformerTest.class));
		suite.addTest(new TestSuite(SymbolCollectorTest.class));
		suite.addTest(new TestSuite(TTEntailsTest.class));

		//first order tests
		suite.addTest(new TestSuite(DLKnowledgeBaseTest.class));
		suite.addTest(new TestSuite(DomainTest.class));
		suite.addTest(new TestSuite(FOLLexerTest.class));
		suite.addTest(new TestSuite(FOLParserTest.class));
		suite.addTest(new TestSuite(FOLSubstTest.class));
		suite.addTest(new TestSuite(VariableCollectorTest.class));
		suite.addTest(new TestSuite(UnifierTest.class));
		
		//utils
		suite.addTest(new TestSuite(SetTest.class));
		return suite;
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

}