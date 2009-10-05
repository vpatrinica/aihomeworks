package aima.test.gametest;

import java.util.ArrayList;

import junit.framework.TestCase;
import aima.games.GameState;
import aima.games.TicTacToe;
import aima.games.TicTacToeBoard;

public class TicTacToeTest extends TestCase {
	private TicTacToeBoard tb;
	public TicTacToeTest(String name) {
		super(name);
	}

	public void setUp() {
		tb = new TicTacToeBoard();
	}
	public void testCreation() {
		TicTacToe t3 = new TicTacToe();
		assertEquals(9, t3.getMoves(t3.getState()).size());
		assertEquals("X", t3.getPlayerToMove(t3.getState()));
	}

	public void testBoard() {

		assertEquals(true, tb.isEmpty(0, 0));
		assertEquals(true, tb.isEmpty(0, 2));
		assertEquals(true, tb.isEmpty(2, 0));
		assertEquals(true, tb.isEmpty(2, 2));
	}

	public void testLevel() {
		TicTacToe t1 = new TicTacToe();
		int level = t1.getLevel(t1.getState());
		assertEquals(0, level);
		t1.makeMove(0, 0);
		level = t1.getLevel(t1.getState());
		assertEquals(1, level);
	}
	public void testMark() {
		tb.markX(0, 0);
		assertEquals(false, tb.isEmpty(0, 0));
	}

	public void testFinished() {
		tb.markX(0, 0);
		tb.markX(0, 1);
		tb.markX(0, 2);
		assertEquals(true, tb.isAnyRowComplete());
		tb.markO(0, 2);
		assertEquals(false, tb.isAnyRowComplete());
		tb.markO(1, 2);
		tb.markO(2, 2);
		assertEquals(true, tb.isAnyColumnComplete());
		tb.markX(2, 2);
		tb.markX(1, 1);
		assertEquals(true, tb.isAnyDiagonalComplete());
		tb.markO(1, 1);
		tb.markO(2, 0);
		assertEquals(true, tb.isAnyDiagonalComplete());
		//tb.print();
	}

	public void testMoves() {
		TicTacToe t3 = new TicTacToe();
		assertEquals(9, t3.getMoves(t3.getState()).size());

		assertEquals("X", t3.getPlayerToMove(t3.getState()));
		t3.makeMove(t3.getState(), 0, 0);

		assertEquals(8, t3.getMoves(t3.getState()).size());
		assertEquals("O", t3.getPlayerToMove(t3.getState()));

		//t3.getBoard().print();
		//try illegal move
		t3.makeMove(t3.getState(), 0, 0);
		assertEquals(8, t3.getMoves(t3.getState()).size());
		assertEquals("O", t3.getPlayerToMove(t3.getState()));
		//t3.printPossibleMoves();

		t3.makeMove(t3.getState(), 1, 1);
		assertEquals(7, t3.getMoves(t3.getState()).size());
		assertEquals("X", t3.getPlayerToMove(t3.getState()));

	}

	public void testUtility() {

		//game 1
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(t3.getState(), 0, 0);
		assertEquals(0, t3.getUtility(t3.getState()));
		t3.makeMove(t3.getState(), 2, 2);
		assertEquals(0, t3.getUtility(t3.getState()));
		t3.makeMove(t3.getState(), 2, 0);
		assertEquals(0, t3.getUtility(t3.getState()));
		t3.makeMove(t3.getState(), 1, 0);
		assertEquals(0, t3.getUtility(t3.getState()));
		t3.makeMove(t3.getState(), 0, 2);
		assertEquals(0, t3.getUtility(t3.getState()));
		t3.makeMove(t3.getState(), 0, 1);
		assertEquals(0, t3.getUtility(t3.getState()));
		t3.makeMove(t3.getState(), 1, 1);
		assertEquals(1, t3.computeUtility((t3.getState())));

		// t3.getBoard().print();

		//game 2
		TicTacToe t4 = new TicTacToe();
		t4.makeMove(t4.getState(), 0, 0);
		assertEquals(0, t4.getUtility(t4.getState()));
		t4.makeMove(t4.getState(), 0, 1);
		assertEquals(0, t4.getUtility(t4.getState()));
		t4.makeMove(t4.getState(), 0, 2);
		assertEquals(0, t4.getUtility(t4.getState()));
		t4.makeMove(t4.getState(), 1, 0);
		assertEquals(0, t4.getUtility(t4.getState()));
		t4.makeMove(t4.getState(), 1, 1);
		assertEquals(0, t4.getUtility(t4.getState()));
		t4.makeMove(t4.getState(), 1, 2);
		assertEquals(0, t4.getUtility(t4.getState()));
		t4.makeMove(t4.getState(), 2, 0);
		// t4.getBoard().print();
		assertEquals(1, t4.computeUtility(t4.getState()));
	}

