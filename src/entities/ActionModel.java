package entities;

import java.util.Random;

// List of all possible moves the agent can take at each square of the maze
public enum ActionModel {
	NORTH("^"),
	SOUTH("v"),
	WEST("<"),
	EAST(">");

	// String representation
	private String strAction;

	ActionModel(String strAction) {
		this.strAction = strAction;
	}

	@Override
	public String toString() {
		return strAction;
	}

	private static final ActionModel[] ACTIONS = values();
	private static final int LENGTH = ACTIONS.length;
	private static final Random RAND = new Random();

	public static ActionModel getRandomAction() {
		return ACTIONS[RAND.nextInt(LENGTH)];
	}
}
