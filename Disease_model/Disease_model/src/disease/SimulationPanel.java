package disease;
import java. awt.*;
import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;

public class SimulationPanel extends JPanel{
	
	//Attributes :
	protected DefaultCategoryDataset  dataset;	
	protected int numSusceptible; //number of susceptible persons in the simulation
	protected int numInfected; //number of infected persons in the simulation
	protected int numRecovered; //number of recovered persons in the simulation
	protected int numIdentified; //number of identified infected persons in the simulation
	protected int iteration;
	protected int day;
	protected final int ITERATIONS_PER_DAY = 10;
	protected Disease disease;
	
	
	//Constructor :
	public SimulationPanel(Disease d){
		disease = d;
		
		setSize(720,870);         
		setVisible(true);
		setBackground(Color.white);
		
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		
		dataset = new DefaultCategoryDataset();
		
		iteration = 0;
		day = 0;
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :	
	
	public void fillDataset() {
		dataset.addValue(numInfected, "Infected", Integer.toString(day));
		dataset.addValue(numRecovered, "Recovered", Integer.toString(day));
		dataset.addValue(numSusceptible, "Susceptible", Integer.toString(day));
		dataset.addValue(numIdentified, "Identified", Integer.toString(day));
	}
	
	public void updateDataValues() {
		
	}
	
	public void iterate(){
		updateDataValues();
		iteration ++;
		int thisDay = iteration/ITERATIONS_PER_DAY;
		if(thisDay != day){
			day = thisDay;
			fillDataset();
		}
		repaint();
	}
		
	
}

