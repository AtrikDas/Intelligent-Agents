package entities;

public class TransitionModel {
	
	double intendedProb;
	double unintendedProb;
	
	//constructor for the Transition model
	TransitionModel(double intendedProb, double unintendedProb){ 
		this.intendedProb = intendedProb;
		this.unintendedProb = unintendedProb;
	}

}
