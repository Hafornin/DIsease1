/**
 * Each instance of Group corresponds to a box in our simulation.
 * It is a JPanel displaying a collection of individuals and making them interact and move.
 */

package disease;

import java. awt.*;
import javax.swing.*;
import java.util.*;

public class Group extends JPanel{
	
//ATTRIBUTES :
	
//=================== Attributes related to the collection of individuals we run the simulation on ========================

	/**
	 * the collection of individuals we run the simulation on
	 */
	private LinkedList<Individual> group;
	
	/**
	 * the initial size of the collection of individuals we run the simulation on
	 */
	private final int SIZE;
	
	/**
	 * the disease parameters for the individuals of this group
	 */
	private Disease disease;
	
	
//=================== Attributes related to the display of the box ========================
	
	/***
	 * the x position of the box
	 */
	private final int X;
	/**
	 * the y position of the box
	 */
	private final int Y;
	/**
	 * the width of the box
	 */
	private final int X_LENGTH;
	/*
	 * the height of the box
	 */
	private final int Y_LENGTH;
	
	
//=================== Number of susceptible, infected, recovered, dead and identified individuals in the group ========================

	/**
	 * the number of susceptible persons in the the collection of individuals we run the simulation on
	 */
	private int numSusceptible;
	/**
	 * the number of infected persons in the collection of individuals we run the simulation on
	 */
	private int numInfected;
	/**
	 * the number of recovered persons in the collection of individuals we run the simulation on
	 */
	private int numRecovered;
	/**
	 * the number of identified infected persons in the collection of individuals we run the simulation on
	 */
	private int numIdentified;
	
	/**
	 * the number of dead people since the beginning of the simulation
	 */
	private int numDead;

	
//CONSTRUCTOR :
	
	/**
	 * Creates an instance of Group
	 * 
	 * @param s the initial size of the collection of individuals we run the simulation on
	 * @param x the x position of the box
	 * @param y the y position of the box
	 * @param xl the width of the box
	 * @param yl the height of the box
	 * @param d the disease parameters for the individuals of this group
	 */
	public Group(int s, int x, int y, int xl, int yl, Disease d){
		
		//affecting the position and dimensions of the box
		X = x;
		Y = y;
		X_LENGTH = xl;
		Y_LENGTH = yl;
		
		disease = d; //affecting the disease parameters for this group
		
		//creating the collection of individuals we run the simulation on
		SIZE = s; //initial number of individuals
		group = new LinkedList<>(); //initializing the list of individuals
		for(int i=0;i<SIZE;i++){
			group.add(new Individual(disease, X+X_LENGTH, Y+Y_LENGTH, X, Y)); //creating instances of Individual in the list
		}
		
		//setting the number of susceptible, infected, recovered and identified individuals in this group to 0
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		numDead = 0;
		
		setSize((int) (X_LENGTH+50),(int) (Y_LENGTH+50)); //setting the size of the JPanel
		
		setVisible(true); //making the JPanel visible
	}
	
	
//GETTERS :
	
	/**
	 * Gets the number of susceptible individuals in this group
	 * @return the number of susceptible individuals in this group
	 */
	public int getNumSusceptible(){
		return numSusceptible;
	}
	
	/**
	 * Gets the number of infected individuals in this group
	 * @return the number of infected individuals in this group
	 */
	public int getNumInfected(){
		return numInfected;
	}
	
	/**
	 * Gets the number of recovered individuals in this group
	 * @return the number of recovered individuals in this group
	 */
	public int getNumRecovered(){
		return numRecovered;
	}
	
	/**
	 * Gets the number of identified individuals in this group
	 * @return the number of identified individuals in this group
	 */
	public int getNumIdentified(){
		return numIdentified;
	}
	
	/**
	 * Gets the number of individuals of this group that have died since the start of the simulation
	 * @return the number of individuals of this group that have died since the start of the simulation
	 */
	public int getNumDead() {
		return numDead;
	}
	
