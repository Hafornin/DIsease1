package disease;

public class Disease {
	
	//Attributes :
	private int infectionTimeDays; //duration of the infection in days
	private double infectionRadius; //distance at which people have a chance of being contaminated
	private double infectionCoeff; //probability for someone at a distance < infectionRadius from a infected person to be themselves infected
	private double r0Exp; //experimental value for r0 (number of persons infected on average by one person while they are sick)
	private double identifiedProportion; //proportion of infected people identified
	private double centralPointTripProba; //probability of going to the central point
	private double socialDistanceRadius; //distance at which individuals repel each other when social distancing is active
	private int groupSize;
	
	//Constructor :
	
	public Disease(int gs, int itd, double ir, double ic, double ip, double cptp, double sdr){
		groupSize = gs;
		infectionTimeDays = itd;
		infectionRadius = ir;
		infectionCoeff = ic;
		r0Exp = 0;
		identifiedProportion =ip;
		centralPointTripProba = cptp;
		socialDistanceRadius = sdr;
	}
	
	//Getters :
	public double getInfectionCoeff(){
		return infectionCoeff;
	}
	
	public double getIdentifiedProportion(){
		return identifiedProportion;
	}
	
	public double getInfectionRadius(){
		return infectionRadius;
	}
	
	public double getCentralPointTripProba(){
		return centralPointTripProba;
	}
	
	public double getInfectionTimeDays(){
		return infectionTimeDays;
	}
	
	public double getTravelProba(){
		return travelProba;
	}
	
	public int getGroupSize(){
		return groupSize;
	}
	
	public double getSocialDistanceRadius(){
		return socialDistanceRadius;
	}
	
	//Setters :
}

