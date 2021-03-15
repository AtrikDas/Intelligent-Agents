package entities;

public class Constants {

		// Rewards for each type of square for the maze
		public static final double EMPTY_SQUARE = -0.040;
		public static final double GREEN_SQUARE = +1.000;
		public static final double BROWN_SQUARE = -1.000;
		public static final double WALL_SQUARE = 0.000;
		public static final double MAX_REWARD = 1.000;
		
		// Dimensions of the maze
		public static final int WIDTH = 6;
		public static final int HEIGHT = 6;

		// Probabilities for Transition model 
		public static final double INTENDED_PROB = 0.800;
		public static final double UNINTENDED_PROB = 0.100;
		
		// Discount factor
		public static final double DISCOUNT =  0.990;
		
		// Constant c
		public static final double C = 30;	//constant parameter to adjust the maximum error allowed
		
		// Epsilon value
		public static final double EPSILON = C * MAX_REWARD;
		
		// Constant determining how many times bellman equation is run to produce utility estimate
		public static final int K = 10;
		
		// Upper bound for Utility
		public static final Object MAX_UTILITY_LIMIT = MAX_REWARD / (1 - DISCOUNT);
		
		// Special squares coordinates
		public static final String GREEN_COORDS = "0,0 2,0 5,0 3,1 4,2 5,3";
		public static final String BROWN_COORDS = "1,1 5,1 2,2 3,3 4,4";
		public static final String WALL_COORDS = "1,0 4,1 1,4 2,4 3,4";
		public static final String AGENT_COORD = "2,3";

}
