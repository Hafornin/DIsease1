package disease;
import java. awt.*;
import java.util.LinkedList;

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
		int i = 0;
		while(i<boxes[k].getGroupSize()) {
			boolean[] array = new boolean[boxes.length];
			array[k] = true;
			int s = (boxes.length+1)*boxes.length/2;
			int s2 = k;
			while(s2 < s) {
				double c = (boxes.length-1)*Math.random();
				int choice = (int) c;
				if(array[choice]==false) {
					s += choice;
					array[choice] = true;
					double test = Math.random();
					if(test<boxes[k].getLeaveGroupProba()*boxes[choice].getEnterGroupProba()) {
						boxes[choice].getGroup().add(boxes[k].getGroup().get(i));
						boxes[k].getGroup().remove(i);
						i--;    
						s2 = s;
					}
				
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
			//travelBetweenGroups(i);
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

