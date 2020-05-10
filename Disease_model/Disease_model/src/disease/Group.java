package disease;
import java. awt.*;
import javax.swing.*;
import java.util.*;

public class Group extends JPanel{
	
//Attributes : 
	private int numSusceptible; //number of susceptible persons in the simulation
	private int numInfected; //number of infected persons in the simulation
	private int numRecovered; //number of recovered persons in the simulation
	private int numIdentified; //number of identified infected persons in the simulation
	
	private double leaveGroupProba = 0.1;
	private double enterGroupProba = 0.1;
	
	private LinkedList<Individual> group; //group of people we run the simulation on
	private Disease disease;
	private final int SIZE;
	private final int X;
	private final int Y;
	private final int X_LENGTH;
	private final int Y_LENGTH;
	//DATA STORAGE

	
//Constructor :
	public Group(int s, int x, int y, int xl, int yl, Disease d){
		SIZE = s;
		X = x;
		Y = y;
		X_LENGTH = xl;
		Y_LENGTH = yl;
		disease = d;
		group = new LinkedList<>();
		for(int i=0;i<SIZE;i++){
			group.add(new Individual(disease, X+X_LENGTH, Y+Y_LENGTH, X, Y));
		}
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		
		setVisible(true);
		setSize((int) (X_LENGTH*1.1),(int) (Y_LENGTH*1.1));
	}
	
	
//Getters :
	public int getNumSusceptible(){
		return numSusceptible;
	}
	
	public int getNumInfected(){
		return numInfected;
	}
	
	public int getNumRecovered(){
		return numRecovered;
	}
	
	public int getNumIdentified(){
		return numIdentified;
	}
	
	public LinkedList<Individual> getGroup(){
		return group;
	}
	
	public int getGroupSize() {
		return SIZE;
	}
	
	public double getLeaveGroupProba() {
		return leaveGroupProba;
	}
	
	public double getEnterGroupProba() {
		return enterGroupProba;
	}
	
//Setters :
	
//Methods :
	
	//Method updating the number of susceptible, infected and recovered individuals
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
    
//Moving the individuals
    
    //Method making the individuals move inside the group
    public void move(){
		for(int i=0;i<group.size();i++){
			for(int j=0;j<group.size();j++){
				if(i!=j && group.get(i).isCloseEnoughtoRepel(group.get(j))){
					group.get(i).repel(group.get(j));
				}
			}			
		}
		for(int i=0;i<group.size();i++){
			group.get(i).updatePosition();
		}
	}
	
	//Method moving an individual to another group
	public void remove(int i) {
		group.remove(i);
	}
	
	public void add(Individual i) {
		//System.out.println("not yet "+group.size());
		group.add(i);
		//System.out.println("done "+group.size());
	}
	
	//Method performing the disease transmission step
	public void infect(){
		for(int i=0;i<group.size();i++){
			for(int j=0;j<group.size();j++){
				if(group.get(i).isCloseEnoughtoInfect(group.get(j))){
					group.get(i).infect(group.get(j));
				}
			}
			
		}
		for(int i=0;i<group.size();i++){
			group.get(i).updateTime();
			group.get(i).updateState();
		}
	}
	
	public void identify() {
		for(int i=0;i<group.size();i++){
			group.get(i).identify();
		}
	}
	
//Display :

	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.drawRect(X-5,Y-5,X_LENGTH+10,Y_LENGTH+10);
		for(int i=0;i<group.size();i++){
			g.setColor(group.get(i).getColor());
			g.fillOval((int)group.get(i).getPosition().getX()-5,(int)group.get(i).getPosition().getY()-5,10,10);
		}
	}
		
		
}		
		
		
		
	
	
	
	
	




