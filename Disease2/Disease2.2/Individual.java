import java.awt.Color;

public class Individual {
	
//Attributes :
<<<<<<< HEAD
	private final double X_MAX; //maximum x value (edge of the box)
	private final double Y_MAX; //maximum y value (edge of the box)
	private final double X_MIN; //minimum x value (edge of the box)
	private final double Y_MIN; //minimum y value (edge of the box)
	private final double V_NORM = 10;
	private final double DELTA_T = 0.01;
	
	private Position position;
	private Vector velocity;
	private Vector othersForce;
=======
	private final int X_MAX; //maximum x value (edge of the box)
	private final int Y_MAX; //maximum y value (edge of the box)
	private final int X_MIN; //minimum x value (edge of the box)
	private final int Y_MIN; //minimum y value (edge of the box)
	
	private double currentX; //current x coordinate
	private double currentY; //current y coordinate
	private double nextX; //next x coordinate
	private double nextY; //next y coordinate
	private double angle;
>>>>>>> DiseaseModelling
	
	private String state; //susceptible OR infected OR recovered 
	private Color color;
	private boolean identified; //TRUE if the individual has been identified as contaminated, FALSE otherwise
	private int infectedTimeDays; //number of days after infection
	private double socialDistanceCoeff; // coefficient defining the extent of social distancing for this individual
	private Disease disease;
	
//Constructor :
<<<<<<< HEAD
	public Individual(Disease d, double xMax, double yMax, double xMin, double yMin){
=======
	public Individual(Disease d, int xMax, int yMax, int xMin, int yMin){
>>>>>>> DiseaseModelling
		X_MAX = xMax;
		Y_MAX = yMax;
		X_MIN = xMin;
		Y_MIN = yMin;
<<<<<<< HEAD
		
		position = new Position(X_MIN+(X_MAX-X_MIN)*Math.random(),Y_MIN+(Y_MAX-Y_MIN)*Math.random());
		velocity = new Vector("polar", V_NORM,2*Math.PI*Math.random());
		othersForce = new Vector();
=======
	
		currentX = X_MIN+(X_MAX-X_MIN)*Math.random();
		currentY = Y_MIN+(Y_MAX-Y_MIN)*Math.random();
		nextX = currentX;
		nextY = currentY;
		angle = 2*Math.PI*Math.random();
>>>>>>> DiseaseModelling
		
		disease = d;
		state = "susceptible";
		color = Color.green;
		identified = false;
		infectedTimeDays = 0;
<<<<<<< HEAD
		socialDistanceCoeff = 0;
=======
		socialDistanceCoeff = 100000000;
>>>>>>> DiseaseModelling
	}
	
//Getters :
	public String getState(){
		return state;
	}
	
	public Color getColor(){
		return color;
	}
	
<<<<<<< HEAD
	public Position getPosition(){
		return position;
=======
	public double getX(){
		return currentX;
	}
	
	public double getY(){
		return currentY;
>>>>>>> DiseaseModelling
	}

	
//Setters :
<<<<<<< HEAD
=======
	public void setSocialDistanceCoeff(double sdc){
		socialDistanceCoeff = sdc;
	}	
	
>>>>>>> DiseaseModelling
	
//Methods :
	
//Methods :
	
<<<<<<< HEAD
	
=======
>>>>>>> DiseaseModelling
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
	
<<<<<<< HEAD
	//Method updating the position of an individual
	public void updatePosition(){
		Vector displacement = new Vector();
		displacement.add(velocity);
		displacement.add(othersForce);
		velocity.setAngle(displacement.getAngle());
		Position newPos = new Position(position.getX()+displacement.getX(),position.getY()+displacement.getY());
		checkWalls(newPos);
=======
	//Method updating the position of the individual
	public void updatePosition(){
		checkWalls(nextX,nextY);
>>>>>>> DiseaseModelling
	}
		
	
//infecting an individual :
	
	//Method telling wether an individual belongs or not to another's radius of infection
	public boolean isCloseEnoughtoInfect(Individual individual){
		if(position.distance(individual.getPosition()) <= disease.getInfectionRadius()){
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
<<<<<<< HEAD
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
		velocity.setAngle(velocity.getAngle() + sign*0.1*Math.PI*Math.random());
	}
	
	
	//Method checking that the individuals do not get out of the box
	public void checkWalls(Position pos){
		if(X_MIN>pos.getX()){
			position.setX(X_MIN);
			velocity.setX(Math.abs(velocity.getX()));
		}else if(X_MAX<pos.getX()){
			position.setX(X_MAX);
			velocity.setX(-Math.abs(velocity.getX()));
		}else{
			position.setX(pos.getX());
		}
		if(Y_MIN>pos.getY()){
			position.setY(Y_MIN);
			velocity.setY(Math.abs(velocity.getY()));
		}else if(Y_MAX<pos.getY()){
			position.setY(Y_MAX);
			velocity.setY(-Math.abs(velocity.getY()));
		}else{
			position.setY(pos.getY());
		}		
	}


//Social distancing :	
	
	
	//Method telling wether an individual belongs or not to another's social distance radius
	public boolean isCloseEnoughtoRepel(Individual individual){
		if(position.distance(individual.getPosition()) <= disease.getSocialDistanceRadius()){
			return true;
		}else{
			return false;
		}
	}
	
	
	//Method implementing social distancing 
	public void repel(Individual individual){
		Vector repelForce = new Vector(individual.getPosition(), position);
		repelForce.multiply(socialDistanceCoeff/position.distance(individual.getPosition()));
		othersForce.add(repelForce);
=======
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
		double xi = this.nextX - (individual.currentX-this.currentX)*this.socialDistanceCoeff/distance(individual);
		double yi = this.nextY - (individual.currentY-this.currentY)*this.socialDistanceCoeff/distance(individual);
>>>>>>> DiseaseModelling
	}
	
	
		
	
}

