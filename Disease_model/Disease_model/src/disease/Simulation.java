package disease;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;

import java.awt.Color;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener, ChangeListener{
	
	//Attributes :
	private SimulationPanel groups;
	private int DELTA_T = 10;
	private Timer timer;
	private JPanel parametersPanel;
	private JButton restart;
	private JButton pause;
	private JButton quarantine;
	
	private JSlider mortality;
	private JLabel mortalityLabel;
	
	private JSlider infectionCoeff;
	private JLabel infectionCoeffLabel;
	
	private JSlider identifiedProportion;
	private JLabel identifiedProportionLabel;
	
	private JSlider socialDistanceCoeff;
	private JLabel socialDistanceCoeffLabel;
	
	private JSlider centralPointTripProba;
	private JLabel centralPointTripProbaLabel;
	
	private JSlider travelBetweenGroups;
	private JLabel travelBetweenGroupsLabel;

	
	//Constructor :
	public Simulation(SimulationPanel s){
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
		
		infectionCoeffLabel = new JLabel("Infectivity : ");
		infectionCoeffLabel.setVisible(true);
		parametersPanel.add(infectionCoeffLabel);
		infectionCoeffLabel.setLocation(20,90);
		infectionCoeffLabel.setSize(150,50);
		
		infectionCoeff = new JSlider();
		infectionCoeff.addChangeListener(this);
		infectionCoeff.setBackground(Color.white);
		infectionCoeff.setMaximum(100);
		infectionCoeff.setMinimum(0);
		infectionCoeff.setValue(10);
		infectionCoeff.setPaintTicks(false);
		infectionCoeff.setPaintLabels(false);
		infectionCoeff.setVisible(true);
		parametersPanel.add(infectionCoeff);
		infectionCoeff.setLocation(170,90);
		infectionCoeff.setSize(300,50);
		
		mortalityLabel = new JLabel("Mortality : ");
		mortalityLabel.setVisible(true);
		parametersPanel.add(mortalityLabel);
		mortalityLabel.setLocation(20,130);
		mortalityLabel.setSize(150,50);
		
		mortality = new JSlider();	
		mortality.addChangeListener(this);
		mortality.setBackground(Color.white);
		mortality.setMaximum(100);
		mortality.setMinimum(0);
		mortality.setValue(2);
		mortality.setPaintTicks(false);
		mortality.setPaintLabels(false);
		mortality.setVisible(true);
		parametersPanel.add(mortality);	
		mortality.setLocation(170,130);
		mortality.setSize(300,50);
		
		identifiedProportionLabel = new JLabel("Identification : ");
		identifiedProportionLabel.setVisible(true);
		parametersPanel.add(identifiedProportionLabel);
		identifiedProportionLabel.setLocation(20,170);
		identifiedProportionLabel.setSize(150,50);
		
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
		
		socialDistanceCoeffLabel = new JLabel("Social distancing : ");
		socialDistanceCoeffLabel.setVisible(true);
		parametersPanel.add(socialDistanceCoeffLabel);
		socialDistanceCoeffLabel.setLocation(20,210);
		socialDistanceCoeffLabel.setSize(150,50);
		
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
		
		centralPointTripProbaLabel = new JLabel("Central point : ");
		centralPointTripProbaLabel.setVisible(true);
		parametersPanel.add(centralPointTripProbaLabel);
		centralPointTripProbaLabel.setLocation(20,250);
		centralPointTripProbaLabel.setSize(150,50);
		
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
		
		travelBetweenGroupsLabel = new JLabel("Travel : ");
		travelBetweenGroupsLabel.setVisible(true);
		parametersPanel.add(travelBetweenGroupsLabel);
		travelBetweenGroupsLabel.setLocation(20,290);
		travelBetweenGroupsLabel.setSize(150,50);
		
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

	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==timer) {
			groups.iterate();		
			repaint();
		}
		if(e.getSource()==restart) {
			timer.stop();
			this.setVisible(false);
			StartWindow window = new StartWindow();
			dispose();
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
	
		
	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider) e.getSource();
		
		if(source==centralPointTripProba) {
			groups.getDisease().setCentralPointTripProba((double) centralPointTripProba.getValue()/10000);
			centralPointTripProbaLabel.setText("Central point : "+centralPointTripProba.getValue()/10);		
		}
		if(source==mortality) {
			groups.getDisease().setMortality((double) mortality.getValue()/1000);
			mortalityLabel.setText("Mortality : "+(double) mortality.getValue()/10+"%");
		}
		if(source==infectionCoeff) {
			groups.getDisease().setInfectionCoeff((double) infectionCoeff.getValue()/(100*groups.getIterationsPerDay()));
			infectionCoeffLabel.setText("Infectivity : "+infectionCoeff.getValue()+"%");
		}
		if(source==identifiedProportion) {
			groups.getDisease().setIdentifiedProportion((double) identifiedProportion.getValue()/(100*groups.getIterationsPerDay()));
			identifiedProportionLabel.setText("Identification : "+identifiedProportion.getValue()+"%");
		}
		if(source==socialDistanceCoeff) {
			groups.getDisease().setSocialDistanceCoeff(Math.pow(socialDistanceCoeff.getValue()/40,2));
			groups.socialDistancing();
			socialDistanceCoeffLabel.setText("Social distancing : "+socialDistanceCoeff.getValue()/10);			
		}
		if(source==travelBetweenGroups) {
			groups.getDisease().setTravelBetweenGroups((double) travelBetweenGroups.getValue()/200000);
			System.out.println(groups.getDisease().getTravelBetweenGroups());
			travelBetweenGroupsLabel.setText("Travel : "+ travelBetweenGroups.getValue()/10);
		}
		parametersPanel.revalidate();
		parametersPanel.repaint();
		groups.repaint();
		repaint();
	}
		
	
}


