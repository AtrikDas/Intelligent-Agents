package boundaries;

import entities.Constants;
import entities.CreateEnvironment;

public class Main {
	
	public static CreateEnvironment createEnvironment = null;

	public static void main(String[] args) {
		//Initialize the maze environment
		createEnvironment = new CreateEnvironment(Constants.WIDTH, Constants.HEIGHT);
	}

}