	/**
	 * Gets the collection of individuals in this group
	 * @return the collection of individuals in this group
	 */
	public LinkedList<Individual> getGroup(){
		return group;
	}
	
	/**
	 * Gets the initial number of individuals in this group
	 * @return the initial number of individuals in this group (SIZE)
	 */
	public int getGroupSize() {
		return SIZE;
	}

	
//METHODS :
	
//=================== Adding and removing individuals from this group ========================

	/**
	 * Method removing the individual at the position i in the list of individuals of this group
	 * @param i the position of the individual removed from the list of this group
	 */
	public void remove(int i) {
		group.remove(i);
	}
	
	/**
	 * Method adding an individual to list of individuals of this group
	 * @param i the individual removed from the list of this group
	 */
	public void add(Individual i) {
		group.add(i);
	}
	

//=================== Updating the number of susceptible, infected, recovered, dead and identified individuals in the group ========================
	
	/**
	 * Method updating the number of susceptible, infected and recovered individuals
	 */
	public void updateValues(){
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified = 0;
		for(int i=0;i<group.size();i++){
			if (group.get(i).getState()=="susceptible"){
				numSusceptible++;
			}			
			if(group.get(i).getState()=="infected"){
				numInfected++;
			}
			if(group.get(i).getState()=="recovered"){
				numRecovered++;
			}
			if(group.get(i).isIdentified()){
				numIdentified++;
			}
		}
    }
    
//=================== Moving the individuals ========================
    
    /**
     * Method making the individuals move inside this group
     */
    public void move(){
    	//making the individuals in this group repel each other when social distancing is on
		for(int i=0;i<group.size();i++){
			for(int j=0;j<group.size();j++){
				if(i!=j && group.get(i).isCloseEnoughtoRepel(group.get(j))){
					group.get(i).repel(group.get(j));
				}
			}			
		}
		//displacing the individuals
		for(int i=0;i<group.size();i++){
			group.get(i).updatePosition();
		}
	}
    
    
//=================== Transmitting the disease ========================
	
	/**
	 * Method performing the disease transmission step
	 */
	public void infect(){
		//infecting the individuals that are close to an infected person
		for(int i=0;i<group.size();i++){
			for(int j=0;j<group.size();j++){
				if(group.get(i).isCloseEnoughtoInfect(group.get(j))){
					group.get(i).infect(group.get(j));
				}
			}
			
		}
		//updating the disease-related parameters of each individual
		for(int i=0;i<group.size();i++){
			group.get(i).updateTime();
			group.get(i).updateState();
		}
	}
	
	/**
	 * Method to identify some of the infected individuals in this group
	 */
	public void identify() {
		for(int i=0;i<group.size();i++){
			group.get(i).identify();
		}
	}
	/**
	 * Method to make some of the infected individuals in this group die
	 */
	public void grimReaper() {
		int i=0;
		while(i<group.size()){
			if(group.get(i).die()) {
				remove(i);
				numDead++; //updating the number of dead individuals in this group 
				i--;
			}
			i++;
		}
	}
	
	
//=================== Social distancing ========================
	
	/**
	 * Method setting the extent of social distancing for each individual in this group
	 */
	public void setSocialDistancing() {
    	for(int i=0;i<group.size();i++){
    		group.get(i).setSocialDistanceCoeff(disease.getSocialDistanceCoeff());
    	}
    }
	
	
//=================== Social distancing ========================

	/**
	 * Method displaying the individuals in a square box
	 */
	public void paintComponent(Graphics g){
		//drawing the box
		g.setColor(Color.black);
		g.drawRect(X-5,Y-5,X_LENGTH+10,Y_LENGTH+10);
		//drawing the individuals as colored dots
		for(int i=0;i<group.size();i++){
			g.setColor(group.get(i).getColor());
			g.fillOval((int)group.get(i).getPosition().getX()-5,(int)group.get(i).getPosition().getY()-5,10,10);
		}
	}
		
		
}
