/*
 * IndividualTest.sql
 * 
 * Copyright 2020 Aurore Wendling <Aurore@desktop-a4bfq5c.home>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


public class IndividualTest {
	//Attributes :
	private double x; //x coordinate
	private double y; //y coordinate
	private final double X_MAX; //maximum x value (edge of the box)
	private final double Y_MAX; //maximum y value (edge of the box)
	private double vx; //x component of velocity
	private double vy; //y component of velocity
	private double vNorm; //norm of velocity vector
	private double vAngle; //angle of velocity vector
	private final double V_INIT; //initial velocity of people
	private final double V_MAX; //maximal velocity of people
	private final double DELTA_T; //elementary time
	
	private String state; //susceptible OR infected OR recovered 
	private boolean identified; //TRUE if the individual has been identified as contaminated, FALSE otherwise
	private int infectedTimeDays; //number of days after infection
	private Disease disease;
	
	//Constructor :
	public IndividualTest(Disease d, double xm, double ym, double vm, double vi, double dt){
		X_MAX = xm;
		Y_MAX = ym;
		V_MAX = vm;
		V_INIT = vi;
		DELTA_T = dt;
	
		x = X_MAX*Math.random();
		y = Y_MAX*Math.random();
		vNorm = V_INIT;
		vAngle = 2*Math.PI*Math.random();
		vx = Math.cos(vAngle)/vNorm;
		vy = Math.sin(vAngle)/vNorm;
		
		disease = d;
		state = "susceptible";
		identified = false;
		infectedTimeDays = 0;
	}
	
	//Getters :
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getVx(){
		return vx;
	}
	
	public double getVy(){
		return vy;
	}
	
	public double getVnorm(){
		return vNorm;
	}
	
	public double getVangle(){
		return vAngle;
	}
	
	public int getInfectedTime(){
		return infectedTimeDays;
	}
	
	public String getState(){
		return state;
	}
	
	public boolean isIdentified(){
		return identified;
	}
	
	//Setters :
	
	public void setX(double xi){
		x = xi;
	}
	
	public void setY(double yi){
		y = yi;
	}
	
	public void setVx(double vxi){
		vx = vxi;
	}
	
	public void setVy(double vyi){
		vy = vyi;
	}
	
	public void setVnorm(double vNormi){
		vNorm = vNormi;
	}
	
	public void setVangle(double vAnglei){
		vAngle = vAnglei;		
	}
	
	public void setInfectedTime(int infectedTimeDaysi){
		infectedTimeDays = infectedTimeDaysi;
	}
	
	public void setState(String statei){
		state = statei;
	}
	

}

