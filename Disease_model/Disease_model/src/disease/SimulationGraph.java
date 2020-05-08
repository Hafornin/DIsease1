package disease;
import java. awt.*;
import javax.swing.JPanel;
import javax.swing.*;


public class SimulationGraph extends JPanel{
	
	//Attributes :
	Group[] boxes;
	
	private int[][] numSusceptible;
	private int[][] numInfected;
	private int[][] numRecovered;
	private int[][] numIdentified;
	private final int HISTORY = 200;
	private final int X_AXIS_LENGTH = 700;
	private final int Y_AXIS_LENGTH = 440;
	private final int X_ZERO = 10;
	private final int Y_ZERO = 450;

	
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
		
		JLabel day = new JLabel("day : ");
		day.setLocation(600,0);
		day.setSize(100,100);
		
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
		int[][] t = new int[100][2];
		for(int i=0;i<5;i++){
			t[i][0] = i;
			t[i][1] = i;
		}
		plot(numSusceptible,Color.blue,g);		
	}
	
	public void drawAxis (Graphics g){
		g.setColor(Color.black);
		g.drawLine(X_ZERO,Y_ZERO-Y_AXIS_LENGTH,X_ZERO,Y_ZERO); //y
		g.drawLine(X_AXIS_LENGTH+X_ZERO,Y_ZERO,X_ZERO,Y_ZERO); //x
	}
	
	public void plot(int[][] points, Color c, Graphics g){
		g.setColor(c);
		int xStep = X_AXIS_LENGTH/HISTORY;
		int yStep = Y_AXIS_LENGTH/HISTORY;
		for(int i=0;i<points.length;i++){
			g.fillOval(xStep*i+10,445-yStep*points[i][1],5,5);
		}
	}		
	
}

