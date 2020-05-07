import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener{
	
	//Attributes :
	SimulationGroup groups;
	SimulationGraph graph;
	int DELTA_T;
	Timer timer;
	
	//Constructor :
	public Simulation(SimulationGroup sgro, SimulationGraph sgra, int dt){
		DELTA_T = dt;
		timer = new Timer(DELTA_T,this);
		groups = sgro;
		graph = sgra;
		
		setTitle("Simulation");
		setSize(1500, 900);
		setLocationRelativeTo(null); 
		setLayout(null);              
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(groups);
		groups.setLocation(30,30);
		
		add(graph);
		graph.setLocation(770,400);
		
		
		timer.start();
	}
	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		groups.iterate();
		repaint();
	}
		
	
}

