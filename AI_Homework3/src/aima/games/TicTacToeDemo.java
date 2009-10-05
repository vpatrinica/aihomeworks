package aima.games;

public class TicTacToeDemo {

	public static void main(String[] args) {
		System.out.println("TicTacToe Demo");
		System.out.println("");
		System.out.println("MINI MAX ");
		System.out.println("");

		TicTacToe t3 = new TicTacToe();
		while (!(t3.hasEnded())) {
			System.out.println(t3.getPlayerToMove(t3.getState()) + " playing");
			System.out.println("");
			t3.makeMiniMaxMove();
			GameState presentState = t3.getState();
			TicTacToeBoard board = t3.getBoard(presentState);
			System.out.println("");
			board.print();
			System.out.println("");
		}

		System.out.println("ALPHA BETA ");
		System.out.println("");

		TicTacToe t4 = new TicTacToe();
		while (!(t4.hasEnded())) {
			System.out.println(t4.getPlayerToMove(t4.getState())
					+ "  playing ... ");

			t4.makeAlphaBetaMove();
			GameState presentState = t4.getState();
			TicTacToeBoard board = t4.getBoard(presentState);
			board.print();
		}

	}

}