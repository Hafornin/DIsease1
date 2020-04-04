import java. awt.*;
import javax.swing.*;
import java.util.*;

public class Group extends JPanel{
	
	//Attributes : 
	private int numSusceptible; //number of susceptible persons in the simulation
	private int numInfected; //number of infected persons in the simulation
	private int numRecovered; //number of recovered persons in the simulation
	private int numIdentified; //number of identified infected persons in the simulation
	
	private Set<Individual> group; //group of people we run the simulation on
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
		group = new HashSet<>(2*SIZE);
		for(int i=0;i<SIZE;i++){
			group.add(new Individual(disease, X+X_LENGTH, Y+Y_LENGTH, X, Y));
		}
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
	}
	
	
		
	
			
	
	
	//Getters :
	
	//Setters :
	
	//Methods :
	
	//Method updating the number of susceptible, infected and recovered individuals
	public void updateValues(){
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
			Iterator<Individual> iterator = group.iterator();
		while (iterator.hasNext()) {
			Individual i = iterator.next();
			if (i.getState()=="susceptible"){
				numSusceptible++;
			}			
			if(i.getState()=="infected"){
				numInfected++;
			}
			if(i.getState()=="recovered"){
				numRecovered++;
			}
		}
    }
    
    //Method making the individuals move inside the group
    public void move(){
		Iterator<Individual> iterator = group.iterator();
		while (iterator.hasNext()) {
			Individual i = iterator.next();
			i.planTrip();
		}
		iterator = group.iterator();
		while (iterator.hasNext()) {
			Individual i = iterator.next();
			while (iterator.hasNext()) {
				Individual j = iterator.next();
				if(i.isCloseEnoughtoRepel(j)){
					i.repel(j);
				}
			}			
		}
	}
	
	//Method moving an individual to another group
	public void transfer(Individual i, Group g){
		g.group.add(i);
		this.group.remove(i);
	}
	
	//Method performing the disease transmission step
	public void infect(){
		Iterator<Individual> iteratorA = group.iterator();
		while (iteratorA.hasNext()) {
			Individual i = iteratorA.next();
			Iterator<Individual> iteratorB = group.iterator();
			while (iteratorB.hasNext()) {
				Individual j = iteratorB.next();
				if(i.isCloseEnoughtoInfect(j)){
					i.infect(j);
				}
			}
			
		}
	}
	
	//Display
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.drawRect(X,Y,X_LENGTH,Y_LENGTH);
		Iterator<Individual> iterator = group.iterator();
		while (iterator.hasNext()) {
			Individual i = iterator.next();
			g.setColor(i.getColor());
			g.fillOval((int)i.getX(),(int)i.getY(),5,5);
		}
	}
		
		
}		
		
		
		
	
	
	
	
	



