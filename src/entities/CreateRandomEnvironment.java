package entities;

import java.util.Random;

public class CreateRandomEnvironment {
	
	private StateModel[][] maze = null;
	public int row;
	public int column;
	
	//Constructor for CreateRandomEnvironment
		public CreateRandomEnvironment(int row, int column) {

			maze = new StateModel[Constants.WIDTH][Constants.HEIGHT];
			this.row = row;
			this.column = column;
			setRandomMaze();
		}
		
		//Getter for maze
		public StateModel[][] getRandomMaze() {
			return maze;
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
