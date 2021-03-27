package controllers;

import java.text.DecimalFormat;

import entities.Constants;
import entities.StateModel;
import entities.UtilityModel;

public class DisplayController {
	
	// Print maze on console
	public static void printMaze(StateModel[][] maze) {										
		
		System.out.println("Printing Maze...");	
		System.out.println("-------------------------");
				
		for (int row = 0; row < Constants.WIDTH; row++){
			System.out.print("|");
			for(int col = 0; col < Constants.HEIGHT; col++) {
				
				StateModel state = maze[col][row];				
				
				// Print "S" to mark starting position of agent
				if (col == Integer.parseInt(String.valueOf(Constants.AGENT_COORD.charAt(0))) && row == Integer.parseInt(String.valueOf(Constants.AGENT_COORD.charAt(Constants.AGENT_COORD.length() - 1)))) {
					System.out.print(" "+"S"+" |");
				// Print "W" for wall
				} else if(state.getIsWall()) {
					System.out.print(" "+"W"+" |");
				// Print " " for empty squares containing -0.040 reward
				} else if(state.getReward() == Constants.EMPTY_SQUARE) {
					System.out.print(" "+" "+" |");
				// Print "+" for green squares containing +1.000 reward
				}else if(state.getReward() == Constants.GREEN_SQUARE) {
					System.out.print(" "+"+"+" |");
				// Print "-" for brown squares containing -1.000 reward
				}else if(state.getReward() == Constants.BROWN_SQUARE) {
					System.out.print(" "+"-"+" |");
				}
			}
			System.out.println("");
			System.out.println("-------------------------");
		}
	}
	
	// Print the policy, i.e. the action to be taken at each state
	public static void printPolicy(final UtilityModel[][] utilityArr) {
		
		System.out.println("Printing Maze with Actions...");	
		System.out.println("-------------------------------------------------------------------");
		
		for (int row = 0; row < Constants.WIDTH; row++) {
			System.out.print("|");
			for(int col = 0 ; col < Constants.WIDTH ; col++) {
				
				String utility = utilityArr[col][row].getStrAction();
				int n = (9 - utility.length())/2;
				String str = String.format("%1$"+n+"s", "");
				String str1 = String.format("%1$"+(n-1)+"s", "");
				System.out.print(" "+str+utility+str1+" |");				
				
			}
			System.out.println("");
			System.out.println("-------------------------------------------------------------------");

		}
	}
	
	// Display the utilities of all the states
		public static void printUtilities(final StateModel[][] maze, final UtilityModel[][] utilityArr) {
			System.out.println("Utility Values of all the States");
			for (int col = 0; col < Constants.WIDTH; col++) {
				for (int row = 0; row < Constants.HEIGHT; row++) {

					if (!maze[col][row].getIsWall()) {
						String utility = String.format("%.8g", utilityArr[col][row].getUtility());
						System.out.println("(" + col + ", " + row + "): " + utility + "\n");
					}
				}
			}
		}

		// Display the utilities of all the states in the maze
		public static void printUtilitiesMaze(final UtilityModel[][] utilityArr) {

			System.out.println("Utilities of all the States in the Maze");
			System.out.println("-------------------------------------------------------");
			
			String pattern = "00.000";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			
			for (int row = 0; row < Constants.WIDTH; row++) {
				System.out.print("|");
				for(int col = 0 ; col < Constants.WIDTH ; col++) {			
					System.out.print(String.format(" %s |",
					decimalFormat.format(utilityArr[col][row].getUtility()).substring(0, 6)));
				}
				System.out.println("");
				System.out.println("-------------------------------------------------------");
			}
		}		
}
