import java.awt.Color;

public class Individual {
	
//Attributes :

	private final double X_MAX; //maximum x value (edge of the box)
	private final double Y_MAX; //maximum y value (edge of the box)
	private final double X_MIN; //minimum x value (edge of the box)
	private final double Y_MIN; //minimum y value (edge of the box)
	private final double V_MAX = 150;
	private final double V_NORM = 100;
	private final double DELTA_T = 0.01;
	private final double WALL_REPULSION = 10;
	
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
		System.out.println("othersForce: "+othersForce.getX()+"  "+othersForce.getY());
		wallsForce = new Vector();
		
		disease = d;
		state = "susceptible";
		color = Color.green;
		identified = false;
		infectedTimeDays = 0;
		socialDistanceCoeff = 10000000;
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
	

	//Method updating the position of an individual
	public void updatePosition(){
		Vector dV = new Vector();
		System.out.println("othersForce: "+othersForce.getX()+"  "+othersForce.getY());
		System.out.println("1: "+dV.getX()+"  "+dV.getY());
		dV.add(wallsForce);
		dV.add(othersForce);
		System.out.println("2: "+dV.getX()+"  "+dV.getY());
		dV.multiply(DELTA_T);
		System.out.println("3: "+dV.getX()+"  "+dV.getY());
		velocity.add(dV);
		if(velocity.getNorm()>V_MAX){
			velocity.setNorm(V_MAX);
		}
		Vector displacement = new Vector();
		displacement.add(velocity);
		displacement.multiply(DELTA_T);
		Vector newPos = new Vector();
		newPos.add(position);
		newPos.add(displacement);
		checkWalls(newPos);
		othersForce.setX(0);
		othersForce.setY(0);
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
		System.out.println("x1 : "+velocity.getX()+" y1 : "+velocity.getY()+" n1 : "+velocity.getNorm()+" a1 : "+velocity.getAngle());
		//velocity.setAngle(velocity.getAngle());
		System.out.println("x2 : "+velocity.getX()+" y2 : "+velocity.getY()+" n2 : "+velocity.getNorm()+" a2 : "+velocity.getAngle());
	}
	
	
	//Method checking that the individuals do not get out of the box
	public void checkWalls(Vector pos){
		if(X_MIN>pos.getX()){
			position.setX(X_MIN);
			velocity.setX(Math.abs(velocity.getX()));
			//wallsForce.setX(WALL_REPULSION);
		}else if(X_MAX<pos.getX()){
			position.setX(X_MAX);
			velocity.setX(-Math.abs(velocity.getX()));
			//wallsForce.setX(-WALL_REPULSION);
		}else{
			position.setX(pos.getX());
			//wallsForce.setX(0);
		}
		if(Y_MIN>pos.getY()){
			position.setY(Y_MIN);
			velocity.setY(Math.abs(velocity.getY()));
			//wallsForce.setY(WALL_REPULSION);
		}else if(Y_MAX<pos.getY()){
			position.setY(Y_MAX);
			velocity.setY(-Math.abs(velocity.getY()));
			//wallsForce.setY(-WALL_REPULSION);
		}else{
			position.setY(pos.getY());
			//wallsForce.setY(0);
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
		System.out.println("rep "+repelForce.getX()+"  "+repelForce.getY());
		repelForce.multiply(socialDistanceCoeff/(position.distance(individual.getPosition())*position.distance(individual.getPosition())*position.distance(individual.getPosition())*position.distance(individual.getPosition())));
		System.out.println("rep "+repelForce.getX()+"  "+repelForce.getY());
		othersForce.add(repelForce);
		System.out.println("othersForcerep: "+othersForce.getX()+"  "+othersForce.getY());


	}
	
}

