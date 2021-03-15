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
	    	
	    	// Print relevant information 
	    	System.out.println("Discount Factor\t\t" + ":\t" + Constants.DISCOUNT + "\n");
	    	System.out.println("Utility Upper Bound\t" + ":\t" + String.format("%.5g", Constants.MAX_UTILITY_LIMIT) + "\n");
	    	System.out.println("Max Reward(Rmax)\t" + ":\t" + Constants.MAX_REWARD + "\n");
	    	System.out.println("Constant 'c'\t\t" + ":\t" + Constants.C + "\n");
	    	System.out.println("Epsilon Value(c * Rmax)\t" + ":\t" + Constants.EPSILON + "\n");	    	
	    	
	    	IterationController.valueIteration(maze);
	    	
	    	System.out.println("Convergence Threshold\t:\t" + String.format("%.5f", IterationController.convergeThreshold) + "\n\n");
	    	
	    } else if (choice.equals("2")) {
	    	System.out.println("Commencing Policy Iteration...");
	    	
	    	// Print relevant information 
	    	System.out.println("Discount Factor\t\t" + ":\t" + Constants.DISCOUNT + "\n");
	    	System.out.println("k\t\t:\t" + Constants.K + "\n\n");
	    	
	    	IterationController.policyIteration(maze);
	    }
	    
	    System.out.println("Total Number of Iterations: " + IterationController.k + "\n");
	    
	    
	}

}
