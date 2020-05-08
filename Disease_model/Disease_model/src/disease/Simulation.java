package disease;
import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener{
	
	//Attributes :
	private SimulationGroup groups;
	private SimulationGraph graph;
	private int DELTA_T;
	private Timer timer;
	
	
	//Constructor :
	public Simulation(SimulationGroup sgro, Graph sgra, int dt){
		DELTA_T = dt;
		timer = new Timer(DELTA_T,this);
		groups = sgro;
		graph = sgra;
		
		setTitle("Simulation");
		setSize(1500, 900);
		setLocationRelativeTo(null); 
		setLayout(null);              
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(groups);
		groups.setLocation(30,30);
		
		add(sgra);
		graph.setLocation(770,400);
		
		
		timer.start();
	}
	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		groups.iterate();
		
		graph.setNumSusceptible(groups.getNumSusceptible());		
		graph.setNumInfected(groups.getNumInfected());
		graph.setNumRecovered(groups.getNumRecovered());
		graph.setNumIdentified(groups.getNumIdentified());
		
		repaint();
	}
		
	
}


