package controllers;

import entities.Constants;
import entities.StateModel;

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
}
