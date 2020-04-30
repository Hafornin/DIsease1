import java.awt.Color;

public class Individual {
	
//Attributes :
	private final int X_MAX; //maximum x value (edge of the box)
	private final int Y_MAX; //maximum y value (edge of the box)
	private final int X_MIN; //minimum x value (edge of the box)
	private final int Y_MIN; //minimum y value (edge of the box)
	
	private double currentX; //current x coordinate
	private double currentY; //current y coordinate
	private double nextX; //next x coordinate
	private double nextY; //next y coordinate
	private double angle;
	
	private String state; //susceptible OR infected OR recovered 
	private Color color;
	private boolean identified; //TRUE if the individual has been identified as contaminated, FALSE otherwise
	private int infectedTimeDays; //number of days after infection
	private double socialDistanceCoeff; // coefficient defining the extent of social distancing for this individual
	private Disease disease;
	
//Constructor :
	public Individual(Disease d, int xMax, int yMax, int xMin, int yMin){
		X_MAX = xMax;
		Y_MAX = yMax;
		X_MIN = xMin;
		Y_MIN = yMin;
	
		currentX = X_MIN+(X_MAX-X_MIN)*Math.random();
		currentY = Y_MIN+(Y_MAX-Y_MIN)*Math.random();
		nextX = currentX;
		nextY = currentY;
		angle = 2*Math.PI*Math.random();
		
		disease = d;
		state = "susceptible";
		color = Color.green;
		identified = false;
		infectedTimeDays = 0;
		socialDistanceCoeff = 40;
	}
	
//Getters :
	public String getState(){
		return state;
	}
	
	public Color getColor(){
		return color;
	}
	
	public double getX(){
		return currentX;
	}
	
	public double getY(){
		return currentY;
	}

	
//Setters :
	public void setSocialDistanceCoeff(double sdc){
		socialDistanceCoeff = sdc;
	}	
	
	
//Methods :
	
	
//updating the parameters of the individuals :
	
	//Method updating the state of an individual from their infection time
	public void updateState(){
		if(infectedTimeDays>disease.getInfectionTimeDays()){
			state = "recovered";
			color = Color.gray;
		}else if(infectedTimeDays>0){
			state = "infected";
			color = Color.red;
		}
	}
	
	//Method updating the infection time of an individual
	public void updateTime(){
		if(state != "susceptible"){
			infectedTimeDays++;
		}
	}
	
	//Method updating the position of the individual
	public void updatePosition(){
		checkWalls(nextX,nextY);
	}
	
//infecting an individual :
	
	//Method telling wether an individual belongs or not to another's radius of infection
	public boolean isCloseEnoughtoInfect(Individual individual){
		if(distance(individual) <= disease.getInfectionRadius()){
			return true;
		}else{
			return false;
		}
	}
			
	//Method to decide wether an individual infects another individual belonging to its radius of infection
	public void infect(Individual individual){
		double test = Math.random();
		if(this.state=="infected" && individual.state=="susceptible" && test<disease.getInfectionCoeff()){
			individual.infectedTimeDays = 1;
		}
	}
	
	//Method to infect an individual
	public void infect(){
		state = "infected";
		infectedTimeDays = 1;
	}
	
	//Method deciding wether a person is identified as infected
	public boolean identify(){
		double test = Math.random();
		if(state=="infected" && test<disease.getIdentifiedProportion()){
			identified = true;
		}
		return identified;
	}
	
//Moving the individuals :	


	//Method defining the normal movement of each individual
	public void planTrip(){
		double test = Math.random();
		double sign = 1;
		if(test<0.5){
			sign = -1;
		}
		angle = angle + sign*0.2*Math.PI*Math.random();
		double xi = currentX + disease.getDistanceTraveled()*Math.cos(angle);
		double yi = currentY + disease.getDistanceTraveled()*Math.sin(angle);
		checkWalls(xi,yi);
	}
	
	
	//Method checking that the individuals do not get out of the box
	public void checkWalls(double xi, double yi){
		if(X_MIN>xi){
			currentX = 2*X_MIN-xi;
			angle = angle + Math.PI;
		}else if(X_MAX<xi){
			currentX = 2*X_MAX-xi;
			angle = angle + Math.PI;
		}else{
			currentX = xi;
			nextX = currentX;
		}
		if(Y_MIN>yi){
			currentY = 2*Y_MIN-yi;
			angle = angle + Math.PI;
		}else if(Y_MAX<yi){
			currentY = 2*Y_MAX-yi;
			angle = angle + Math.PI;
		}else{
			currentY = yi;
			nextY = currentY;
		}		
	}


//Social distancing :	


	//Method computing the current distance between 2 individuals
	public double distance(Individual individual){
		double distance = Math.sqrt((this.currentX-individual.currentX)*(this.currentX-individual.currentX) + (this.currentY-individual.currentY)*(this.currentY-individual.currentY));
		return distance;
	}
	
	
	//Method telling wether an individual belongs or not to another's social distance radius at the next iteration
	public boolean isCloseEnoughtoRepel(Individual individual){
		if(distance(individual) <= disease.getSocialDistanceRadius()){
			return true;
		}else{
			return false;
		}
	}
	
	
	//Method implementing social distancing 
	public void repel(Individual individual){
		double xi = this.nextX - (individual.currentX-this.currentX)*this.socialDistanceCoeff;
		double yi = this.nextY - (individual.currentY-this.currentY)*this.socialDistanceCoeff;
	}
	
	
		
	
}

