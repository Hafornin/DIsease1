package disease;
import java. awt.*;
import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;

public class SimulationGroup extends JPanel{
	
	//Attributes :
	private Group[] boxes;
	private DefaultCategoryDataset  dataset;
	/*private DefaultCategoryDataset  numSusceptible;
	private DefaultCategoryDataset  numInfected;
	private DefaultCategoryDataset  numRecovered;
	private DefaultCategoryDataset  numIdentified;*/
	
	private int numSusceptible; //number of susceptible persons in the simulation
	private int numInfected; //number of infected persons in the simulation
	private int numRecovered; //number of recovered persons in the simulation
	private int numIdentified; //number of identified infected persons in the simulation
	
	private final int HISTORY = 200;
	private int iteration;
	private int day;
	private final int ITERATIONS_PER_DAY = 10;
	
	
	//Constructor :
	public SimulationGroup(Group[] b){
		boxes = new Group[b.length];
		for(int i=0;i<boxes.length;i++){
			boxes[i] = b[i];
		}
		
		setLayout(new GridLayout(3,3));
		setSize(750,800);         
		setVisible(true);
		
		for(int i=0;i<boxes.length;i++){
			add(boxes[i]);
		}
		
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		
		/*numSusceptible = new DefaultCategoryDataset();
		numInfected = new DefaultCategoryDataset();
		numRecovered = new DefaultCategoryDataset();
		numIdentified = new DefaultCategoryDataset();*/
		dataset = new DefaultCategoryDataset();
		
		iteration = 0;
		day = 0;
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	/*
	public DefaultCategoryDataset getNumSusceptible(){
		return numSusceptible;
	}
	
	public DefaultCategoryDataset getNumInfected(){
		return numInfected;
	}
	
	public DefaultCategoryDataset getNumRecovered(){
		return numRecovered;
	}
	
	public DefaultCategoryDataset getNumIdentified(){
		return numIdentified;
	}
	
	public int getDay(){
		return day;
	}
	*/
	//Methods :
	
	//Method performing the iterations
	public void iterate(){
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		for(int i=0;i<boxes.length;i++){
				boxes[i].move();
				boxes[i].infect();
				boxes[i].updateValues();
				numSusceptible += boxes[i].getNumSusceptible();
				numInfected += boxes[i].getNumInfected();
				numRecovered += boxes[i].getNumRecovered();
				numIdentified += boxes[i].getNumIdentified();
		}
		iteration ++;
		int thisDay = iteration/ITERATIONS_PER_DAY;
		if(thisDay != day){
			day = thisDay;
			dataset.addValue(numInfected, "Infected", Integer.toString(day));
			dataset.addValue(numRecovered, "Recovered", Integer.toString(day));
			dataset.addValue(numSusceptible, "Susceptible", Integer.toString(day));
			dataset.addValue(numIdentified, "Identified", Integer.toString(day));
		}
		repaint();
	}
		
	
}

