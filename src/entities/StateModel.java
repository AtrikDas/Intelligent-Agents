package entities;

public class StateModel {
	
	private double reward = 0.000;
	
	private boolean isWall = false;

	public StateModel(double reward) {
		this.reward = reward;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public boolean getIsWall() {
		return isWall;
	}

	public void setIsWall(boolean isWall) {
		this.isWall = isWall;
	}

}
