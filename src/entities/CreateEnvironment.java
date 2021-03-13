package entities;

import java.util.Random;

public class CreateEnvironment {
	
	private StateModel[][] maze = null;
	public int row;
	public int column;
	
	//Constructor for CreateEnvironment
	public CreateEnvironment(int row, int column) {

		maze = new StateModel[Constants.WIDTH][Constants.HEIGHT];
		this.row = row;
		this.column = column;
		setMaze();
	}
	
	//Constructor for CreateRandomEnvironment
	public void CreateRandomEnvironment(int row, int column) {

		maze = new StateModel[Constants.WIDTH][Constants.HEIGHT];
		this.row = row;
		this.column = column;
		setRandomMaze();
	}
	
	//Getter for maze
	public StateModel[][] getMaze() {
		return maze;
	}
	
	//Setter for maze (builds the maze)
	public void setMaze() {

		// Initialize all squares with reward of -0.040
		for(int row = 0; row < Constants.WIDTH; row++) {
			for(int col = 0; col < Constants.HEIGHT; col++) {
				maze[col][row] = new StateModel(Constants.EMPTY_SQUARE);
			}
		}

		// Set all green squares with +1.000 reward
		String[] coordsArrGreen = Constants.GREEN_COORDS.split(" ");
		
		for(int i = 0; i < coordsArrGreen.length; i++) {
			String [] coords = coordsArrGreen[i].split(",");
			maze[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setReward(Constants.GREEN_SQUARE);
		}		

		// Set all brown squares with -1.000 reward
		String[] coordsArrBrown = Constants.BROWN_COORDS.split(" ");
		
		for(int i = 0; i < coordsArrBrown.length; i++) {
			String [] coords = coordsArrBrown[i].split(",");
			maze[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setReward(Constants.BROWN_SQUARE);
		}
		
		// Set all walls with 0.000 reward and make them impassable
		String[] coordsArrWall = Constants.WALL_COORDS.split(" ");
		
		for(int i = 0; i < coordsArrWall.length; i++) {
			String [] coords = coordsArrWall[i].split(",");
			maze[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setReward(Constants.WALL_SQUARE);
			maze[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setIsWall(true);
		}
	}
	
	//Setter for random maze (builds the random maze)
		public void setRandomMaze() {
			
			Random rand = new Random();
			
			// Initialize all squares with reward of -0.040
			for(int row = 0; row < Constants.WIDTH; row++) {
				for(int col = 0; col < Constants.HEIGHT; col++) {
					maze[col][row] = new StateModel(Constants.EMPTY_SQUARE);
				}
			}
			
			//Randomly assign each square with green, brown, and wall rewards
			for (int row = 0; row < Constants.WIDTH; row++){
				for(int col = 0; col < Constants.HEIGHT; col++) {
					switch(rand.nextInt(3)) {
					case 0:
						if (rand.nextInt(2) == 0) {
						maze[row][col].setReward(Constants.GREEN_SQUARE);
						}
						break;
					case 1:
						if (rand.nextInt(2) == 0) {
						maze[row][col].setReward(Constants.BROWN_SQUARE);
						}
						break;
					case 2:
						if (rand.nextInt(2) == 0) {
						maze[row][col].setReward(Constants.WALL_SQUARE);
						maze[row][col].setIsWall(true);
						}
						break;
					}					
				}
			}
			
			//If the agent coordinate is set with something other than empty reward, reset it back to empty reward
			String [] coordsAgent = Constants.AGENT_COORD.split(",");
			maze[Integer.parseInt(coordsAgent[0])][Integer.parseInt(coordsAgent[1])].setReward(Constants.EMPTY_SQUARE);
			
		}

}
