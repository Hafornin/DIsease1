package disease;
import java.util.LinkedList;

import org.jfree.data.category.DefaultCategoryDataset;

public class OneGroupPanel extends SimulationPanel{
	
	//Attributes :
	private Group box;
	private Group quarantine;
	private Group centralPoint;
	
	//Constructor :
	public OneGroupPanel(Disease d){
		super(d);
		box = new Group(disease.getGroupSize(), 20, 20, 400, 400, d);
		
		for(int j=0;j<15;j++) {
			box.getGroup().get(j).initialInfect();
		}
		
		setLayout(null);
		
		add(box);
		box.setLocation(50, 50);
		
		quarantine = new Group(0, 10, 10, 200, 200, disease);
		add(quarantine);
		quarantine.setLocation(480,520);
		quarantine.setVisible(false);
		
		centralPoint = new Group(0, 10, 10, 10, 10, disease);
		add(centralPoint);
		centralPoint.setLocation(255,255);
		centralPoint.setVisible(true);
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :
	
	
	public void goToCentralPoint() {
		int i = 0;
		while(i<box.getGroup().size()) {
			double test = Math.random();
			if(test<disease.getCentralPointTripProba()) {
				box.getGroup().get(i).takeToCenter();
				centralPoint.add(box.getGroup().get(i));
				box.remove(i);
				i--;
			}
			i++;
		}
	}
	
	public void leaveCentralPoint() {
		int i = 0;
		while(i<centralPoint.getGroup().size()) {
			if(centralPoint.getGroup().get(i).getTimeInCentralPoint()>20) {
				centralPoint.getGroup().get(i).setTimeInCentralPoint(0);
				centralPoint.getGroup().get(i).setRandomVelocity();
				box.add(centralPoint.getGroup().get(i));
				centralPoint.remove(i);
				i--;
			} else {
				centralPoint.getGroup().get(i).setTimeInCentralPoint(centralPoint.getGroup().get(i).getTimeInCentralPoint()+1);
			}
			i++;
		}
	}
	
	public void travel() {
		goToCentralPoint();
		leaveCentralPoint();
	}
	
	public void stopTravel() {
		travel = false;
		int i = 0;
		while(i<centralPoint.getGroup().size()) {
			box.add(centralPoint.getGroup().get(i));
			centralPoint.remove(i);
			i--;
		}
		centralPoint.setVisible(false);
	}
	
	public void startTravel() {
		travel = true;
		centralPoint.setVisible(true);
	}
	
	public void startQuarantine() {
		quarantining = true;
		quarantine.setVisible(true);
	}
	
	public void stopQuarantine() {
		quarantining = false;
		quarantine.setVisible(false);
		
	}
	
	public void inQuarantine() {
		groupToQuarantine(box, quarantine);
	}
	
	public void outQuarantine() {
		quarantineToGroup(quarantine, box);
	}
	
	public void updateConfig() {
		box.move();
		box.infect();
		box.identify();
		
		quarantine.move();
		quarantine.infect();
	}	
		
	
	public void updateValues() {		
		box.updateValues();
		numSusceptible = box.getNumSusceptible();
		numInfected = box.getNumInfected();
		numRecovered = box.getNumRecovered();
		numIdentified = box.getNumIdentified();
		
		quarantine.updateValues();
		numInfected += quarantine.getNumInfected();
		
		centralPoint.updateValues();
		numSusceptible += centralPoint.getNumSusceptible();
		numInfected += centralPoint.getNumInfected();
		numRecovered += centralPoint.getNumRecovered();
		numIdentified += centralPoint.getNumIdentified();
	}
	
}

