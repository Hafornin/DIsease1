/**
 * Each instance of Individual represents a person in our simulation, which can move around and catch the disease.
 */

package disease;
import java.awt.Color;

public class Individual {
	
//ATTRIBUTES :

//=================== Position and displacement of this individual ========================
	/** 
	 * maximum x value (right edge of the box)
	 */
	private final double X_MAX;
	/** 
	 * maximum y value (lower edge of the box)
	 */
	private final double Y_MAX;
	/** 
	 * minimum x value (left edge of the box)
	 */
	private final double X_MIN;
	/** 
	 * minimum y value (upper edge of the box)
	 */
	private final double Y_MIN;
	/** 
	 * maximum speed of this individual
	 */
	private final double V_MAX = 200;
	/** 
	 * initial speed of this individual
	 */
	private final double V_NORM = 150;
	/**
	 * time step to perform the displacement of individuals
	 */
	private final double DELTA_T = 0.01;
	
	
	/**
	 * position of this individual
	 */
	private Vector position;
	/**
	 * velocity of this individual
	 */
	private Vector velocity;
	
//=================== Disease-related characteristics of this individual ========================
	
	/**
	 * "susceptible" OR "infected" OR "recovered"
	 */
	private String state;
	/**
	 * color of the dot : susceptible-green , infected-red , recovered-blue
	 */
	private Color color;
	/**
	 * TRUE if this individual has been identified as contaminated, FALSE otherwise
	 */
	private boolean identified;
	/**
	 * number of days after infection
	 */
	private int infectedTimeDays;
	/**
	 * disease parameters for this individual
	 */
	private Disease disease;
	
//=================== Attributes for implementing social distancing ========================
	
	/**
	 * force applied by other individuals on this individual when social distancing is on
	 */
	private Vector othersForce;
	/**
	 * coefficient defining the extent of social distancing for this individual
	 */
	private double socialDistanceCoeff;
	
	/**
	 * value of socialDistanceCoeff when this individual applies social distancing
	 */
	private double SOCIAL_DISTANCE_COEFF = 10000000;
	
	/**
	 * distance at which individuals repel each other when social distancing is active
	 */
	private int SOCIAL_DISTANCE_RADIUS = 50;
	
//=================== Attributes for implementing a central travel point ========================
	
	/**
	 * time spent in the central point
	 */
	private int timeInCentralPoint;

	
//CONSTRUCTOR :
	
	/**
	 * Creates an instance of Individual.
	 * By default, its state is "susceptible".
	 * Its position and velocity are chosen randomly.
	 * 
	 * @param d disease parameters for this individual
	 * @param xMax maximum x value (right edge of the box)
	 * @param yMax maximum y value (lower edge of the box)
	 * @param xMin minimum x value (left edge of the box)
	 * @param yMin minimum y value (upper edge of the box)
	 */
	
	public Individual(Disease d, double xMax, double yMax, double xMin, double yMin){
		
		//affecting the maximum and minimum x and y values
		X_MAX = xMax;
		Y_MAX = yMax;
		X_MIN = xMin;
		Y_MIN = yMin;
		
		//affecting some disease parameters to this individual
		disease = d;
		
		//setting the initial disease-related state of the individual (by default, the individual is susceptible)
		state = "susceptible"; 
		color = Color.green;
		identified = false;
		infectedTimeDays = 0;
		
		//choosing randomly the initial position and velocity of this individual
		position = new Vector("cartesian",X_MIN+(X_MAX-X_MIN)*Math.random(),Y_MIN+(Y_MAX-Y_MIN)*Math.random());
		velocity = new Vector("polar", V_NORM,2*Math.PI*Math.random());
		
		//initializing the attributes used to implement social distancing
		socialDistanceCoeff = 0;
		othersForce = new Vector();
		
		//initializing the attribute used to implement a central travel point
		timeInCentralPoint = 0;
	}
	
//GETTERS :
	
	/**
	 * Gets the state of this individual
	 * @return the state of this individual ("susceptible" OR "infected" OR "recovered")
	 */
	public String getState(){
		return state;
	}
	
	/**
	 * Gets the color of the dot
	 * @return the color of the dot : susceptible-green , infected-red , recovered-blue
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Gets the position of this individual
	 * @return the position of this individual
	 */
	public Vector getPosition(){
		return position;
	}
	
	/**
	 * Tells wether this individual is identified as infected
	 * @return TRUE if this individual is identified as infected, FALSE otherwise
	 */
	public boolean isIdentified() {
		return identified;
	}
	
	/**
	 * Gets the time spent by this individual in the central point
	 * @return the time spent by this individual in the central point since it arrived there
	 */
	public int getTimeInCentralPoint() {
		return timeInCentralPoint;
	}


	
//SETTERS:
	
	/**
	 * Sets the extent of social distancing for this individual
	 * @param sdc the coefficient of social distancing for this individual
	 */
	public void setSocialDistanceCoeff(double sdc){
		socialDistanceCoeff = sdc*SOCIAL_DISTANCE_COEFF;
		velocity.setNorm(V_NORM);
	}	
	
	/**
	 * Sets the time spent in the central point by this individual
	 * @param t the time spent in the central point by this individual
	 */
	public void setTimeInCentralPoint(int t) {
		timeInCentralPoint = t;
	}

	
//METHODS :
	
//=================== Updating the disease-related parameters of the individuals ========================
	
	/**
	 * Method updating the state and color of an individual from their infection time
	 */
	public void updateState(){
		if(infectedTimeDays>disease.getInfectionTimeDays()){
			state = "recovered";
			color = Color.blue;
		}else if(infectedTimeDays>0){
			state = "infected";
			color = Color.red;
		}
	}
	
