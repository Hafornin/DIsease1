/**
 * This class inherits from SimulationPanel.
 * It is a collection of groups displayed on a JPanel.
 */

package disease;

import java.util.LinkedList;
import org.jfree.data.category.DefaultCategoryDataset;

public class MultipleGroupsPanel extends SimulationPanel{
	
//ATTRIBUTES :
	
	/**
	 * the groups of individuals displayed
	 */
	private Group[] boxes;
	/**
	 * the quarantine box for each group displayed
	 */
	private Group[] quarantine;
	/**
	 * the central point for each group displayed
	 */
	private Group[] centralPoint;

	
//CONSTRUCTOR :
	
	/**
	 * Creates an instance of MultipleGroupsPanel
	 * @param d the disease propagating in the groups
	 */
	public MultipleGroupsPanel(Disease d){
		super(d);
		
		//initializing the groups, the central points and the quarantines
		boxes = new Group[8];
		centralPoint = new Group[boxes.length];
		quarantine = new Group[boxes.length];
		
		for(int i=0;i<boxes.length;i++){
			
			boxes[i] = new Group(disease.getGroupSize(), 10, 10, 200, 200, d);
			add(boxes[i]);
			
			centralPoint[i] = new Group(0, 10, 10, 10, 10, d);
			add(centralPoint[i]);
			
			quarantine[i] = new Group(0, 10, 10, 200, 200, disease);
			add(quarantine[i]);
			quarantine[i].setLocation(500,540);
			quarantine[i].setVisible(false); //as long as quarantine is off, the quarantine box is not visible
		}
		
		//infect 1/10 of the indidividuals in the top left group to start spreading the epidemic
		for(int i=0;i<=boxes[0].getGroup().size()/10;i++) {
			boxes[0].getGroup().get(i).initialInfect();
		}
		
		setLayout(null);
		
		//positioning the boxes
		boxes[0].setLocation(20,60);
		boxes[1].setLocation(260,60);
		boxes[2].setLocation(500,60);
		
		boxes[3].setLocation(20,300);
		boxes[4].setLocation(260,300);
		boxes[5].setLocation(500,300);
		
		boxes[6].setLocation(20,540);
		boxes[7].setLocation(260,540);
		
		//positioning the center of the boxes
		centralPoint[0].setLocation(115,155);
		centralPoint[1].setLocation(355,155);
		centralPoint[2].setLocation(595,155);
		
		centralPoint[3].setLocation(115,395);
		centralPoint[4].setLocation(355,395);
		centralPoint[5].setLocation(595,395);
		
		centralPoint[6].setLocation(115,635);
		centralPoint[7].setLocation(355,635);
		
		
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
	
//=================== Implementing travel between groups ========================
	
	/**
	 * Method making the individuals travel between groups
	 */
	public void travel() {
		int[] order = randomGroupOrder(); //create a random order for the groups
		/*
		 * create one temporary list of individuals per group
		 * the individuals removed from a group will be stored in this list before being added to the group they must go to
		 * this way, the individuals cannot be moved twice during the same iteration :
		 * otherwise, one individual could be moved from group1 to group2 and then from group2 to group5... and so on
		 */
		LinkedList<Individual>[] temporaryGroups = new LinkedList[boxes.length]; 
		for(int i=0;i<temporaryGroups.length;i++) {
			temporaryGroups[i] = new LinkedList<Individual>();
		}
		for(int i=0;i<boxes.length;i++) {
			for(int j=0;j<order.length;j++) {
				int a = order[j];
				if(i!=a) {
					int k = 0;
					while(k<boxes[i].getGroup().size()) {
						double test = Math.random();
						if(test<disease.getTravelBetweenGroups()) {
							boxes[i].getGroup().get(k).takeToCenter(); //move the individual that is going to be taken to another group to the central point
							temporaryGroups[a].add(boxes[i].getGroup().get(k)); //add the individual to the arrival group's temporary list
							boxes[i].remove(k);	//remove the individual from the departure group
							k--; //decrease k since one element has been removed from the group : the next element has taken its index
						}
						k++;
					}
				}
			}
		}
		for(int i=0;i<boxes.length;i++) {
			boxes[i].getGroup().addAll(temporaryGroups[i]); //add the individuals in the temporary lists to their arrival groups
		}		
	}
	
	/**
	 * Method used to order randomly the groups
	 * @return an array of integers representing a random order of groups
	 */
	public int[] randomGroupOrder() {
		int[] order = new int[boxes.length];
		for(int i=0;i<order.length;i++) {
			order[i] = -1;
		}
		for(int i=0;i<order.length;i++) {
			int r = (int) ((order.length)*Math.random());
			while(isInArray(order,r)==true) {
				r = (int) ((order.length)*Math.random());
			}
			order[i] = r;
		}
		for(int i=0;i<order.length;i++) {
			
		}
		return order;
	}
	
	/**
	 * Method to check if an integer is contained in an array of integers
	 * @param array the array of integers
	 * @param n the integer
	 * @return TRUE if the array contains this integer, FALSE otherwise
	 */
	public boolean isInArray(int[] array, int n) {
		boolean t = false;
		for(int i=0;i<array.length;i++) {
			if(array[i]==n) {
				t = true;
			}
		}
		return t;		
	}
	
	
//=================== Implementing travel to the central point ========================

	/**
	 * Method making some individuals in each group move to/from the central point
	 */
	public void centralPoint() {
		for(int i=0;i<boxes.length;i++) {
			goToCentralPoint(boxes[i],centralPoint[i]); //make some individuals go to the central point
			leaveCentralPoint(boxes[i],centralPoint[i]); //make individuals leave the central point after they have spent enough time there
		}
	}

	
//=================== Implementing quarantine ========================

	/**
	 * Method setting quarantine on
	 */
	public void startQuarantine() {
		quarantining = true;
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].setVisible(true); //set the quarantine box visible
		}
	}
	
	/**
	 * Method setting quarantine off
	 */
	public void stopQuarantine() {
		quarantining = false;
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].setVisible(false); //set the quarantine box invisible
		}
	}
	
	/**
	 * Method making identified individuals go to quarantine
	 */
	public void inQuarantine() {
		for(int i=0;i<boxes.length;i++){
			groupToQuarantine(boxes[i], quarantine[i]);
		}
	}
	
	/**
	 * Method making recovered individuals leave quarantine
	 */
	public void outQuarantine() {
		for(int i=0;i<boxes.length;i++){
			quarantineToGroup(quarantine[i], boxes[i]);
		}
	}
	

