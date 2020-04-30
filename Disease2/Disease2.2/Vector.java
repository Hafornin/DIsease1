public class Vector {
	
//Attributes :
	private double x; //x component
	private double y; //y component
	private double norm;
	private double angle;
	
//Constructors :

	public Vector(){
		x = 0;
		y = 0;
		norm = 0;
		angle = 0;
	}
		
	public Vector(String s, double xiOrNorm, double yiOrAngle){
		if(s=="cartesian"){
			x = xiOrNorm;
			y = yiOrAngle;
			norm = Math.sqrt(x*x+y*y);
			angle = Math.atan(y/x);
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
			System.out.println("First parameter should be : 'cartesian' or 'polar'. Every value was set to 0.");
		}			
	}
	
	public Vector(Position posA, Position posB){
		x = posB.getX()-posA.getX();
		y = posB.getY()-posA.getY();
		norm = Math.sqrt(x*x+y*y);
		angle = Math.atan(y/x);
	}
	
//Getters :
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getNorm(){
		return norm;
	}
	
	public double getAngle(){
		return angle;
	}
	
//Setters :

	public void setX(double xi){
		x = xi;
		norm = Math.sqrt(x*x+y*y);
		angle = Math.atan(y/x);
	}
	
	public void setY(double yi){
		y = yi;
		norm = Math.sqrt(x*x+y*y);
		angle = Math.atan(y/x);
	}
	
	public void setNorm(double ni){
		norm = ni;
		x = norm*Math.cos(angle);
		y = norm*Math.sin(angle);
	}
	
	public void setAngle(double ai){
		angle = ai;
		x = norm*Math.cos(angle);
		y = norm*Math.sin(angle);
	}
		
	
//Methods :
	
	public void add(Vector v){
		x = x + v.getX();
		y = y + v.getY();
		norm = Math.sqrt(x*x+y*y);
		angle = Math.atan(y/x);
	}
	
	public void multiply(double k){
		norm = norm*k;
		x = norm*Math.cos(angle);
		y = norm*Math.sin(angle);
	}
		
}

