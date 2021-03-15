package controllers;

import entities.UtilityModel;
import entities.StateModel;
import entities.ActionModel;

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

		private static double getEastUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			// TODO Auto-generated method stub
			return 0;
		}

		private static double getWestUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			// TODO Auto-generated method stub
			return 0;
		}

		private static double getSouthUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			// TODO Auto-generated method stub
			return 0;
		}

		private static double getNorthUtility(int col, int row, UtilityModel[][] curUtilityArr, StateModel[][] maze) {
			// TODO Auto-generated method stub
			return 0;
		}
}