//=================== Implementing social distancing ========================
	
	/**
	 * Method setting the extent of social distancing
	 */
	public void socialDistancing() {
		for(int i=0;i<boxes.length;i++) {
			boxes[i].setSocialDistancing();
		}
	}
	
	
//=================== Updating the configuration and the data ========================
	
	/**
	 * Method updating the configuration of the individuals in the groups
	 */
	public void updateConfig() {
		for(int i=0;i<boxes.length;i++){
			boxes[i].move(); //move individuals inside each group
			boxes[i].infect(); //infect individuals inside each group
			boxes[i].identify(); //identify some of the infected individuals inside each group
			boxes[i].grimReaper(); //make some individuals die from the disease inside each group
		}
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].move(); //move individuals in the quarantine box
			quarantine[i].infect(); //update the state of the quarantined individuals
			quarantine[i].grimReaper(); //make some individuals die from the disease in the quarantine box
		}
		for(int i=0;i<centralPoint.length;i++){
			centralPoint[i].move(); //move individuals in each central point group
			centralPoint[i].infect(); //infect individuals in each central point group
			centralPoint[i].identify(); //identify some of the infected individuals inside each central point group
			centralPoint[i].grimReaper(); //make some individuals die from the disease inside each central point group
		}
	}	
	
	/**
	 * Method updating the overall number of susceptible, infected, recovered, identified and dead individuals
	 */
	public void updateValues() {
		//set the number of susceptible, infected, recovered, identified and dead individuals to 0
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified = 0;
		numDead = 0;
		for(int i=0;i<boxes.length;i++){
			boxes[i].updateValues(); //update the number of susceptible, infected, recovered, identified and dead individuals in each group
			//add the number of susceptible, infected, recovered, identified and dead individuals in each group to the overall number
			numSusceptible += boxes[i].getNumSusceptible();
			numInfected += boxes[i].getNumInfected();
			numRecovered += boxes[i].getNumRecovered();
			numIdentified += boxes[i].getNumIdentified();
			numDead += boxes[i].getNumDead();
		}
		for(int i=0;i<centralPoint.length;i++){
			centralPoint[i].updateValues(); // update the number of susceptible, infected, recovered, identified and dead individuals in each central point
			//add the number of susceptible, infected, recovered, identified and dead individuals in each central point to the overall number
			numSusceptible += centralPoint[i].getNumSusceptible();
			numInfected += centralPoint[i].getNumInfected();
			numRecovered += centralPoint[i].getNumRecovered();
			numIdentified += centralPoint[i].getNumIdentified();
			numDead += centralPoint[i].getNumDead();
		}		
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].updateValues(); //updating the number of infected and dead individuals in each quarantine group
			//adding the number of infected and dead individuals in each quarantine group to the overall total
			numInfected += quarantine[i].getNumInfected();
			numDead += quarantine[i].getNumDead();
		}
	}
	
}

