import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class SimulationGraph extends JPanel{
	
	//Attributes :
	Group[] boxes;
	
	private int[][] numSusceptible;
	private int[][] numInfected;
	private int[][] numRecovered;
	private int[][] numIdentified;
	private final int HISTORY = 100;
	
	//Constructor :
	public SimulationGraph(Group[] b){
		
		setVisible(true);
		setSize(750,500);
		setOpaque(true);
		setBackground(Color.blue);
		
		numSusceptible = new int[HISTORY][2];
		numInfected = new int[HISTORY][2];
		numRecovered = new int[HISTORY][2];
		numIdentified = new int[HISTORY][2];
		
	}
	
	//Setters :
	
	public void setNumSusceptible(int[][] num){
		for(int i=0;i<num.length;i++){
			numSusceptible[i][0] = num[i][0];
			numSusceptible[i][1] = num[i][1];
		}
	}
	
	public void setNumInfected(int[][] num){
		for(int i=0;i<num.length;i++){
			numInfected[i][0] = num[i][0];
			numInfected[i][1] = num[i][1];
		}
	}
	
	public void setNumRecovered(int[][] num){
		for(int i=0;i<num.length;i++){
			numRecovered[i][0] = num[i][0];
			numRecovered[i][1] = num[i][1];
		}
	}
	
	public void setNumIdentified(int[][] num){
		for(int i=0;i<num.length;i++){
			numIdentified[i][0] = num[i][0];
			numIdentified[i][1] = num[i][1];
		}
	}
			
	
	//Methods :
	
	public void paintComponent(Graphics g){
		drawAxis(g);
		plot(numSusceptible,g);		
	}
	
	public void drawAxis (Graphics g){
		g.setColor(Color.black);
		g.drawLine(10,10,10,450); //y
		g.drawLine(700,450,10,450); //x
	}
	
	public void plot(int[][] points, Graphics g){
		g.setColor(Color.black);
		for(int i=0;i<points.length;i++){
			g.fillOval(points[i][0]+10,points[i][1]+10,5,5);
		}
	}		
	
}

