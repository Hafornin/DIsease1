/**
 * This class inherits from JFrame.
 * It is the main display window.
 * It includes :
 * - a SimulationPanel containing the displayed groups of individuals
 * - a graph displaying the number of susceptible, infected, recovered, identified and dead people with respect to time
 * - a set of buttons and sliders to change some parameters that have an influence on the propagation of the disease
 */

package disease;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.event.*;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;

public class Simulation extends JFrame implements ActionListener, ChangeListener{
	
//ATTRIBUTES :
	
//=================== Simulation-related attributes ========================
	
	/**
	 * the SimulationPanel containing and displaying the groups of individuals
	 */
	private SimulationPanel groups;
	
	/**
	 * the time step for the displacement of the individuals
	 */
	private int DELTA_T = 10;
	
	/**
	 * the timer
	 */
	private Timer timer;
	
//=================== User interaction components ========================
	
	/**
	 * the JPanel containing the user interaction components
	 */
	private JPanel parametersPanel;
	
	/**
	 * the button allowing to start a new simulation
	 */
	private JButton restart;
	/**
	 * the button allowing to start/pause the simulation
	 */
	private JButton pause;
	/**
	 * the button allowing to set quarantine on/off
	 */
	private JButton quarantine;
	
	/**
	 * the slider allowing to set the mortality of the disease
	 */
	private JSlider mortality;
	/**
	 * the label showing the mortality of the disease
	 */
	private JLabel mortalityLabel;
	/**
	 * the slider allowing to set the infectiousness of the disease
	 */
	private JSlider infectionCoeff;
	/**
	 * the label showing the infectiousness of the disease
	 */
	private JLabel infectionCoeffLabel;
	/**
	 * the slider allowing to set the proportion of identified infected individuals
	 */
	private JSlider identifiedProportion;
	/**
	 * the label showing the proportion of identified individuals
	 */
	private JLabel identifiedProportionLabel;
	/**
	 * the slider allowing to set the extent of social distancing
	 */
	private JSlider socialDistanceCoeff;
	/**
	 * the label showing the extent of social distancing
	 */
	private JLabel socialDistanceCoeffLabel;
	/**
	 * the slider allowing to set the probability of going to the central point
	 */
	private JSlider centralPointTripProba;
	/**
	 * the label showing the probability of going to the central point
	 */
	private JLabel centralPointTripProbaLabel;
	/**
	 * the slider allowing to set the amount of travel between groups
	 */
	private JSlider travelBetweenGroups;
	/**
	 * the label showing the amount of travel between groups
	 */
	private JLabel travelBetweenGroupsLabel;

	
//CONSTRUCTOR :
	/**
	 * Creates an instance of Simulation
	 * @param s the SimulationPanel containing the groups we run the simulation on
	 */
	public Simulation(SimulationPanel s){
		
		timer = new Timer(DELTA_T,this); //adding a timer
		
		//setting the main display parameters for this window
		setTitle("Simulation");
		setSize(1500, 900);
		setLocationRelativeTo(null); 
		setLayout(null);              
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//adding the SimulationPanel containing the groups we run the simulation on
		groups = s;
		add(groups);
		groups.setLocation(0,0);	
		groups.setVisible(true);
		
		//creating the graph
		JFreeChart graph = ChartFactory.createLineChart(
				"Propagation of the disease in the population", //title
		        "Time in days", //x axis label
		        "Number of people", //y axis label
		        groups.getData() //set of data plotted
		        );
		//creating and adding the panel containing the graph
		ChartPanel graphPanel = new ChartPanel(graph);    
		graphPanel.setSize(750,500);                  
	    add( graphPanel );
	    graphPanel.setLocation(750,370);
	    graphPanel.setVisible(true);	
		
	    //creating the panel containing the user interaction components
	    parametersPanel = new JPanel();	
	    parametersPanel.setBackground(Color.white);
	    parametersPanel.setLayout(null);
	    
	    //creating the start/pause button
	    pause = new JButton("Start");
		pause.setVisible(true);
		pause.addActionListener(this);
		parametersPanel.add(pause);
		pause.setLocation(550,90);
		pause.setSize(150,40);
		
		//creating the quarantine button
		quarantine = new JButton("Quarantine on");
		quarantine.setVisible(true);
		quarantine.addActionListener(this);
		parametersPanel.add(quarantine);
		quarantine.setLocation(550,190);
		quarantine.setSize(150,40);
		
		//creating the restart button
		restart = new JButton("New Simulation");
		restart.setVisible(true);
		restart.addActionListener(this);
		parametersPanel.add(restart);
		restart.setLocation(550,290);
		restart.setSize(150,40);
		
		//creating the label showing the infectiousness of the disease
		infectionCoeffLabel = new JLabel("Infectiousness : ");
		infectionCoeffLabel.setVisible(true);
		parametersPanel.add(infectionCoeffLabel);
		infectionCoeffLabel.setLocation(20,90);
		infectionCoeffLabel.setSize(150,50);
		
		//creating the slider allowing to set the infectiousness of the disease
		infectionCoeff = new JSlider();
		infectionCoeff.addChangeListener(this);
		infectionCoeff.setBackground(Color.white);
		infectionCoeff.setMaximum(100);
		infectionCoeff.setMinimum(0);
		infectionCoeff.setValue(30);
		infectionCoeff.setPaintTicks(false);
		infectionCoeff.setPaintLabels(false);
		infectionCoeff.setVisible(true);
		parametersPanel.add(infectionCoeff);
		infectionCoeff.setLocation(170,90);
		infectionCoeff.setSize(300,50);
		
		//creating the label showing the mortality of the disease
		mortalityLabel = new JLabel("Mortality : ");
		mortalityLabel.setVisible(true);
		parametersPanel.add(mortalityLabel);
		mortalityLabel.setLocation(20,130);
		mortalityLabel.setSize(150,50);
		
		//creating the slider allowing to set the mortality of the disease
		mortality = new JSlider();	
		mortality.addChangeListener(this);
		mortality.setBackground(Color.white);
		mortality.setMaximum(1000);
		mortality.setMinimum(0);
		mortality.setValue(100);
		mortality.setPaintTicks(false);
		mortality.setPaintLabels(false);
		mortality.setVisible(true);
		parametersPanel.add(mortality);	
		mortality.setLocation(170,130);
		mortality.setSize(300,50);
		
		//creating the label showing the proportion of infected individuals identified
		identifiedProportionLabel = new JLabel("Identification : ");
		identifiedProportionLabel.setVisible(true);
		parametersPanel.add(identifiedProportionLabel);
		identifiedProportionLabel.setLocation(20,170);
		identifiedProportionLabel.setSize(150,50);
		
		//creating the slider allowing to set the proportion of individuals identified
		identifiedProportion = new JSlider();
		identifiedProportion.addChangeListener(this);
		identifiedProportion.setBackground(Color.white);
		identifiedProportion.setMaximum(100);
		identifiedProportion.setMinimum(0);
		identifiedProportion.setValue(20);
		identifiedProportion.setPaintTicks(false);
		identifiedProportion.setPaintLabels(false);
		identifiedProportion.setVisible(true);
		parametersPanel.add(identifiedProportion);
		identifiedProportion.setLocation(170,170);
		identifiedProportion.setSize(300,50);
		
		//creating the label showing the extent of social distancing
		socialDistanceCoeffLabel = new JLabel("Social distancing : ");
		socialDistanceCoeffLabel.setVisible(true);
		parametersPanel.add(socialDistanceCoeffLabel);
		socialDistanceCoeffLabel.setLocation(20,210);
		socialDistanceCoeffLabel.setSize(150,50);
		
		//creating the slider allowing to set the extent of social distancing
		socialDistanceCoeff = new JSlider();
		socialDistanceCoeff.addChangeListener(this);
		socialDistanceCoeff.setBackground(Color.white);
		socialDistanceCoeff.setMaximum(100);
		socialDistanceCoeff.setMinimum(0);
		socialDistanceCoeff.setValue(0);
		socialDistanceCoeff.setPaintTicks(false);
		socialDistanceCoeff.setPaintLabels(false);
		socialDistanceCoeff.setVisible(true);
		parametersPanel.add(socialDistanceCoeff);
		socialDistanceCoeff.setLocation(170,210);
		socialDistanceCoeff.setSize(300,50);
		
		//creating the label showing the probability of going to the central point
		centralPointTripProbaLabel = new JLabel("Central point : ");
		centralPointTripProbaLabel.setVisible(true);
		parametersPanel.add(centralPointTripProbaLabel);
		centralPointTripProbaLabel.setLocation(20,250);
		centralPointTripProbaLabel.setSize(150,50);
		
		//creating the slider allowing to set the probability of going to the central point
		centralPointTripProba = new JSlider();
		centralPointTripProba.addChangeListener(this);
		centralPointTripProba.setBackground(Color.white);
		centralPointTripProba.setMaximum(100);
		centralPointTripProba.setMinimum(0);
		centralPointTripProba.setValue(0);
		centralPointTripProba.setPaintTicks(false);
		centralPointTripProba.setPaintLabels(false);
		centralPointTripProba.setVisible(true);
		parametersPanel.add(centralPointTripProba);
		centralPointTripProba.setLocation(170,250);
		centralPointTripProba.setSize(300,50);
		
		//creating the label showing the amount of travel between groups
		travelBetweenGroupsLabel = new JLabel("Travel : ");
		travelBetweenGroupsLabel.setVisible(true);
		parametersPanel.add(travelBetweenGroupsLabel);
		travelBetweenGroupsLabel.setLocation(20,290);
		travelBetweenGroupsLabel.setSize(150,50);
		
		//creaitng the slider allowing to set the amount of travel between groups
		travelBetweenGroups = new JSlider();
		travelBetweenGroups.addChangeListener(this);
		travelBetweenGroups.setBackground(Color.white);
		travelBetweenGroups.setMaximum(100);
		travelBetweenGroups.setMinimum(0);
		travelBetweenGroups.setValue(20);
		travelBetweenGroups.setPaintTicks(false);
		travelBetweenGroups.setPaintLabels(false);
		travelBetweenGroups.setVisible(true);
		parametersPanel.add(travelBetweenGroups);
		travelBetweenGroups.setLocation(170,290);
		travelBetweenGroups.setSize(300,50);

		
		add(parametersPanel);
		parametersPanel.setLocation(750,0);
		parametersPanel.setSize(750,370);
		parametersPanel.setVisible(true);
		
		setVisible(true);
	}

	
//METHODS :
	
