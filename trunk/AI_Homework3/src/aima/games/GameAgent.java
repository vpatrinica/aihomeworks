package aima.games;

public class GameAgent {
	private Game game;

	public GameAgent(Game g) {
		this.game = g;
	}

	public void makeMiniMaxMove() {
		game.makeMiniMaxMove();
	}

	public void makeAlphaBetaMove() {
		game.makeAlphaBetaMove();
	}

}