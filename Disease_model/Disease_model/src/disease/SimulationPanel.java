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
	
	private boolean quarantine;
	
	
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
		
		quarantine = false;
		
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :	
	
	public void startQuarantine() {
		
	}
	
	public void endQuarantine() {
		
	}
	
	public void quarantine() {
		
	}
	
	public void quarantineGroup(Group g, Group q) {
		int i = 0;
		while(i<g.getGroup().size()) {
			if(g.getGroup().get(i).getState()=="infected" && g.getGroup().get(i).isIdentified()) {
				q.add(g.getGroup().get(i));
				g.remove(i);
				i--;
			}
			i++;
		}
		i = 0;
		while(i<q.getGroup().size()) {
			if(q.getGroup().get(i).getState()=="recovered") {
				g.add(q.getGroup().get(i));
				q.remove(i);
				i--;
			}
			i++;
		}
	}
	
	
	public void fillDataset() {
		dataset.addValue(numInfected, "Infected", Integer.toString(day));
		dataset.addValue(numRecovered, "Recovered", Integer.toString(day));
		dataset.addValue(numSusceptible, "Susceptible", Integer.toString(day));
		dataset.addValue(numIdentified, "Identified", Integer.toString(day));
	}
	
	public void update() {
		
	}
	
	public void iterate(){
		update();
		if(quarantine) {
			quarantine();
		}
		iteration ++;
		int thisDay = iteration/ITERATIONS_PER_DAY;
		if(thisDay != day){
			day = thisDay;
			fillDataset();
		}
		repaint();
	}
	
	
		
	
}

