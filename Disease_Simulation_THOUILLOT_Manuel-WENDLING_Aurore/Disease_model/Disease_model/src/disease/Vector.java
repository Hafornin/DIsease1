/**
 * This class represents a vector in the plane.
 * It is used to model the position and velocity of the individuals.
 */
package disease;

public class Vector {
	
//ATTRIBUTES :
	
	/**
	 * the x component of the vector
	 */
	private double x;
	/**
	 * the y component of the vector
	 */
	private double y;
	/**
	 * the norm of the vector
	 */
	private double norm;
	/**
	 * the angle of the vector
	 */
	private double angle;
		
//CONSTRUCTORS :

	/**
	 * Creates an instance of Vector.
	 * Creates the nil vector.
	 */
	public Vector(){
		x = 0;
		y = 0;
		norm = 0;
		angle = 0;
	}
	
	/**
	 * Creates an instance of Vector from cartesian or polar coordinates
	 * @param s : "cartesian" will create a vector from cartesian coordinates ; "polar" will create a vector from polar coordinates
	 * @param xiOrNorm the x component if s is "cartesian" ; the norm if s is "polar"
	 * @param yiOrAngle the y component if s is "cartesian" ; the angle if s is "polar"
	 */
	public Vector(String s, double xiOrNorm, double yiOrAngle){
		if(s=="cartesian"){
			x = xiOrNorm;
			y = yiOrAngle;
			norm = Math.sqrt(x*x+y*y);
			calcAngle();
		}else if(s=="polar"){
			norm = xiOrNorm;
			angle = yiOrAngle;
			x = norm*Math.cos(angle);
			y = norm*Math.sin(angle);
		}else{
			x = 0;
			y = 0;
			norm = 0;
			angle = 0;
			System.out.println("First parameter should be : 'cartesian' or 'polar'. Nil vector created.");
		}			
	}
	
	/**
	 * Creates an instance of Vector from two other Vectors
	 * @param posA the starting point of this vector
	 * @param posB the end point of this vector
	 */
	public Vector(Vector posA, Vector posB){
		x = posB.getX()-posA.getX();
		y = posB.getY()-posA.getY();
		norm = Math.sqrt(x*x+y*y);
		calcAngle();
	}
	
//GETTERS :
	
	/**
	 * Gets the x component of this vector
	 * @return the x component of this vector
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Gets the y component of this vector
	 * @return the y component of this vector
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Gets the norm of this vector
	 * @return the norm of this vector
	 */
	public double getNorm(){
		return norm;
	}
	
	/**
	 * Gets the angle of this vector
	 * @return the angle of this vector
	 */
	public double getAngle(){
		return angle;
	}
	
//SETTERS :

	/**
	 * Sets the x component of this vector
	 * @param xi the new x component of this vector
	 */
	public void setX(double xi){
		x = xi;
		norm = Math.sqrt(x*x+y*y);
		calcAngle();
	}
	
	/**
	 * Sets the y component of this vector
	 * @param yi the new y component of this vector
	 */
	public void setY(double yi){
		y = yi;
		norm = Math.sqrt(x*x+y*y);
		calcAngle();
	}
	
	/**
	 * Sets the norm of this vector
	 * @param ni the new norm of this vector
	 */
	public void setNorm(double ni){
		norm = ni;
		x = norm*Math.cos(angle);
		y = norm*Math.sin(angle);
	}
	
	/**
	 * Sets the angle of this vector
	 * @param ai the new angle of this vector
	 */
	public void setAngle(double ai){
		angle = ai;
		x = norm*Math.cos(angle);
		y = norm*Math.sin(angle);
	}
		
	
//METHODS :

	/**
	 * Method computing the angle of this vector from its x and y components
	 */
	public void calcAngle(){
		if(x==0 && y==0){
			angle = 0;
		}
		if(x>0 && y==0){
			angle = 0;
		}
		if(x>0 && y>0){
			angle = Math.atan(y/x);
		}
		if(x==0 && y>0){
			angle = Math.PI/2;
		}
		if(x<0 && y>0){
			angle = Math.PI + Math.atan(y/x);
		}
		if(x<0 && y==0){
			angle = Math.PI;
		}
		if(x<0 && y<0){
			angle = Math.PI + Math.atan(y/x);
		}
		if(x==0 && y<0){
			angle = 3*Math.PI/2;
		}
		if(x>0 && y<0){
			angle = Math.atan(y/x);
		}			
	}
	
	/**
	 * Method adding a vector to this vector
	 * @param v the vector added to this vector
	 */
	public void add(Vector v){
		x = x + v.getX();
		y = y + v.getY();
		norm = Math.sqrt(x*x+y*y);
		calcAngle();
	}
	
	/**
	 * Method multiplying this vector by a real number
	 * @param k the real number multiplying this vector
	 */
	public void multiply(double k){
		norm = norm*k;
		x = x*k;
		y = y*k;
		calcAngle();
	}
	
	/**
	 * Method computing the distance between this vector and another vector
	 * @param v the vector to which the distance is computed
	 * @return the distance between this vector and the vector v
	 */
	public double distance(Vector v){
		double distance = Math.sqrt((x-v.getX())*(x-v.getX()) + (y-v.getY())*(y-v.getY()));
		return distance;
	}
		
}


