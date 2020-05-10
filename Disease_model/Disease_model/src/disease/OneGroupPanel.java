package disease;
import java. awt.*;

import org.jfree.data.category.DefaultCategoryDataset;

public class OneGroupPanel extends SimulationPanel{
	
	//Attributes :
	private Group box;
	private Group quarantine;
	
	
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
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :
	
	public void startQuarantine() {
		quarantining = true;
		quarantine.setVisible(true);
	}
	
	public void endQuarantine() {
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
	}
	
}

