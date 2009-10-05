/*
 * Created on Sep 16, 2003 by Ravi Mohan
 *
 */
package aima.test.logictest.propositional;

import junit.framework.TestCase;
import aima.logic.prop.infrastructure.PEParser;
import aima.logic.prop.infrastructure.Sentence;
import aima.logic.prop.infrastructure.SymbolCollector;

public class SymbolCollectorTest extends TestCase {
	private PEParser parser;
	private SymbolCollector sc;

	public void setUp() {
		parser = new PEParser();
		sc = new SymbolCollector();
	}
	public void testSymbolCollection() {

		Sentence s = (Sentence) parser.parse(" ( NOT  ( P <=>  ( S AND T ) ) ) ");
		s.visit(sc);
		assertEquals(3, sc.getSymbols().size());
		assertEquals(0, sc.getNegatedSymbols().size());
	}
	public void testNegatedSymbolCollection() {
		Sentence s = (Sentence) parser.parse(" (( NOT   P) <=>  ( S AND T ) )  ");
		s.visit(sc);
		assertEquals(3, sc.getSymbols().size());
		assertEquals(1, sc.getNegatedSymbols().size());
		assertEquals(2, sc.getPositiveSymbols().size());
	}

	public void testSimpleSymbolCollection() {
		Sentence s = (Sentence) parser.parse("P");
		s.visit(sc);
		assertEquals(1, sc.getSymbols().size());
		assertEquals(0, sc.getNegatedSymbols().size());
		//assertEquals(1, sc.getPositiveSymbols().size());
	}
	public void testSimpleNOTSymbolCollection() {
		Sentence s = (Sentence) parser.parse("NOT P");
		s.visit(sc);
		assertEquals(1, sc.getSymbols().size());
		assertEquals(1, sc.getNegatedSymbols().size());
		assertEquals(0, sc.getPositiveSymbols().size());
	}

}
