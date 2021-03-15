package boundaries;

import controllers.DisplayController;
import controllers.IterationController;

import entities.Constants;
import entities.CreateEnvironment;
import entities.StateModel;
import entities.UtilityModel;

import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static StateModel[][] maze;	
	public static CreateEnvironment createEnvironment = null;

	public static void main(String[] args) {
		// Initialize the maze environment
		createEnvironment = new CreateEnvironment(Constants.WIDTH, Constants.HEIGHT);
		maze = createEnvironment.getMaze();
		
		// Displays the Maze Environment
		DisplayController.printMaze(maze);
		
		// Allow user to choose which iteration they want to apply
		Scanner scanObj = new Scanner(System.in);
	    System.out.println("Enter 1 for Value Iteration or 2 for Policy Iteration");
	    String choice = scanObj.nextLine();
	    
	    if (choice.equals("1")) {
	    	System.out.println("Commencing Value Iteration...");
	    	IterationController.valueIteration(maze);
	    } else if (choice.equals("2")) {
	    	System.out.println("Commencing Policy Iteration...");
	    	IterationController.policyIteration(maze);
	    }
	    
	    
	}

}
