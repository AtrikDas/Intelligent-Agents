package controllers;

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
	
	// Display the policy, i.e. the action to be taken at each state
	public static void printPolicy(final UtilityModel[][] utilityArr) {
		
		System.out.println("Printing Maze with Actions...");	
		System.out.println("-------------------------");
		
		for (int row = 0; row < Constants.WIDTH; row++) {
			System.out.print("|");
			for(int col = 0 ; col < Constants.WIDTH ; col++) {
				
				String utility = utilityArr[col][row].getStrAction();
				int n = (9 - utility.length())/2;
				String str = String.format("%1$"+n+"s", "");
				String str1 = String.format("%1$"+(n-1)+"s", "");
				System.out.print("\t"+str+utility+str1+"\t|");
				
//				if (((m.maze[i][j]).getS())!=SquareType.wall)
//					System.out.print("\t"+this.policy[i][j].getAction().get_display()+"\t|");	
//				else
//					System.out.print("\t"+(m.maze[i][j].getS()).get_display()+"\t|");		
				
			}
			System.out.println("");
			System.out.println("-------------------------");

//			sb.append("|");
//			for (int col = 0; col < Const.NUM_COLS; col++) {
//				String util = utilArr[col][row].getActionStr();
//				int n = (9 - util.length())/2;
//				String str = String.format("%1$"+n+"s", "");
//				String str1 = String.format("%1$"+(n-1)+"s", "");
//				sb.append(str + util + str1 + "|");
//			}
//
//			sb.append("\n|");
//			for(int col = 0 ; col < Const.NUM_COLS ; col++) {
//				sb.append("--------|".replace('-', ' '));
//			}
//			sb.append("\n");
//
//			sb.append("|");
//			for(int col = 0 ; col < Const.NUM_COLS ; col++) {
//				sb.append("--------|");
//			}
//			sb.append("\n");
		}
	}
		
}
