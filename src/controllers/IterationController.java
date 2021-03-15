package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.StateModel;
import entities.UtilityModel;
import entities.Constants;

public class IterationController {
	
	private static List<UtilityModel[][]> utilityList;
	private static double convergeThreshold;
	private static int k = 0;

	public static void valueIteration(StateModel[][] maze) {
		
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
		
	}

	public static void policyIteration(StateModel[][] maze) {
				
	}

}
