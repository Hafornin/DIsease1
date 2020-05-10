package disease;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;

import java.awt.Color;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener{
	
	//Attributes :
	private SimulationPanel groups;
	private int DELTA_T;
	private Timer timer;
	JButton Stop;
	JButton Quarantine;
	JButton CentralPoint;
	JSlider Mortality;
	JSlider InfectionCoeff;
	JSlider IndentifiedProportion;
	JSlider SocialDistance;
	
	
	//Constructor :
	public Simulation(SimulationPanel s, int dt){
		DELTA_T = dt;
		timer = new Timer(DELTA_T,this);
		groups = s;
		s.startQuarantine();
		
		setTitle("Simulation");
		setSize(1500, 900);
		setLocationRelativeTo(null); 
		setLayout(null);              
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel parametersPanel = new JPanel();
		parametersPanel.setBackground(Color.white);
		add(parametersPanel);
		parametersPanel.setLocation(750,0);
		parametersPanel.setSize(750,400);
		
		JButton Stop= new JButton("Stop simulation");
		JButton Quarantine= new JButton("Quarantine on/off");
		JButton CentralPoint= new JButton("Activate central point in groups");
		Stop.addActionListener(this);
		Quarantine.addActionListener(this);
		CentralPoint.addActionListener(this);
	
		
		Stop.setLocation(5,5);
		Quarantine.setLocation(5,25);
		CentralPoint.setLocation(5,45);
		
		parametersPanel.add(Stop);
		parametersPanel.add(Quarantine);
		parametersPanel.add(CentralPoint);
		
		
		JSlider Mortality = new JSlider();
		JSlider InfectionCoeff = new JSlider();
		JSlider IdentifiedProportion = new JSlider();
		JSlider SocialDistance = new JSlider();
		
		Mortality.setMaximum(100);
		Mortality.setMinimum(0);
		Mortality.setValue(30);
		Mortality.setPaintTicks(true);
		Mortality.setPaintLabels(true);
		Mortality.setMinorTickSpacing(1);
		Mortality.setMajorTickSpacing(10);
		Mortality.setVisible(true);
		
		parametersPanel.add(Mortality);
		
		InfectionCoeff.setMaximum(100);
		InfectionCoeff.setMinimum(0);
		InfectionCoeff.setValue(30);
		InfectionCoeff.setPaintTicks(true);
		InfectionCoeff.setPaintLabels(true);
		InfectionCoeff.setMinorTickSpacing(1);
		InfectionCoeff.setMajorTickSpacing(10);
		InfectionCoeff.setVisible(true);
		
		IdentifiedProportion.setMaximum(100);
		IdentifiedProportion.setMinimum(0);
		IdentifiedProportion.setValue(30);
		IdentifiedProportion.setPaintTicks(true);
		IdentifiedProportion.setPaintLabels(true);
		IdentifiedProportion.setMinorTickSpacing(1);
		IdentifiedProportion.setMajorTickSpacing(10);
		
		SocialDistance.setMaximum(100);
		SocialDistance.setMinimum(0);
		SocialDistance.setValue(30);
		SocialDistance.setPaintTicks(true);
		SocialDistance.setPaintLabels(true);
		SocialDistance.setMinorTickSpacing(1);
		SocialDistance.setMajorTickSpacing(10);
		
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.white);
		add(leftPanel);
		leftPanel.setLocation(0,0);
		leftPanel.setSize(30,900);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.white);
		add(topPanel);
		topPanel.setLocation(0,0);
		topPanel.setSize(750,30);
		
		add(groups);
		groups.setLocation(30,30);	
		groups.setVisible(true);
		
		JFreeChart graph = ChartFactory.createLineChart(
				"Graph",
		        "Time in days",
		        "Number of people",
		        groups.getData(),
		        PlotOrientation.VERTICAL,
		        true,
		        true,
		        false);
		
		ChartPanel graphPanel = new ChartPanel( graph );    
		graphPanel.setSize(750,500);         
		//chartPanel.setMouseZoomable( true , false );         
	    add( graphPanel );
	    graphPanel.setLocation(750,400);
	    graphPanel.setVisible(true);
	
	    
		timer.start();
	}

	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		groups.iterate();		
		repaint();
	}
		
	
}


