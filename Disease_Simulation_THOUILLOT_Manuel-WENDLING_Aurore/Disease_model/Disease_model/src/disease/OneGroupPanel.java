/**
 * This class inherits from SimulationPanel
 * It is a Group and a quarantine box displayed on a JPanel.
 * 
 * This class was created to allow a different display, with only one group.
 * Due to lack of time, it is not finished and fully functional (there are issues with the quarantine box).
 * However, it is possible to use it if you want to try : the program will not crash, but there will be display issues.
 * If you want to use it, in the StartWindow class, replace line 143 :
 * SimulationPanel p = new MultipleGroupsPanel(d);
 * by :
 * SimulationPanel p = new OneGroupPanel(d);
 */
package disease;
import org.jfree.data.category.DefaultCategoryDataset;

public class OneGroupPanel extends SimulationPanel{
	
//ATTRIBUTES :
	
	/**
	 * the group we run the simulation on
	 */
	private Group box;
	/**
	 * the quarantine box
	 */
	private Group quarantine;
	/**
	 * the central point of the group we run the simulation on
	 */
	private Group centralPoint;
	
	
//CONSTRUCTOR :
	
	/**
	 * Creates an instance of OneGroupPanel
	 * @param d the disease propagating in the group
	 */
	public OneGroupPanel(Disease d){
		super(d);
		
		//creating the group
		box = new Group(disease.getGroupSize(), 20, 20, 400, 400, d);
		
		//infecting 1/10 of the individuals in the group to start the epidemic
		for(int j=0;j<box.getGroup().size()/10;j++) {
			box.getGroup().get(j).initialInfect();
		}
		
		setLayout(null);
		
		add(box);
		box.setLocation(50, 50);
		
		//creating the quarantine group
		quarantine = new Group(0, 10, 10, 200, 200, disease);
		add(quarantine);
		quarantine.setLocation(480,520);
		quarantine.setVisible(false); //the quarantine is not visible by default
		
		//creating the central point
		centralPoint = new Group(0, 10, 10, 10, 10, disease);
		add(centralPoint);
		centralPoint.setLocation(255,255);
		centralPoint.setVisible(true);
	}
	
//GETTERS :
	
	/**
	 * Gets the dataset containing the number of susceptible, infected, recovered, identified and dead individuals
	 * @return the dataset containing the number of susceptible, infected, recovered, identified and dead individuals
	 */
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
//METHODS :
	
	
//=================== Implementing travel to the central point ========================
	/**
	 * Method Method making some individuals in each group move to/from the central point
	 */
	public void centralPoint() {
		goToCentralPoint(box,centralPoint);
		leaveCentralPoint(box,centralPoint);
	}
	
	
//=================== Implementing quarantine ========================

	/**
	 * Method setting quarantine on
	 */
	public void startQuarantine() {
		quarantining = true;
		quarantine.setVisible(true);
	}
	
	/**
	 * Method setting quarantine off
	 */
	public void stopQuarantine() {
		quarantining = false;
		quarantine.setVisible(false);
		
	}
	
	/**
	 * Method making identified individuals go to quarantine
	 */
	public void inQuarantine() {
		groupToQuarantine(box, quarantine);
	}
	
	/**
	 * Method making recovered individuals leave quarantine
	 */
	public void outQuarantine() {
		quarantineToGroup(quarantine, box);
	}
	
	
//=================== Implementing social distancing========================

	/**
	 * Method setting the extent of social distancing
	 */
	public void socialDistancing() {
		box.setSocialDistancing();
	}
	
	
//=================== Updating the configuration and the data ========================
	
	/**
	 * Method updating the configuration of the individuals in the group
	 */
	public void updateConfig() {
		box.move();
		box.infect();
		box.identify();
		box.grimReaper();
		
		quarantine.move();
		quarantine.infect();
		quarantine.grimReaper();
		
		centralPoint.move();
		centralPoint.infect();
		centralPoint.identify();
		centralPoint.grimReaper();
	}	
		
	/**
	 * Method updating the overall number of susceptible, infected, recovered, identified and dead individuals
	 */
	public void updateValues() {		
		box.updateValues();
		numSusceptible = box.getNumSusceptible();
		numInfected = box.getNumInfected();
		numRecovered = box.getNumRecovered();
		numIdentified = box.getNumIdentified();
		numDead = box.getNumDead();
		
		quarantine.updateValues();
		numInfected += quarantine.getNumInfected();
		numIdentified += quarantine.getNumIdentified();
		numDead += quarantine.getNumDead();
		
		centralPoint.updateValues();
		numSusceptible += centralPoint.getNumSusceptible();
		numInfected += centralPoint.getNumInfected();
		numRecovered += centralPoint.getNumRecovered();
		numIdentified += centralPoint.getNumIdentified();
		numDead += centralPoint.getNumDead();
	}
	
}

