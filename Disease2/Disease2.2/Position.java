public class Position {
<<<<<<< HEAD
	
//Attributes :
	private double x; //x coordinate
	private double y; //y coordinate
	
//Constructor :

	public Position(double xi, double yi){
		x = xi;
		y = yi;
	}
	
//Getters :
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
//Setters :

	public void setX(double xi){
		x = xi;
	}
	
	public void setY(double yi){
		y = yi;
	}
	
//Methods :

	public double distance(Position pos){
		double distance = Math.sqrt((x-pos.getX())*(x-pos.getX()) + (y-pos.getY())*(y-pos.getY()));
		return distance;
	}
=======
	private double x; //x coordinate
	private double y; //y coordinate
>>>>>>> DiseaseModelling
}

