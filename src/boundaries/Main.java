package boundaries;

import controllers.DisplayController;
import entities.Constants;
import entities.CreateEnvironment;
import entities.StateModel;

public class Main {
	
	private static StateModel[][] maze;	
	public static CreateEnvironment createEnvironment = null;

	public static void main(String[] args) {
		// Initialize the maze environment
		createEnvironment = new CreateEnvironment(Constants.WIDTH, Constants.HEIGHT);
		maze = createEnvironment.getMaze();
		
		// Displays the Maze Environment
		DisplayController.printMaze(maze);
	}

}