	/**
	 * actionPerformed method: performing the iterations and reacting when a button is pressed
	 */
	public void actionPerformed(ActionEvent e){
		//performing the iterations
		if(e.getSource()==timer) { 
			groups.iterate();		
			repaint();
		}
		//starting a new simulation
		if(e.getSource()==restart) {
			timer.stop(); //stopping this simulation
			this.setVisible(false); //stopping to display this window
			StartWindow window = new StartWindow(); //displaying the start window
			dispose(); //destroying this window
		}
		//starting/pausing the simulation
		if(e.getSource()==pause) {
			if(pause.getText()=="Pause") {
				timer.stop();
				pause.setText("Start");
			}else {
				timer.start();
				pause.setText("Pause");
			}
			
		}
		//starting/stopping quarantine
		if(e.getSource()==quarantine) {
			if(groups.getQuarantining()) {
				groups.stopQuarantine();
				quarantine.setText("Quarantine on");
			}else {
				groups.startQuarantine();
				quarantine.setText("Quarantine off");
			}
		}
		parametersPanel.revalidate();
		parametersPanel.repaint();
		groups.repaint();
		repaint();
	}	
	
	/**
	 * Method reacting when a slider is moved
	 */
	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider) e.getSource();
		//updating the probability of going to the central point
		if(source==centralPointTripProba) {
			groups.getDisease().setCentralPointTripProba((double) centralPointTripProba.getValue()/10000);
			centralPointTripProbaLabel.setText("Central point : "+centralPointTripProba.getValue()/10);		
		}
		//updating the mortality of the disease
		if(source==mortality) {
			groups.getDisease().setMortality((double) mortality.getValue()/1000);
			mortalityLabel.setText("Mortality : "+(double) mortality.getValue()/10+"%");
		}
		//updating the infectiousness of the disease
		if(source==infectionCoeff) {
			groups.getDisease().setInfectionCoeff((double) infectionCoeff.getValue()/(100*groups.getIterationsPerDay()));
			infectionCoeffLabel.setText("Infectiousness : "+infectionCoeff.getValue()+"%");
		}
		//updating the proportion of identified infected individuals
		if(source==identifiedProportion) {
			groups.getDisease().setIdentifiedProportion((double) identifiedProportion.getValue()/(100*groups.getIterationsPerDay()));
			identifiedProportionLabel.setText("Identification : "+identifiedProportion.getValue()+"%");
		}
		//updating the extent of social distancing
		if(source==socialDistanceCoeff) {
			groups.getDisease().setSocialDistanceCoeff(Math.pow(socialDistanceCoeff.getValue()/40,2));
			groups.socialDistancing();
			socialDistanceCoeffLabel.setText("Social distancing : "+socialDistanceCoeff.getValue()/10);			
		}
		//updating the amount of travel between groups
		if(source==travelBetweenGroups) {
			groups.getDisease().setTravelBetweenGroups((double) travelBetweenGroups.getValue()/200000);
			travelBetweenGroupsLabel.setText("Travel : "+ travelBetweenGroups.getValue()/10);
		}
		parametersPanel.revalidate();
		parametersPanel.repaint();
		groups.repaint();
		repaint();
	}
		
	
}


