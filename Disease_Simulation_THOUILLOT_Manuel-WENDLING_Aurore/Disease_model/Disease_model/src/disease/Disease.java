/**
 * This class represents the disease that is propagating.
 * It contains a number of parameters related to the disease.
 */
package disease;

public class Disease {
	
//ATTRIBUTES :
	/**
	 * the duration of the infection
	 */
	private int infectionTime;
	/**
	 * the distance at which people have a chance of being contaminated
	 */
	private double infectionRadius;
	/**
	 * the probability for someone at a distance less than infectionRadius from a infected person to be themselves infected
	 */
	private double infectionCoeff;
	/**
	 * the proportion of infected people identified
	 */
	private double identifiedProportion;
	/**
	 * the probability of going to the central point
	 */
	private double centralPointTripProba;
	/**
	 * the coefficient representing the amount of travel between groups
	 */
	private double travelBetweenGroups;
	/**
	 * the coefficient representing the extent of social distancing
	 */
	private double socialDistanceCoeff;
	/**
	 * the mortality from this disease
	 */
	private double mortality;
	/**
	 * the size of the groups
	 */
	private int groupSize;
	
	
//CONSTRUCTOR :
	
	/**
	 * Creates an instance of Disease
	 * @param gs the group size
	 * @param it the infection time
	 */
	public Disease(int gs, int it){
		//setting all the variables
		groupSize = gs;
		infectionTime = it;
		infectionRadius = 10;
		infectionCoeff = 0.5;
		identifiedProportion =0.0;
		centralPointTripProba = 0.0;
		travelBetweenGroups = 0.0;
		mortality = 0.0;
	}
	
	
	
//GETTERS :
	
	/**
	 * Gets the probability for someone at a distance less than infectionRadius from a infected person to be themselves infected
	 * @return the probability for someone at a distance less than infectionRadius from a infected person to be themselves infected
	 */
	public double getInfectionCoeff(){
		return infectionCoeff;
	}
	
	/**
	 * Gets the proportion of infected people identified
	 * @return the proportion of infected people identified
	 */
	public double getIdentifiedProportion(){
		return identifiedProportion;
	}
	
	/**
	 * Gets the distance at which people have a chance of being contaminated
	 * @return the distance at which people have a chance of being contaminated
	 */
	public double getInfectionRadius(){
		return infectionRadius;
	}
	
	/**
	 * Gets the probability of going to the central point
	 * @return the probability of going to the central point
	 */
	public double getCentralPointTripProba(){
		return centralPointTripProba;
	}
	
	/**
	 * Gets the duration of the infection
	 * @return the duration of the infection
	 */
	public int getInfectionTime(){
		return infectionTime;
	}
	
	/**
	 * Gets the size of the groups
	 * @return the size of the groups
	 */
	public int getGroupSize(){
		return groupSize;
	}
	
	/**
	 * Gets the coefficient representing the amount of travel between groups
	 * @return the coefficient representing the amount of travel between groups
	 */
	public double getTravelBetweenGroups() {
		return travelBetweenGroups;
	}
	
	/**
	 * Gets the coefficient representing the extent of social distancing
	 * @return the coefficient representing the extent of social distancing
	 */
	public double getSocialDistanceCoeff() {
		return socialDistanceCoeff;
	}
	
	/**
	 * Gets the mortality from this disease
	 * @return the mortality from this disease
	 */
	public double getMortality() {
		return mortality;
	}
	
	
//SETTERS :
	
	/**
	 * Sets the probability for someone at a distance less than infectionRadius from a infected person to be themselves infected
	 * @param ic the probability for someone at a distance less than infectionRadius from a infected person to be themselves infected
	 */
	public void setInfectionCoeff(double ic){
		infectionCoeff = ic;
	}
	
	/**
	 * Sets the proportion of infected people identified
	 * @param ip the proportion of infected people identified
	 */
	public void setIdentifiedProportion(double ip){
		identifiedProportion = ip;
	}
	
	/**
	 * Sets the probability of going to the central point
	 * @param cptp the probability of going to the central point
	 */
	public void setCentralPointTripProba(double cptp){
		centralPointTripProba = cptp;
	}
	
	/**
	 * Sets the duration of the infection
	 * @param it the duration of the infection
	 */
	public void setInfectionTime(int it){
		infectionTime = it;
	}
	
	/**
	 * Sets the size of the groups
	 * @param gs the size of the groups
	 */
	public void setGroupSize(int gs) {
		groupSize = gs;
	}
	
	/**
	 * Sets the coefficient representing the amount of travel between groups
	 * @param tbg the coefficient representing the amount of travel between groups
	 */
	public void setTravelBetweenGroups(double tbg) {
		travelBetweenGroups = tbg;
	}
	
	/**
	 * Sets the coefficient representing the extent of social distancing
	 * @param sdc the coefficient representing the extent of social distancing
	 */
	public void setSocialDistanceCoeff(double sdc) {
		socialDistanceCoeff = sdc;
	}
	
	/**
	 * Sets the mortality from this disease
	 * @param m the mortality from this disease
	 */
	public void setMortality(double m) {
		mortality = m;
	}
}

