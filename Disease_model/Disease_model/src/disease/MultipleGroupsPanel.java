package disease;
import java. awt.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class MultipleGroupsPanel extends SimulationPanel{
	
	//Attributes :
	private Group[] boxes;
	
	
	//Constructor :
	public MultipleGroupsPanel(Disease d , int nGroups){
		super(d);
		boxes = new Group[nGroups];
		for(int i=0;i<nGroups;i++){
			boxes[i] = new Group(disease.getGroupSize(), 10, 10, 200, 200, d);
		}
		
		for(int i=0;i<boxes.length;i++){
			for(int j=0;j<10;j++) {
				boxes[i].getGroup().get(j).initialInfect();
			}
		}
		
		setLayout(new GridLayout(3,3));
		
		for(int i=0;i<boxes.length;i++){
			add(boxes[i]);
		}
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :
	
	public void travelBetweenGroups(int k) {
		int[] randomBoxChoice = new int[boxes.length];
		for(int i=0;i<randomBoxChoice.length;i++) {
			randomBoxChoice[i] = -1;
		}
		int i = 0;
		while(i<boxes[k].getGroup().size()) {
			while(randomBoxChoice.includes(-1))
			int choice = (int) boxes.length*Math.random();
			
		}
		
		
		while(i<boxes[k].getGroup().size()) {
			int j=0;
			int ip = i;
			while(j<boxes.length && ip==i) {
				double test = Math.random();
				if(test<boxes[k].getLeaveGroupProba()*boxes[j].getEnterGroupProba()) {
					boxes[j].getGroup().add(boxes[k].getGroup().get(i));
					boxes[k].getGroup().remove(i);
					i--;
				}
			}
			i++;
		}			
	}
	
	public void updateDataValues() {
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified =0;
		for(int i=0;i<boxes.length;i++){
			travelBetweenGroups(i);
			boxes[i].move();
			boxes[i].infect();
			boxes[i].updateValues();
			numSusceptible += boxes[i].getNumSusceptible();
			numInfected += boxes[i].getNumInfected();
			numRecovered += boxes[i].getNumRecovered();
			numIdentified += boxes[i].getNumIdentified();
		}
	}	
	
}

