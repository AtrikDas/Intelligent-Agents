package entities;

public class UtilityModel implements Comparable<UtilityModel>{
	
	private double utility = 0.000;
	private ActionModel action = null;
	
	// Constructor for UtilityModel no arguments
	public UtilityModel() {
		action = null;
		utility = 0.000;
	}
	
	// Constructor for UtilityModel with arguments
	public UtilityModel(ActionModel action, double utility) {
		this.action = action;
		this.utility = utility;
	}

	// Getter for action
	public ActionModel getAction() {
		return action;
	}
	
	// Getter of string representation of action
	public String getStrAction() {

		// No action (stay in the same square) at wall, otherwise return one of the 4 possible actions
		return action != null ? action.toString() : "-";
	}

	// Setter for action
	public void setAction(ActionModel action) {
		this.action = action;
	}

	// Getter for utility
	public double getUtility() {
		return utility;
	}

	// Setter for utility
	public void setUtility(double utility) {
		this.utility = utility;
	}
	
	@Override
	public int compareTo(UtilityModel diffPair) {

		// Order utility values in descending order
		return ((Double) diffPair.getUtility()).compareTo(getUtility());
	}

}
