package disease;

public class Disease {
	
	//Attributes :
	private int infectionTime; //duration of the infection in days
	private double infectionRadius; //distance at which people have a chance of being contaminated
	private double infectionCoeff; //probability for someone at a distance < infectionRadius from a infected person to be themselves infected
	private double r0Exp; //experimental value for r0 (number of persons infected on average by one person while they are sick)
	private double identifiedProportion; //proportion of infected people identified
	private double centralPointTripProba; //probability of going to the central point
	private double travelBetweenGroups;
	private double socialDistanceCoeff;
	private double mortality;
	private int groupSize;
	
	//Constructor :
	
	public Disease(int gs, int it){
		groupSize = gs;
		infectionTime = it;
		infectionRadius = 10;
		infectionCoeff = 0.5;
		r0Exp = 0;
		identifiedProportion =0.0;
		centralPointTripProba = 0.0;
		travelBetweenGroups = 0.0;
		mortality = 0.0;
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
	
	public double getInfectionTime(){
		return infectionTime;
	}
	
	public int getGroupSize(){
		return groupSize;
	}
	
	public double getTravelBetweenGroups() {
		return travelBetweenGroups;
	}
	
	public double getSocialDistanceCoeff() {
		return socialDistanceCoeff;
	}
	
	public double getMortality() {
		return mortality;
	}
	
	//Setters :
	
	public void setInfectionCoeff(double ic){
		infectionCoeff = ic;
	}
	
	public void setIdentifiedProportion(double ip){
		identifiedProportion = ip;
	}
	
	public void setCentralPointTripProba(double cptp){
		centralPointTripProba = cptp;
	}
	
	public void setInfectionTime(int it){
		infectionTime = it;
	}
	
	public void setGroupSize(int gs) {
		groupSize = gs;
	}
	
	public void setTravelBetweenGroups(double tbg) {
		travelBetweenGroups = tbg;
	}
	
	public void setSocialDistanceCoeff(double sdc) {
		socialDistanceCoeff = sdc;
	}
	
	public void setMortality(double m) {
		mortality = m;
	}
}

