import java.awt.Color;

public class Individual {
	
	//Attributes :
	private final int X_MAX; //maximum x value (edge of the box)
	private final int Y_MAX; //maximum y value (edge of the box)
	private final int X_MIN; //minimum x value (edge of the box)
	private final int Y_MIN; //minimum y value (edge of the box)
	
	private double x; //x coordinate
	private double y; //y coordinate
	private double angle;
	
	private String state; //susceptible OR infected OR recovered 
	private Color color;
	private boolean identified; //TRUE if the individual has been identified as contaminated, FALSE otherwise
	private int infectedTimeDays; //number of days after infection
	private double socialDistanceCoeff; // coefficient defining the extent of social distancing
	private Disease disease;
	
	//Constructor :
	public Individual(Disease d, int xMax, int yMax, int xMin, int yMin){
		X_MAX = xMax;
		Y_MAX = yMax;
		X_MIN = xMin;
		Y_MIN = yMin;
	
		x = X_MIN+(X_MAX-X_MIN)*Math.random();
		y = Y_MIN+(Y_MAX-Y_MIN)*Math.random();
		angle = 2*Math.PI*Math.random();
		
		disease = d;
		state = "susceptible";
		color = Color.green;
		identified = false;
		infectedTimeDays = 0;
		socialDistanceCoeff = 5;
	}
	
	//Getters :
	public String getState(){
		return state;
	}
	
	public Color getColor(){
		return color;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}

	
	//Setters :
	public void setSocialDistanceCoeff(double sdc){
		socialDistanceCoeff = sdc;
	}
	
	public void infect(){
		state = "infected";
		infectedTimeDays = 1;
	}
	
	
	
	//Methods :
	
	
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
	
	//Method computing the distance between 2 individuals
	public double distance(Individual individual){
		double distance = Math.sqrt((this.x-individual.x)*(this.x-individual.x) + (this.y-individual.y)*(this.y-individual.y));
		return distance;
	}
	
	//Method deciding wether a person is identified as infected
	public boolean identify(){
		double test = Math.random();
		if(state=="infected" && test<disease.getIdentifiedProportion()){
			identified = true;
		}
		return identified;
	}
	
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
	
	//Method telling wether an individual belongs or not to another's social distance radius
	public boolean isCloseEnoughtoRepel(Individual individual){
		if(distance(individual) <= disease.getSocialDistanceRadius()){
			return true;
		}else{
			return false;
		}
	}
	
	//Method checking that the individuals do not get out of the box
	public void checkWalls(double xi, double yi){
		if(X_MIN>xi){
			x = 2*X_MIN-xi;
			angle = angle + Math.PI;
		}else if(X_MAX<xi){
			x = 2*X_MAX-xi;
			angle = angle + Math.PI;
		}else{
			x = xi;
		}
		if(Y_MIN>yi){
			y = 2*Y_MIN-yi;
			angle = angle + Math.PI;
		}else if(Y_MAX<yi){
			y = 2*Y_MAX-yi;
			angle = angle + Math.PI;
		}else{
			y=yi;
		}
	}
	
	//Method defining the normal movement of each individual
	public void planTrip(){
		double test = Math.random();
		double sign = 1;
		if(test<0.5){
			sign = -1;
		}
		angle = angle + sign*0.2*Math.PI*Math.random();
		double xi = x + disease.getDistanceTraveled()*Math.cos(angle);
		double yi = y + disease.getDistanceTraveled()*Math.sin(angle);
		checkWalls(xi,yi);
	}
	
	//Method implementing social distancing 
	public void repel(Individual individual){
		double xi = this.x - (individual.x-this.x)*this.socialDistanceCoeff;
		double yi = this.y - (individual.y-this.y)*this.socialDistanceCoeff;
		checkWalls(xi,yi);
	}
		
	
}

