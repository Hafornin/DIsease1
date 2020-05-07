import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class SimulationGraph extends JPanel{
	
	//Attributes :
	Group[] boxes;
	
	//Constructor :
	public SimulationGraph(Group[] b){
		
		setVisible(true);
		setSize(750,500);
		setOpaque(true);
		setBackground(Color.blue);
		
	}
	
	//Methods :
	
	public void paintComponent(Graphics g){
		drawAxis(g);
		
	}
	
	public void drawAxis (Graphics g){
		g.setColor(Color.black);
		g.drawLine(10,10,10,450); //y
		g.drawLine(700,450,10,450); //x
	}
	

		
	
}

