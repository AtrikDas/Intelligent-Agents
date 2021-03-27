package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.StateModel;
import entities.UtilityModel;
import entities.ActionModel;
import entities.Constants;

public class IterationController {
	
	public static double convergeThreshold;
	public static int k = 0;

	public static void valueIteration(StateModel[][] maze, List<UtilityModel[][]> utilityList) {
		
		UtilityModel[][] curUtilityArr = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];
		UtilityModel[][] newUtilityArr = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];

		// Initialize default utilities for each state
		for (int col = 0; col < Constants.WIDTH; col++) {
			for (int row = 0; row < Constants.HEIGHT; row++) {
				newUtilityArr[col][row] = new UtilityModel();
			}
		}

		utilityList = new ArrayList<>();

		// Initialize delta to minimum double value first
		double delta = Double.MIN_VALUE;

		convergeThreshold = Constants.EPSILON * ((1.000 - Constants.DISCOUNT) / Constants.DISCOUNT);

		// Initialize number of iterations
		do {

			UtilityController.updateUtilites(newUtilityArr, curUtilityArr);

 			delta = Double.MIN_VALUE;

			// Append to list of Utility a copy of the existing actions & utilities
 			UtilityModel[][] currUtilArrCopy =
 			new UtilityModel[Constants.WIDTH][Constants.HEIGHT];
			UtilityController.updateUtilites(curUtilityArr, currUtilArrCopy);
			utilityList.add(currUtilArrCopy);

			// For each state
			for(int row = 0 ; row < Constants.WIDTH ; row++) {
				for(int col = 0 ; col < Constants.HEIGHT ; col++) {

					// Calculate the utility for each state, not necessary to calculate for walls
					if (!maze[col][row].getIsWall()) {
						newUtilityArr[col][row] =
						UtilityController.getBestUtility(col, row, curUtilityArr, maze);

						double updatedUtil = newUtilityArr[col][row].getUtility();
						double currentUtil = curUtilityArr[col][row].getUtility();
						double updatedDelta = Math.abs(updatedUtil - currentUtil);

						// Update delta, if the updated delta value is larger than the current one
						delta = Math.max(delta, updatedDelta);
					}
				}
			}
			k++;

		//the iteration will cease when the delta is less than the convergence threshold
		} while ((delta) >= convergeThreshold);
		
		// Final item in the list is the optimal policy derived by value iteration
		int lastIteration = utilityList.size() - 1;
		final UtilityModel[][] optimalPolicy = utilityList.get(lastIteration);
					    
		// Displays the Maze Environment with Optimal Actions
		DisplayController.printPolicy(optimalPolicy);
		
		// Display the utilities of all the states as a list
		DisplayController.printUtilities(maze, optimalPolicy);
				
		// Display the utilities of all states in the maze
		DisplayController.printUtilitiesMaze(optimalPolicy);
		
	}

	public static void policyIteration(StateModel[][] maze, List<UtilityModel[][]> utilityList) {
		
		UtilityModel[][] curUtilityArr = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];
		UtilityModel[][] newUtilityArr = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];

		// Initialize default utilities and policies for each state
		for (int col = 0; col < Constants.WIDTH; col++) {
			for (int row = 0; row < Constants.HEIGHT; row++) {
				newUtilityArr[col][row] = new UtilityModel();
				if (!maze[col][row].getIsWall()) {
					ActionModel randomAction = ActionModel.getRandomAction();
					newUtilityArr[col][row].setAction(randomAction);
				}
			}
		}

		// List to store utilities of every state at each iteration
		utilityList = new ArrayList<>();

		// Used to check if the current policy value is already optimal
		boolean optimal = true;

		do {

			UtilityController.updateUtilites(newUtilityArr, curUtilityArr);

			// Append to list of Utility a copy of the existing actions & utilities
			UtilityModel[][] curUtilityArrCopy = new UtilityModel[Constants.WIDTH][Constants.HEIGHT];
			UtilityController.updateUtilites(curUtilityArr, curUtilityArrCopy);
			utilityList.add(curUtilityArrCopy);
			

			// Policy estimation based on the current actions and utilities
			newUtilityArr = UtilityController.bellmanEstimates(curUtilityArr, maze);

			optimal = true;

			// For each state - Policy improvement
			for (int row = 0; row < Constants.WIDTH; row++) {
				for (int col = 0; col < Constants.HEIGHT; col++) {

					// Calculate the utility for each state, not necessary to calculate for walls
					if (!maze[col][row].getIsWall()) {
						// Best calculated action based on maximizing utility
						UtilityModel bestActionUtil = UtilityController.getBestUtility(col, row, newUtilityArr, maze);

						// Action and the corresponding utility based on current policy
						ActionModel policyAction = newUtilityArr[col][row].getAction();
						UtilityModel policyActionUtil = UtilityController.getFixedUtility(policyAction, col, row, newUtilityArr, maze);

						if((bestActionUtil.getUtility() > policyActionUtil.getUtility())) {
							newUtilityArr[col][row].setAction(bestActionUtil.getAction());
							optimal = false;
						}
					}
				}
			}
			k++;

		} while (!optimal);
		
		// Final item in the list is the optimal policy derived by policy iteration
		int latestUtilities = utilityList.size() - 1;
		final UtilityModel[][] optimalPolicy = utilityList.get(latestUtilities);
			    
		// Displays the Maze Environment with Optimal Actions
		DisplayController.printPolicy(optimalPolicy);
		
		// Display the utilities of all the states as a list
		DisplayController.printUtilities(maze, optimalPolicy);
		
		// Display the utilities of all states in the maze
		DisplayController.printUtilitiesMaze(optimalPolicy);
			 	
	}

}
