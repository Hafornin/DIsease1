package disease;
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
	JPanel parametersPanel;
	JButton restart;
	JButton pause;
	JButton quarantine;
	JSlider mortality;
	JSlider infectionCoeff;
	JSlider identifiedProportion;
	JSlider socialDistanceCoeff;
	JSlider centralPointTripProba;
	JSlider travelBetweenGroups;
	
	
	//Constructor :
	public Simulation(SimulationPanel s, int dt){
		DELTA_T = dt;
		timer = new Timer(DELTA_T,this);
		groups = s;
		
		setTitle("Simulation");
		setSize(1500, 900);
		setLocationRelativeTo(null); 
		setLayout(null);              
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//Groups panel
		add(groups);
		groups.setLocation(0,0);	
		groups.setVisible(true);
//Graph panel	
		
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
	    graphPanel.setLocation(750,370);
	    graphPanel.setVisible(true);	
		
//Parameters panel
		
	    parametersPanel = new JPanel();	
	    parametersPanel.setBackground(Color.white);
	    parametersPanel.setLayout(null);
	    
	    pause = new JButton("Start");
		pause.setVisible(true);
		pause.addActionListener(this);
		parametersPanel.add(pause);
		pause.setLocation(550,90);
		pause.setSize(150,40);
		
		quarantine = new JButton("Quarantine on");
		quarantine.setVisible(true);
		quarantine.addActionListener(this);
		parametersPanel.add(quarantine);
		quarantine.setLocation(550,190);
		quarantine.setSize(150,40);
		
		restart = new JButton("New Simulation");
		restart.setVisible(true);
		restart.addActionListener(this);
		parametersPanel.add(restart);
		restart.setLocation(550,290);
		restart.setSize(150,40);
		
		infectionCoeff = new JSlider();
		infectionCoeff.setBackground(Color.white);
		infectionCoeff.setMaximum(100);
		infectionCoeff.setMinimum(0);
		infectionCoeff.setValue(30);
		infectionCoeff.setPaintTicks(false);
		infectionCoeff.setPaintLabels(false);
		infectionCoeff.setMinorTickSpacing(1);
		infectionCoeff.setMajorTickSpacing(10);
		infectionCoeff.setVisible(true);
		parametersPanel.add(infectionCoeff);
		infectionCoeff.setLocation(170,90);
		infectionCoeff.setSize(300,50);
		
		mortality = new JSlider();		
		mortality.setBackground(Color.white);
		mortality.setMaximum(100);
		mortality.setMinimum(0);
		mortality.setValue(30);
		mortality.setPaintTicks(false);
		mortality.setPaintLabels(false);
		mortality.setMinorTickSpacing(1);
		mortality.setMajorTickSpacing(10);
		mortality.setVisible(true);
		parametersPanel.add(mortality);	
		mortality.setLocation(170,130);
		mortality.setSize(300,50);
		
		identifiedProportion = new JSlider();
		identifiedProportion.setBackground(Color.white);
		identifiedProportion.setMaximum(100);
		identifiedProportion.setMinimum(0);
		identifiedProportion.setValue(30);
		identifiedProportion.setPaintTicks(false);
		identifiedProportion.setPaintLabels(false);
		identifiedProportion.setMinorTickSpacing(1);
		identifiedProportion.setMajorTickSpacing(10);
		identifiedProportion.setVisible(true);
		parametersPanel.add(identifiedProportion);
		identifiedProportion.setLocation(170,170);
		identifiedProportion.setSize(300,50);
		
		socialDistanceCoeff = new JSlider();
		socialDistanceCoeff.setBackground(Color.white);
		socialDistanceCoeff.setMaximum(100);
		socialDistanceCoeff.setMinimum(0);
		socialDistanceCoeff.setValue(30);
		socialDistanceCoeff.setPaintTicks(false);
		socialDistanceCoeff.setPaintLabels(false);
		socialDistanceCoeff.setMinorTickSpacing(1);
		socialDistanceCoeff.setMajorTickSpacing(10);
		socialDistanceCoeff.setVisible(true);
		parametersPanel.add(socialDistanceCoeff);
		socialDistanceCoeff.setLocation(170,210);
		socialDistanceCoeff.setSize(300,50);
		
		centralPointTripProba = new JSlider();
		centralPointTripProba.setBackground(Color.white);
		centralPointTripProba.setMaximum(100);
		centralPointTripProba.setMinimum(0);
		centralPointTripProba.setValue(30);
		centralPointTripProba.setPaintTicks(false);
		centralPointTripProba.setPaintLabels(false);
		centralPointTripProba.setMinorTickSpacing(1);
		centralPointTripProba.setMajorTickSpacing(10);
		centralPointTripProba.setVisible(true);
		parametersPanel.add(centralPointTripProba);
		centralPointTripProba.setLocation(170,250);
		centralPointTripProba.setSize(300,50);
		
		travelBetweenGroups = new JSlider();
		travelBetweenGroups.setBackground(Color.white);
		travelBetweenGroups.setMaximum(100);
		travelBetweenGroups.setMinimum(0);
		travelBetweenGroups.setValue(30);
		travelBetweenGroups.setPaintTicks(false);
		travelBetweenGroups.setPaintLabels(false);
		travelBetweenGroups.setMinorTickSpacing(1);
		travelBetweenGroups.setMajorTickSpacing(10);
		travelBetweenGroups.setVisible(true);
		parametersPanel.add(travelBetweenGroups);
		travelBetweenGroups.setLocation(170,290);
		travelBetweenGroups.setSize(300,50);
		
		
		add(parametersPanel);
		parametersPanel.setLocation(750,0);
		parametersPanel.setSize(750,370);
		parametersPanel.setVisible(true);
		
		setVisible(true);
		timer.start();
	}

	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==timer) {
			groups.iterate();		
			repaint();
		}
		if(e.getSource()==restart) {
			timer.stop();
		}
		if(e.getSource()==pause) {
			if(pause.getText()=="Pause") {
				timer.stop();
				pause.setText("Start");
			}else {
				timer.start();
				pause.setText("Pause");
			}
			
		}
		if(e.getSource()==quarantine) {
			if(groups.getQuarantining()) {
				groups.stopQuarantine();
				quarantine.setText("Quarantine on");
				parametersPanel.repaint();
				groups.repaint();
			}else {
				groups.startQuarantine();
				quarantine.setText("Quarantine off");
				parametersPanel.repaint();
				groups.repaint();
			}
		}
		if(e.getSource()==centralPointTripProba) {
			
		}
		if(e.getSource()==mortality) {
			
		}
		if(e.getSource()==infectionCoeff) {
			
		}
		if(e.getSource()==identifiedProportion) {
			
		}
		if(e.getSource()==socialDistanceCoeff) {
			
		}
		if(e.getSource()==travelBetweenGroups) {
			
		}
	}
		
	
}


