package disease;
import java.awt.Color;

public class Individual {
	
//Attributes :

	private final double X_MAX; //maximum x value (edge of the box)
	private final double Y_MAX; //maximum y value (edge of the box)
	private final double X_MIN; //minimum x value (edge of the box)
	private final double Y_MIN; //minimum y value (edge of the box)
	private final double V_MAX = 200;
	private final double V_NORM = 150;
	private final double DELTA_T = 0.01;
	private final double WALL_REPULSION = 10000;
	
	private Vector position;
	private Vector velocity;
	private Vector othersForce;
	private Vector wallsForce;

	
	private String state; //susceptible OR infected OR recovered 
	private Color color;
	private boolean identified; //TRUE if the individual has been identified as contaminated, FALSE otherwise
	private int infectedTimeDays; //number of days after infection
	private double socialDistanceCoeff; // coefficient defining the extent of social distancing for this individual
	private Disease disease;
	
//Constructor :

	public Individual(Disease d, double xMax, double yMax, double xMin, double yMin){

		X_MAX = xMax;
		Y_MAX = yMax;
		X_MIN = xMin;
		Y_MIN = yMin;
		
		position = new Vector("cartesian",X_MIN+(X_MAX-X_MIN)*Math.random(),Y_MIN+(Y_MAX-Y_MIN)*Math.random());
		velocity = new Vector("polar", V_NORM,2*Math.PI*Math.random());
		othersForce = new Vector();
		//System.out.println("othersForce: "+othersForce.getX()+"  "+othersForce.getY());
		wallsForce = new Vector();
		
		disease = d;
		state = "susceptible";
		color = Color.green;
		identified = false;
		infectedTimeDays = 0;
		socialDistanceCoeff = 10000000;
		socialDistanceCoeff = 0;
	}
	
//Getters :
	public String getState(){
		return state;
	}
	
	public Color getColor(){
		return color;
	}
	

	public Vector getPosition(){
		return position;
	}


	
//Setters :

	public void setSocialDistanceCoeff(double sdc){
		socialDistanceCoeff = sdc;
	}	
	

	
//Methods :
	
//Methods :
	


//updating the parameters of the individuals :
	
	//Method updating the state of an individual from their infection time
	public void updateState(){
		if(infectedTimeDays>disease.getInfectionTimeDays()){
			state = "recovered";
			color = Color.blue;
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
	

	//Method updating the position of an individual
	public void updatePosition(){
		Vector dV = new Vector();
		dV.add(othersForce);
		wallsRepel(position);
		//dV.add(wallsForce);
		dV.multiply(DELTA_T);
		Vector displacement = new Vector();
		velocity.add(dV);
		if(velocity.getNorm()>V_MAX){
			velocity.setNorm(V_MAX);
		}
		displacement = new Vector();
		displacement.add(velocity);
		displacement.multiply(DELTA_T);
		position.add(displacement);
		checkWalls(position);
		
		othersForce.setNorm(0);
		othersForce.setAngle(0);
		wallsForce.setNorm(0);
		wallsForce.setAngle(0);
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
	public void initialInfect(){
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
	
	//Method checking that the individuals do not get out of the box
	public void wallsRepel(Vector pos){
		if(X_MIN>=pos.getX()){
			wallsForce.setX(WALL_REPULSION);
		}else if(X_MAX<=pos.getX()){
			wallsForce.setX(-WALL_REPULSION);
		}else{			
			Vector repelLeft = new Vector("polar",1,0);
			repelLeft.multiply(WALL_REPULSION/Math.pow(pos.getX()-X_MIN,4));
			Vector repelRight = new Vector("polar",1,Math.PI);
			repelRight.multiply(WALL_REPULSION/Math.pow(pos.getX()-X_MAX,4));
			wallsForce.add(repelRight);
			wallsForce.add(repelLeft);
			
		}
		if(Y_MIN>=pos.getY()){

			wallsForce.setY(WALL_REPULSION);
		}else if(Y_MAX<=pos.getY()){
			wallsForce.setY(-WALL_REPULSION);
		}else{	
			Vector repelDown = new Vector("polar",1,Math.PI/2);
			repelDown.multiply(WALL_REPULSION/Math.pow(pos.getY()-Y_MIN,4));
			Vector repelUp = new Vector("polar",1,-Math.PI/2);
			repelUp.multiply(WALL_REPULSION/Math.pow(pos.getY()-Y_MAX,4));
			wallsForce.add(repelDown);
			wallsForce.add(repelUp);
		}	
	}
	
	
	
	public void checkWalls(Vector pos){
		if(X_MIN>pos.getX()){
			position.setX(X_MIN);
			velocity.setX(Math.abs(velocity.getX()));
		}
		if(X_MAX<pos.getX()){
			position.setX(X_MAX);
			velocity.setX(-Math.abs(velocity.getX()));
		}
		if(Y_MIN>=pos.getY()){
			position.setY(Y_MIN);
			velocity.setY(Math.abs(velocity.getY()));
		}
		if(Y_MAX<=pos.getY()){
			position.setY(Y_MAX);
			velocity.setY(-Math.abs(velocity.getY()));
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
		repelForce.multiply(socialDistanceCoeff/Math.pow(position.distance(individual.getPosition()),4));
		othersForce.add(repelForce);
	}
	
}

