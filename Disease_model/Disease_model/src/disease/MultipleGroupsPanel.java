package disease;
import java. awt.*;
import java.util.LinkedList;
import java.util.*;

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
		
		for(int i=0;i<boxes.length-7;i++){
			for(int j=0;j<60;j++) {
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
	
	public void travelBetweenGroups() {
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
	
	public void update() {
		numSusceptible = 0;
		numInfected = 0;
		numRecovered = 0;
		numIdentified = 0;
		travelBetweenGroups();
		for(int i=0;i<boxes.length;i++){
			boxes[i].move();
			boxes[i].infect();
			boxes[i].updateValues();
			numSusceptible += boxes[i].getNumSusceptible();
			numInfected += boxes[i].getNumInfected();
			numRecovered += boxes[i].getNumRecovered();
			numIdentified += boxes[i].getNumIdentified();
		}
	}	
	
	
	public void startQuarantine() {
		
	}
	
	public void endQuarantine() {
		
	}
	
	public void quarantine() {
		for(int i=0;i<boxes.length;i++){
			
		}
	}
	
}

