package aima.games;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Game {
	protected GameState initialState = new GameState();

	protected GameState presentState = new GameState();

	public int getLevel(GameState g) {
		return (((Integer) g.get("level")).intValue());
	}

	protected int level;

	public ArrayList getMoves(GameState state) {
		return (ArrayList) state.get("moves");
	}

	public String getPlayerToMove(GameState state) {
		return (String) state.get("player");
	}

	public int getUtility(Hashtable h) {
		return ((Integer) h.get("utility")).intValue();
	}

	public GameState getState() {
		return presentState;
	}

	protected abstract int computeUtility(GameState state);

	protected abstract boolean terminalTest(GameState state);

	public int maxValue(GameState state) {
		// System.out.println("Max Received board ");
		//((TicTacToeBoard)state.get("board")).print();

		int maxOfSuccMinValue = Integer.MIN_VALUE;

		if (terminalTest(state)) {
			maxOfSuccMinValue = computeUtility(state);

		} else {
			ArrayList successorList = getSuccessorStates(state);

			for (int i = 0; i < successorList.size(); i++) {
				GameState successor = (GameState) successorList.get(i);

				int succMinValue = minValue(successor);
				if (succMinValue > maxOfSuccMinValue) {
					maxOfSuccMinValue = succMinValue;
					state.put("next", successor);

				}

			}

		}
		state.put("minimaxvalue", new Integer(maxOfSuccMinValue));

		/*
		 * if (getLevel(state) < 3){ System.out.println("Assigning minimax value
		 * of " + maxOfSuccMinValue + " to");
		 * ((TicTacToeBoard)state.get("board")).print(); }
		 */

		return maxOfSuccMinValue;

	}

	public int minValue(GameState state) {

		//System.out.println("Min Received board ");
		// ((TicTacToeBoard)state.get("board")).print();
		int minOfSuccMaxValue = Integer.MAX_VALUE;

		if (terminalTest(state)) {
			minOfSuccMaxValue = computeUtility(state);

		} else {
			ArrayList successorList = getSuccessorStates(state);
			for (int i = 0; i < successorList.size(); i++) {
				GameState successor = (GameState) successorList.get(i);
				int succMaxValue = maxValue(successor);
				if (succMaxValue < minOfSuccMaxValue) {
					minOfSuccMaxValue = succMaxValue;
					state.put("next", successor);
				}

			}

		}
		/*
		 * if (getLevel(state) < 3){ System.out.println("Assigning minimax value
		 * of " + minOfSuccMaxValue + " to");
		 * ((TicTacToeBoard)state.get("board")).print(); }
		 */
		state.put("minimaxvalue", new Integer(minOfSuccMaxValue));
		return minOfSuccMaxValue;

	}

	protected int maxValue(GameState state, int alpha, int beta) {
		int maxOfSuccMinValue = Integer.MIN_VALUE;

		if (terminalTest(state)) {
			maxOfSuccMinValue = computeUtility(state);
		} else {
			ArrayList successorList = getSuccessorStates(state);
			for (int i = 0; i < successorList.size(); i++) {
				GameState successor = (GameState) successorList.get(i);
				int succMinValue = minValue(successor, alpha, beta);
				if (succMinValue > maxOfSuccMinValue) {
					maxOfSuccMinValue = succMinValue;
					state.put("next", successor);
				}

			}

		}
		state.put("alphabeta", new Integer(maxOfSuccMinValue));
		return maxOfSuccMinValue;
	}

	protected int minValue(GameState state, int alpha, int beta) {
		int minOfSuccMaxValue = Integer.MAX_VALUE;

		if (terminalTest(state)) {
			minOfSuccMaxValue = computeUtility(state);

		} else {
			ArrayList successorList = getSuccessorStates(state);
			for (int i = 0; i < successorList.size(); i++) {
				GameState successor = (GameState) successorList.get(i);
				int succMaxValue = maxValue(successor, alpha, beta);
				if (succMaxValue < minOfSuccMaxValue) {
					minOfSuccMaxValue = succMaxValue;
					state.put("next", successor);
				}

			}

		}
		/*
		 * if (getLevel(state) < 3){ System.out.println("Assigning minimax value
		 * of " + minOfSuccMaxValue + " to");
		 * ((TicTacToeBoard)state.get("board")).print(); }
		 */
		state.put("alphabetavalue", new Integer(minOfSuccMaxValue));
		return minOfSuccMaxValue;

	}

	public void makeMiniMaxMove() {
		getMiniMaxValue(presentState);
		GameState nextState = (GameState) presentState.get("next");
		if (nextState != null) {
			makeMove(presentState, nextState.get("moveMade"));

		}

	}

	public void makeAlphaBetaMove() {
		getAlphaBetaValue(presentState);
		GameState nextState = (GameState) presentState.get("next");
		if (nextState != null) {
			makeMove(presentState, nextState.get("moveMade"));

		}

	}

	public abstract ArrayList getSuccessorStates(GameState state);

	public abstract GameState makeMove(GameState state, Object o);

	public boolean hasEnded() {
		return (terminalTest(getState()));
	}

	public Game() {
	}

	public abstract int getMiniMaxValue(GameState state);

	public abstract int getAlphaBetaValue(GameState state);

}