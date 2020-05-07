import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class SimulationGroup extends JPanel{
	
	//Attributes :
	private Group[] boxes;
	private int[][] numSusceptible;
	private int[][] numInfected;
	private int[][] numRecovered;
	private int[][] numIdentified;
	private final int HISTORY = 100;
	private int iteration;
	private int day;
	private final int ITERATIONS_PER_DAY = 10;
	
	
	//Constructor :
	public SimulationGroup(Group[] b){
		boxes = new Group[b.length];
		for(int i=0;i<boxes.length;i++){
			boxes[i] = b[i];
		}
		
		setLayout(new GridLayout(3,3));
		setSize(750,800);         
		setVisible(true);
		
		for(int i=0;i<boxes.length;i++){
			add(boxes[i]);
		}
		
		numSusceptible = new int[HISTORY][2];
		numInfected = new int[HISTORY][2];
		numRecovered = new int[HISTORY][2];
		numIdentified = new int[HISTORY][2];
		
		iteration = 0;
		day = 0;
	}
	
	//Getters :
	
	public int[][] getNumSusceptible(){
		return numSusceptible;
	}
	
	public int[][] getNumInfected(){
		return numInfected;
	}
	
	public int[][] getNumRecovered(){
		return numRecovered;
	}
	
	public int[][] getNumIdentified(){
		return numIdentified;
	}
	
	public int getDay(){
		return day;
	}
	
	//Methods :
	
	//Method performing the iterations
	public void iterate(){
		iteration ++;
		day = iteration/ITERATIONS_PER_DAY;
		numSusceptible[day%HISTORY][0] = day;
		numInfected[day%HISTORY][0] = day;
		numRecovered[day%HISTORY][0] = day;
		numIdentified[day%HISTORY][0] = day;
		for(int i=0;i<boxes.length;i++){
			boxes[i].move();
			boxes[i].infect();
			boxes[i].updateValues();
			numSusceptible[day%HISTORY][1] += boxes[i].getNumSusceptible();
			numInfected[day%HISTORY][1] += boxes[i].getNumInfected();
			numRecovered[day%HISTORY][1] += boxes[i].getNumRecovered();
			numIdentified[day%HISTORY][1] += boxes[i].getNumIdentified();
		}
		repaint();
	}
		
	
}