	/* public void testMinimumValue(){
	     TicTacToe t3 = new TicTacToe();
	     t3.makeMove(2,2);
	     int i = t3.minValue(t3.getState());
	     System.out.println("i = " + i);
	
	     TicTacToe t4 = new TicTacToe();
	     t4.makeMove(2,1);
	     int j = t4.minValue(t4.getState());
	     System.out.println("j = " + j);
	     assertEquals(-1,i);
	     assertEquals(-1,j);
	 }
	     */
	public void testGenerateSuccessors() {
		TicTacToe t3 = new TicTacToe();
		ArrayList successors = t3.getSuccessorStates(t3.getState());
		assertEquals(9, successors.size());
		checkSuccessorList(successors, "O", 8);
		ArrayList successors2 =
			t3.getSuccessorStates((GameState) successors.get(0));
		checkSuccessorList(successors2, "X", 7);
		ArrayList successors3 =
			t3.getSuccessorStates((GameState) successors2.get(0));
		checkSuccessorList(successors3, "O", 6);

		//System.out.println("done");
	}

	public void testGameStateEquality() {
		TicTacToeBoard tb1 = new TicTacToeBoard();
		TicTacToeBoard tb2 = new TicTacToeBoard();
		GameState gs1 = new GameState();
		GameState gs2 = new GameState();
		gs1.put("board", tb1);
		gs2.put("board", tb2);
		assertEquals(gs1, gs2);
		gs1.put("minimaxValue", new Integer(3));
		assertTrue(!(gs1.equals(gs2)));

	}
	public void testMiniMax() {
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(0, 0);
		t3.makeMove(2, 2);
		t3.makeMove(2, 0);
		t3.makeMove(1, 1);
		assertEquals(1, t3.getMiniMaxValue(t3.getState()));

	}

	public void testMiniMax2() {
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(0, 0);
		t3.makeMove(2, 2);
		t3.makeMove(2, 0);

		assertEquals(1, t3.getMiniMaxValue(t3.getState()));

	}
	public void testMiniMax3() {
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(0, 0);
		t3.makeMove(1, 1);

		assertEquals(0, t3.getMiniMaxValue(t3.getState()));

	}

	public void testMiniMax7() {
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(0, 0);
		t3.makeMove(0, 1);

		assertEquals(1, t3.getMiniMaxValue(t3.getState()));

	}
	public void tesMiniMax4() {
		TicTacToe t3 = new TicTacToe();

		assertEquals(0, t3.getMiniMaxValue(t3.getState()));

	}

	public void testTerminalStateDetection() {
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(0, 0);
		t3.makeMove(0, 1);
		t3.makeMove(0, 2);
		t3.makeMove(1, 0);
		t3.makeMove(1, 1);
		t3.makeMove(1, 2);
		//assertEquals(true,t3.terminalTest(t3.getState()));
		assertEquals(1, t3.getMiniMaxValue(t3.getState()));
	}

	public void testMiniMax15() {

		TicTacToe t1 = new TicTacToe();
		t1.makeMove(0, 0);

		int minimax1 = t1.getMiniMaxValue(t1.getState());
		TicTacToe t2 = new TicTacToe();
		t2.makeMove(0, 2);
		int minimax2 = t2.getMiniMaxValue(t2.getState());
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(2, 0);
		int minimax3 = t3.getMiniMaxValue(t3.getState());
		TicTacToe t4 = new TicTacToe();
		int minimax4 = t4.getMiniMaxValue(t4.getState());
		t4.makeMove(2, 2);
		//System.out.println(minimax1 + " " +minimax2+ " " +minimax3 + " "+ minimax4+ " ");
		assertEquals(minimax1, minimax2);

	}

	public void testMiniMax6() {
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(0, 0);
		t3.makeMove(0, 1);
		t3.makeMove(0, 2);

		t3.makeMove(2, 2);
		t3.makeMove(1, 2);

		t3.makeMove(2, 1);
		t3.makeMove(1, 1);
		assertEquals(-1, t3.getMiniMaxValue(t3.getState()));

	}

	public void testAlphaBeta() {
		TicTacToe t1 = new TicTacToe();
		t1.makeMove(0, 0);

		// System.out.println(t1.getLevel(t1.getState()));
		int alphabeta1 = t1.getAlphaBetaValue(t1.getState());
		TicTacToe t2 = new TicTacToe();
		t2.makeMove(0, 2);
		int alphabeta2 = t2.getAlphaBetaValue(t2.getState());
		TicTacToe t3 = new TicTacToe();
		t3.makeMove(2, 0);
		int alphabeta3 = t3.getAlphaBetaValue(t3.getState());
		TicTacToe t4 = new TicTacToe();
		int alphabeta4 = t4.getAlphaBetaValue(t4.getState());
		t4.makeMove(2, 2);
		// System.out.println(alphabeta1 + " " +alphabeta2+ " " +alphabeta3 + " "+ alphabeta4+ " ");
		assertEquals(alphabeta1, alphabeta2);

	}

	private void checkSuccessorList(
		ArrayList successorList,
		String playerToMove,
		int sizeOfSuccessors) {
		for (int i = 0; i < successorList.size(); i++) {
			GameState h = (GameState) successorList.get(0);

			ArrayList successors2 = new TicTacToe().getSuccessorStates(h);
			assertEquals(sizeOfSuccessors, successors2.size());
			assertEquals(playerToMove, new TicTacToe().getPlayerToMove(h));
		}
	}

}
