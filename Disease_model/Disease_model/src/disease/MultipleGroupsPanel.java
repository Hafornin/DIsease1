package disease;
import java. awt.*;
import java.util.LinkedList;
import java.util.*;

import org.jfree.data.category.DefaultCategoryDataset;

public class MultipleGroupsPanel extends SimulationPanel{
	
	//Attributes :
	private Group[] boxes;
	private Group[] quarantine;
	
	
	//Constructor :
	public MultipleGroupsPanel(Disease d){
		super(d);
		boxes = new Group[8];
		for(int i=0;i<boxes.length;i++){
			boxes[i] = new Group(disease.getGroupSize(), 10, 10, 200, 200, d);
		}
		
		for(int i=0;i<boxes.length-7;i++){
			for(int j=0;j<30;j++) {
				boxes[i].getGroup().get(j).initialInfect();
			}
		}
		
		setLayout(null);
		
		for(int i=0;i<boxes.length;i++){
			add(boxes[i]);
		}
		
		boxes[0].setLocation(20,60);
		boxes[1].setLocation(260,60);
		boxes[2].setLocation(500,60);
		
		boxes[3].setLocation(20,300);
		boxes[4].setLocation(260,300);
		boxes[5].setLocation(500,300);
		
		boxes[6].setLocation(20,540);
		boxes[7].setLocation(260,540);
	
		
		
		//quarantine
		quarantine = new Group[boxes.length];
		for(int i=0;i<quarantine.length;i++){
			quarantine[i] = new Group(0, 10, 10, 200, 200, disease);
			add(quarantine[i]);
			quarantine[i].setLocation(500,520);
			quarantine[i].setVisible(false);
		}
		
		
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :
	
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
	
	public boolean isInArray(int[] array, int n) {
		boolean t = false;
		for(int i=0;i<array.length;i++) {
			if(array[i]==n) {
				t = true;
			}
		}
		return t;		
	}
	
	public void startTravel() {
		travel = true;
	}
	
	public void stopTravel() {
		travel = false;
	}
	
	public void travel() {
		int[] order = randomGroupOrder();
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
						if(test<boxes[i].getLeaveGroupProba()*boxes[a].getEnterGroupProba()) {
							temporaryGroups[a].add(boxes[i].getGroup().get(k));
							boxes[i].remove(k);	
							k--;
						}
						k++;
					}
				}
			}
		}
		for(int i=0;i<boxes.length;i++) {
			boxes[i].getGroup().addAll(temporaryGroups[i]);
		}		
	}
	
	public void updateConfig() {
		for(int i=0;i<boxes.length;i++){
			boxes[i].move();
			boxes[i].infect();
			boxes[i].identify();
		}
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].move();
			quarantine[i].infect();
		}
	}	
	
	public void updateValues() {
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified = 0;
		for(int i=0;i<boxes.length;i++){
			boxes[i].updateValues();
			numSusceptible += boxes[i].getNumSusceptible();
			numInfected += boxes[i].getNumInfected();
			numRecovered += boxes[i].getNumRecovered();
			numIdentified += boxes[i].getNumIdentified();
		}
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].updateValues();
			numInfected += quarantine[i].getNumInfected();
		}
	}
	
	
	public void startQuarantine() {
		quarantining = true;
		for(int i=0;i<quarantine.length;i++){
			quarantine[i].setVisible(true);
		}
	}
	
	public void inQuarantine() {
		for(int i=0;i<boxes.length;i++){
			groupToQuarantine(boxes[i], quarantine[i]);
		}
	}
	
	public void outQuarantine() {
		for(int i=0;i<boxes.length;i++){
			quarantineToGroup(quarantine[i], boxes[i]);
		}
	}
	
}

