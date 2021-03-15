package controllers;

import entities.UtilityModel;
import entities.StateModel;
import entities.ActionModel;
import entities.TransitionModel;
import entities.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UtilityController {

	//Calculates the utility for each possible action and returns the action with maximal utility
		public static UtilityModel getBestUtility(final int col, final int row, final UtilityModel[][] curUtilityArr, final StateModel[][] maze) {

			List<UtilityModel> utilities = new ArrayList<>();

			utilities.add(new UtilityModel(ActionModel.NORTH, getNorthUtility(col, row, curUtilityArr, maze)));
			utilities.add(new UtilityModel(ActionModel.SOUTH, getSouthUtility(col, row, curUtilityArr, maze)));
			utilities.add(new UtilityModel(ActionModel.WEST, getWestUtility(col, row, curUtilityArr, maze)));
			utilities.add(new UtilityModel(ActionModel.EAST, getEastUtility(col, row, curUtilityArr, maze)));

			Collections.sort(utilities);
			UtilityModel bestUtility = utilities.get(0);
			return bestUtility;
		}
		
		//Calculates the utility for the given action
		public static UtilityModel getFixedUtility(final ActionModel action, final int col, final int row, final UtilityModel[][] actionUtilityArr, final StateModel[][] maze) {

			UtilityModel fixedActionUtility = null;

			switch (action) {
				case NORTH:
					fixedActionUtility = new UtilityModel(ActionModel.NORTH, getNorthUtility(col, row, actionUtilityArr, maze));
					break;
				case SOUTH:
					fixedActionUtility = new UtilityModel(ActionModel.SOUTH, getSouthUtility(col, row, actionUtilityArr, maze));
					break;
				case WEST:
					fixedActionUtility = new UtilityModel(ActionModel.WEST, getWestUtility(col, row, actionUtilityArr, maze));
					break;
				case EAST:
					fixedActionUtility = new UtilityModel(ActionModel.EAST, getEastUtility(col, row, actionUtilityArr, maze));
					break;
			}

			return fixedActionUtility;
		}
		
		// Bellman equation
		public static UtilityModel[][] bellmanEstimates(final UtilityModel[][] utilityArr, final StateModel[][] maze) {

			UtilityModel[][] curUtilityArr = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];
			for (int col = 0; col < Constants.WIDTH; col++) {
				for (int row = 0; row < Constants.HEIGHT; row++) {
					curUtilityArr[col][row] = new UtilityModel();
				}
			}

			UtilityModel[][] newUtilityArr = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];
			for (int col = 0; col < Constants.WIDTH; col++) {
				for (int row = 0; row < Constants.HEIGHT; row++) {
					newUtilityArr[col][row] = new UtilityModel(utilityArr[col][row].getAction(), utilityArr[col][row].getUtility());
				}
			}

			int i = 0;
			do {
				updateUtilites(newUtilityArr, curUtilityArr);

				// For each state
				for (int row = 0; row < Constants.WIDTH; row++) {
					for (int col = 0; col < Constants.HEIGHT; col++) {
						if (!maze[col][row].getIsWall()) {
							// Updates the utility based on the action stated in the policy
							ActionModel action = curUtilityArr[col][row].getAction();
							newUtilityArr[col][row] = getFixedUtility(action, col, row, curUtilityArr, maze);
						}
					}
				}
				i++;
			} while(i < Constants.K);

			return newUtilityArr;
		}

		private static TransitionModel transitionModel;
		
		public static double getEastUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			double EastUtility = 0.000;

			// Intends to move east
			EastUtility += transitionModel.getIntendedProb() * moveEast(col, row, curUtilityArr, maze);

			// Intends to move east, but moves north instead
			EastUtility += transitionModel.getUnintendedProb() * moveNorth(col, row, curUtilityArr, maze);

			// Intends to move east, but moves south instead
			EastUtility += transitionModel.getUnintendedProb() * moveSouth(col, row, curUtilityArr, maze);

			// Final utility
			EastUtility = maze[col][row].getReward() + Constants.DISCOUNT * EastUtility;

			return EastUtility;
		}

		public static double getWestUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			double WestUtility = 0.000;

			// Intends to move west
			WestUtility += transitionModel.getIntendedProb() * moveWest(col, row, curUtilityArr, maze);

			// Intends to move west, but moves north instead
			WestUtility += transitionModel.getUnintendedProb() * moveNorth(col, row, curUtilityArr, maze);

			// Intends to move west, but moves south instead
			WestUtility += transitionModel.getUnintendedProb() * moveSouth(col, row, curUtilityArr, maze);

			// Final utility
			WestUtility = maze[col][row].getReward() + Constants.DISCOUNT * WestUtility;

			return WestUtility;
		}

		public static double getSouthUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			double SouthUtility = 0.000;

			// Intends to move south
			SouthUtility += transitionModel.getIntendedProb() * moveSouth(col, row, curUtilityArr, maze);

			// Intends to move south, but moves west instead
			SouthUtility += transitionModel.getUnintendedProb() * moveWest(col, row, curUtilityArr, maze);

			// Intends to move south, but moves east instead
			SouthUtility += transitionModel.getUnintendedProb() * moveEast(col, row, curUtilityArr, maze);

			// Final utility
			SouthUtility = maze[col][row].getReward() + Constants.DISCOUNT * SouthUtility;

			return SouthUtility;
		}

		public static double getNorthUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			double NorthUtility = 0.000;

			// Intends to move north
			NorthUtility += transitionModel.getIntendedProb() * moveNorth(col, row, curUtilityArr, maze);

			// Intends to move north, but moves west instead
			NorthUtility += transitionModel.getUnintendedProb() * moveWest(col, row, curUtilityArr, maze);

			// Intends to move north, but moves east instead
			NorthUtility += transitionModel.getUnintendedProb() * moveEast(col, row, curUtilityArr, maze);

			// Final utility
			NorthUtility = maze[col][row].getReward() + Constants.DISCOUNT * NorthUtility;

			return NorthUtility;
		}
		
		// Move South
		private static double moveSouth(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			if (row + 1 < Constants.WIDTH && !maze[col][row + 1].getIsWall()) {
				return curUtilityArr[col][row + 1].getUtility();
			}
			return curUtilityArr[col][row].getUtility();
		}

		// Move North
		private static double moveNorth(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			if (row - 1 >= 0 && !maze[col][row - 1].getIsWall()) {
				return curUtilityArr[col][row - 1].getUtility();
			}
			return curUtilityArr[col][row].getUtility();
		}

		// Move East
		private static double moveEast(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			if (col + 1 < Constants.HEIGHT && !maze[col + 1][row].getIsWall()) {
				return curUtilityArr[col + 1][row].getUtility();
			}
			return curUtilityArr[col][row].getUtility();
		}
		
		// Move West
		private static double moveWest(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			if (col - 1 >= 0 && !maze[col - 1][row].getIsWall()) {
				return curUtilityArr[col - 1][row].getUtility();
			}
			return curUtilityArr[col][row].getUtility();
		}
		
		// Copy from newUtility array to curUtility array
		public static void updateUtilites(UtilityModel[][] newUtilityArr, UtilityModel[][] curUtilityArr) {
			for (int i = 0; i < newUtilityArr.length; i++) {
				System.arraycopy(newUtilityArr[i], 0, curUtilityArr[i], 0, newUtilityArr[i].length);
			}			
		}
}
