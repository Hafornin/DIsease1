/**
 * This class inherits from JPanel. 
 * It represents the left part of the main simulation window.
 * It contains one or several Groups of Individuals that interact.
 */

package disease;

import java. awt.*;
import javax.swing.JPanel;
import org.jfree.data.category.DefaultCategoryDataset;

public abstract class SimulationPanel extends JPanel{
	
//ATTRIBUTES :
	
//=================== Disease propagation related attributes ========================
	
	/**
	 * the disease that propagates in the groups
	 */
	protected Disease disease;
	/**
	 * variable telling wether quarantine is on : TRUE if quarantine is on, FALSE otherwise
	 */
	protected boolean quarantining;
	
//=================== Attributes used to run the simulation ========================
	
		/**
		 * the iteration number
		 */
		protected int iteration;
		/**
		 * the day number since the start of the simulation
		 */
		protected int day;
		/**
		 * the number of iterations per day
		 */
		protected final int ITERATIONS_PER_DAY = 50;
		

//=================== Current and past data storage ========================
	
	/**
	 * the number of susceptible individuals in the simulation
	 */
	protected int numSusceptible;
	/**
	 * the number of infected individuals in the simulation
	 */
	protected int numInfected;
	/**
	 * the number of recovered individuals in the simulation
	 */
	protected int numRecovered;
	/**
	 * the number of identified infected individuals in the simulation
	 */
	protected int numIdentified;
	/**
	 * the number of dead individuals in the simulation
	 */
	protected int numDead;
	/**
	 * the dataset storing all the past numbers of susceptible, infected, recovered, identified and dead individuals in this simulation
	 */
	protected DefaultCategoryDataset  dataset;		
	
	
//CONSTRUCTOR :
	
	/**
	 * Creates an instance of SimulationPanel
	 * @param d the disease that propagates in the groups
	 */
	public SimulationPanel(Disease d){
		
		disease = d;
		disease.setInfectionTime((int) disease.getInfectionTime()*ITERATIONS_PER_DAY);
		
		//setting quarantine off by default
		quarantining = false;
		
		//initializing the day and iteration numbers to 0
		iteration = 0;
		day = 0;
		
		//initializing the variables storing general data about the simulation
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		numDead = 0;
		dataset = new DefaultCategoryDataset();
		
		//display
		setSize(750,900);  
		setBackground(Color.white);
		setVisible(true);
	}
	
//GETTERS :
	
	/**
	 * Gets the dataset storing all the past numbers of susceptible, infected, recovered, identified and dead individuals in this simulation
	 * @return the dataset storing all the past numbers of susceptible, infected, recovered, identified and dead individuals in this simulation
	 */
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	/**
	 * Gets the value of quarantining
	 * @return TRUE if quarantine is on, FALSE otherwise
	 */
	public boolean getQuarantining() {
		return quarantining;
	}
	
	/**
	 * Gets the disease propagating in this simulation
	 * @return the disease propagating in this simulation
	 */
	public Disease getDisease() {
		return disease;
	}
	
	/**
	 * Gets the number of iterations per day
	 * @return the number of iterations per day
	 */
	public int getIterationsPerDay() {
		return ITERATIONS_PER_DAY;
	}
	
//METHODS :
	
//=================== Making the individuals travel between groups ========================
	
	/**
	 * Method making the individuals travel between groups
	 */
	public void travel() {
		
	}
	
//=================== Implementing quarantine ========================
	
	/**
	 * Method to set quarantine on
	 */
	public void startQuarantine() {
		
	}
	
	/**
	 * Method to set quarantine off
	 */
	public void stopQuarantine() {
		
	}
	
	/**
	 * Method to take identified individuals to the quarantine box
	 */
	public void inQuarantine() {
		
	}
	
	/**
	 * Method to remove recovered individuals from the quarantine box
	 */
	public void outQuarantine() {
		
	}
	
	/**
	 * Method to take the identified individuals of one group to the quarantine box
	 * @param g the departure group
	 * @param q the quarantine group
	 */
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
	
	/**
	 * Method to take the recovered individuals from the the quarantine box to a group
	 * @param q the quarantine group
	 * @param g the arrival group
	 */
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
	
//=================== Implementing social distancing ========================
	
	/**
	 * Method implementing social distancing
	 */
	public void socialDistancing() {
		
	}
	
	
//=================== Implementing travel to a central point ========================
	
	/**
	 * Method making some individuals go from one group to the group representing its central point
	 * @param g the departure group
	 * @param centralPoint the central point group
	 */
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
	
	/**
	 * Method making individuals leave the central point after they have spent enough time there
	 * @param g the arrival group
	 * @param centralPoint the central point group
	 */
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
	
	/**
	 * Method implementing all displacements from and to the central point
	 */
	public void centralPoint() {
		
	}
	
//=================== Updating the configuration and the data ========================
	
	/**
	 * Method updating the configuration of individuals in the groups
	 */
	public void updateConfig() {
		
	}
	
	/**
	 * Method updating the number of susceptible, infected, recovered, identified and dead individuals
	 */
	public void updateValues() {
		
	}
	
	/**
	 * Method adding the information of the current day to the dataset
	 */
	public void fillDataset() {
		dataset.addValue(numInfected, "Infected", Integer.toString(day));
		dataset.addValue(numRecovered, "Recovered", Integer.toString(day));
		dataset.addValue(numSusceptible, "Susceptible", Integer.toString(day));
		dataset.addValue(numIdentified, "Identified", Integer.toString(day));
		dataset.addValue(numDead, "Dead", Integer.toString(day));
	}
	
	
//=================== Performing the iterations ========================
	
	/**
	 * Method performing every step of an iteration
	 */
	public void iterate(){
		updateConfig(); //update the configuration of individuals
		outQuarantine(); //remove recovered individuals from quarantine
		if(quarantining) {
			inQuarantine(); //take identified individuals to quarantine
		}
		travel(); //make the individuals travel between groups
		centralPoint(); //make the individuals go to the central point
		updateValues(); //update number of susceptible, infected, recovered, identified and dead individuals
		iteration ++; //increment the iteration number
		int thisDay = iteration/ITERATIONS_PER_DAY;
		if(thisDay != day){
			day = thisDay; //increment the day number
			fillDataset(); //add the number of susceptible, infected, recovered, identified and dead individuals for the current day to the dataset
		}
		repaint();
	}	
}