	/**
	 * Method updating the infection time of an individual
	 */
	public void updateTime(){
		if(state != "susceptible"){
			infectedTimeDays++;
		}
	}
		
	
//=================== Infecting individuals ========================
	
	/**
	 * Method telling wether an individual belongs or not to this individual's radius of infection
	 * @param i the individual for which we want to know if it belongs this individual's infection radius
	 * @return TRUE if the individual i belongs to this individual's infection radius, FALSE otherwise
	 */
	public boolean isCloseEnoughtoInfect(Individual i){
		if(position.distance(i.getPosition()) <= disease.getInfectionRadius()){
			return true;
		}else{
			return false;
		}
	}
			
	/**
	 * Method to decide wether this individual infects another individual belonging to its radius of infection
	 * @param i the individual that may be infected by this individual
	 */
	public void infect(Individual i){
		double test = Math.random();
		if(this.state=="infected" && i.state=="susceptible" && test<disease.getInfectionCoeff()){
			i.infectedTimeDays = 1; //setting the infection time of the newly infected individual to 1
		}
	}
	
	/**
	 * Method to infect manually this individual (to infect the first individuals to transmit the disease in our simulation)
	 */
	public void initialInfect(){
		state = "infected";
		infectedTimeDays = 1;
	}
	
	/**
	 * Method deciding wether this individual is identified as infected depending on the probability of identifying infected people stored in disease
	 */
	public void identify(){
		double test = Math.random();
		if(state=="infected" && test<disease.getIdentifiedProportion()){
			identified = true;
		}
	}
	
//=================== Moving this individual ========================	
	
	/**
	 * Method to check that the next position of this individual is inside the box
	 * @param pos the next position of this individual
	 */
	public void checkWalls(Vector pos){
		//checking that this individual is not on the left of the box
		if(X_MIN>pos.getX()){
			position.setX(X_MIN); //setting the position of this individual to the left edge of the box
			velocity.setX(Math.abs(velocity.getX())); //setting the velocity of this individual to the right
		}
		//checking that this individual is not on the right of the box
		if(X_MAX<pos.getX()){
			position.setX(X_MAX); //setting the position of this individual to the right edge of the box
			velocity.setX(-Math.abs(velocity.getX())); //setting the velocity of this individual to the left
		}
		//checking that this individual is not above the box
		if(Y_MIN>=pos.getY()){
			position.setY(Y_MIN); //setting the position of this individual to the upper edge of the box
			velocity.setY(Math.abs(velocity.getY())); //setting the velocity of this individual downwards
		}
		//checking that this individual is not below the box
		if(Y_MAX<=pos.getY()){
			position.setY(Y_MAX); //setting the position of this individual to the lower edge of the box
			velocity.setY(-Math.abs(velocity.getY())); //setting the velocity of this individual upwards
		}
	}
	
	/**
	 * Method updating the position of this individual
	 */
	public void updatePosition(){
		Vector dV = new Vector(); //acceleration of this individual
		dV.add(othersForce); //adding the force applied by others on this individual when social distancing in on
		dV.multiply(DELTA_T); //multiplying this force by the time step to get the speed variation of this individual
		velocity.add(dV); //adding the variation to the current velocity
		//making sure that the individual does not go faster than the maximum speed
		if(velocity.getNorm()>V_MAX){
			velocity.setNorm(V_MAX);
		}
		Vector displacement = new Vector(); //initializing the displacement of the individual from its current position
		displacement.add(velocity); 
		displacement.multiply(DELTA_T); //adding the velocity times the time step to the displacement
		position.add(displacement); //moving the individual from its current position to its new position after displacement
		checkWalls(position); //checking that the individual does not get out of the box
		
		//setting the force applied by others on this individual when social distancing in on to 0
		othersForce.setNorm(0);
		othersForce.setAngle(0);
	}


//=================== Implementing social distancing ========================		
	
	/**
	 * Method to make this individual avoid other individuals
	 */
	public void startSocialDistancing() {
		socialDistanceCoeff = SOCIAL_DISTANCE_COEFF;
	}
	
	/**
	 * Method to make this individual ignore social distancing
	 */
	public void stopSocialDistancing() {
		socialDistanceCoeff = 0;
	}
	
	/**
	 * Method telling wether an individual belongs or not to this individual's social distance radius
	 * @param i the individual for which we want to know wether it belongs or not to this individual's social distance radius
	 * @return TRUE if the individual i belongs to this individual's social distance radius, FALSE otherwise
	 */
	public boolean isCloseEnoughtoRepel(Individual i){
		if(position.distance(i.getPosition()) <= SOCIAL_DISTANCE_RADIUS){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * Method implementing repulsion between individuals for social distancing
	 * @param i the individual that repels this individual
	 */
	public void repel(Individual i){
		Vector repelForce = new Vector(i.getPosition(), position); //the repulsive force is a Vector collinear to the vector going from the repelling individual to this individual
		repelForce.multiply(socialDistanceCoeff/Math.pow(position.distance(i.getPosition()),4)); //the repulsive force is proportional to the social distance coefficient of this individual and inversely proportional to the distance between the 2 individuals raised to the power of 4
		othersForce.add(repelForce); //the repulsive force is added to the total force applied by other individuals on this individual
	}

//=================== Implementing travel to a central point ========================	
	
	/**
	 * Method to move this individual to the center of the box
	 */
	public void takeToCenter() {
		position.setX(X_MIN+(X_MAX-X_MIN)/2);
		position.setY(Y_MIN+(Y_MAX-Y_MIN)/2);
	}
	
	/**
	 * Method to set randomly the velocity of this individual
	 */
	public void setRandomVelocity() {
		velocity = new Vector("polar", V_NORM,2*Math.PI*Math.random());
	}
	
}


