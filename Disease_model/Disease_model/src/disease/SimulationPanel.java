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
	protected int numDead;
	protected int iteration;
	protected int day;
	protected final int ITERATIONS_PER_DAY = 50;
	protected Disease disease;
	
	protected boolean quarantining;
	protected boolean travel;
	
	
	//Constructor :
	public SimulationPanel(Disease d){
		disease = d;
		d.setInfectionTime((int) d.getInfectionTime()*ITERATIONS_PER_DAY);		
		setSize(750,900);         
		setVisible(true);
		setBackground(Color.white);
		
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		numDead = 0;
		
		dataset = new DefaultCategoryDataset();
		
		iteration = 0;
		day = 0;
		
		quarantining = false;
		travel = true;
		
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	public boolean getQuarantining() {
		return quarantining;
	}
	
	public boolean getTravel() {
		return travel;
	}
	
	public Disease getDisease() {
		return disease;
	}
	
	public int getIterationsPerDay() {
		return ITERATIONS_PER_DAY;
	}
	
	//Methods :	
	
	//travel
	public void startTravel() {
		
	}
	
	public void stopTravel() {
		
	}
	
	public void travel() {
		
	}
	
	//quarantine
	public void startQuarantine() {
		
	}
	
	public void stopQuarantine() {
		
	}
	
	public void inQuarantine() {
		
	}
	
	public void outQuarantine() {
		
	}
	
	public void quarantineToGroup(Group q, Group g) {
		int i = 0;
		while(i<q.getGroup().size()) {
			if(q.getGroup().get(i).getState()=="recovered") {
				g.add(q.getGroup().get(i));
				q.remove(i);
				i--;
			}
			i++;
		}
	}
	
	public void groupToQuarantine(Group g, Group q) {
		int i = 0;
		while(i<g.getGroup().size()) {
			if(g.getGroup().get(i).getState()=="infected" && g.getGroup().get(i).isIdentified()) {
				q.add(g.getGroup().get(i));
				g.remove(i);
				i--;
			}
			i++;
		}
	}	
	
	//normal
	public void fillDataset() {
		dataset.addValue(numInfected, "Infected", Integer.toString(day));
		dataset.addValue(numRecovered, "Recovered", Integer.toString(day));
		dataset.addValue(numSusceptible, "Susceptible", Integer.toString(day));
		dataset.addValue(numIdentified, "Identified", Integer.toString(day));
		dataset.addValue(numDead, "Dead", Integer.toString(day));
	}
	
	public void updateConfig() {
		
	}
	
	public void updateValues() {
		
	}
	
	public void socialDistancing() {
		
	}
	
	public void goToCentralPoint(Group g, Group centralPoint) {
		int i = 0;
		while(i<g.getGroup().size()) {
			double test = Math.random();
			if(test<disease.getCentralPointTripProba()) {
				g.getGroup().get(i).takeToCenter();
				centralPoint.add(g.getGroup().get(i));
				g.remove(i);
				i--;
			}
			i++;
		}
	}
	
	public void leaveCentralPoint(Group g, Group centralPoint) {
		int i = 0;
		while(i<centralPoint.getGroup().size()) {
			if(centralPoint.getGroup().get(i).getTimeInCentralPoint()>20) {
				centralPoint.getGroup().get(i).setTimeInCentralPoint(0);
				centralPoint.getGroup().get(i).setRandomVelocity();
				g.add(centralPoint.getGroup().get(i));
				centralPoint.remove(i);
				i--;
			} else {
				centralPoint.getGroup().get(i).setTimeInCentralPoint(centralPoint.getGroup().get(i).getTimeInCentralPoint()+1);
			}
			i++;
		}
	}
	
	public void centralPoint() {
		
	}
	
	public void iterate(){
		updateConfig();
		outQuarantine();
		if(quarantining) {
			inQuarantine();
		}
		if(travel) {
			travel();
		}
		centralPoint();
		updateValues();
		iteration ++;
		int thisDay = iteration/ITERATIONS_PER_DAY;
		if(thisDay != day){
			day = thisDay;
			fillDataset();
		}
		
		repaint();
	}
	
	
		
	
}

