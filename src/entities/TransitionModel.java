package entities;

public class TransitionModel {
	
	double intendedProb = Constants.INTENDED_PROB;
	double unintendedProb = Constants.UNINTENDED_PROB;
	
	//constructor for the Transition model
	TransitionModel(double intendedProb, double unintendedProb){ 
		this.intendedProb = intendedProb;
		this.unintendedProb = unintendedProb;
	}
	
	// Getter for intended probability
	public double getIntendedProb() {
		return intendedProb;
	}

	// Setter for intended probability
	public void setIntendedProb(double intendedProb) {
		this.intendedProb = intendedProb;
	}
	
	// Getter for unintended probability
	public double getUnintendedProb() {
		return unintendedProb;
	}

	// Setter for unintended probability
	public void setUnintendedProb(double unintendedProb) {
		this.unintendedProb = unintendedProb;
	}

}
