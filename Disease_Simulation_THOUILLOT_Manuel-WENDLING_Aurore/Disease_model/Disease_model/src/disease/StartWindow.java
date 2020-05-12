/**
 * This class is the window displayed at the start of the program.
 * This window allows to set the number of people in each group, the duration of infection and to start the simulation.
 */

package disease;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.*;

public class StartWindow extends JFrame implements ActionListener, ChangeListener {
	
//ATTRIBUTES :
	
//=================== Simulation related attributes ========================

	/**
	 * the disease that will propagate in the simulation created
	 */
	Disease d;
	/**
	 * the simulation created
	 */
	Simulation s;
	
	
//=================== User interaction attributes ========================

	/**
	 * the button allowing to start the simulation
	 */
	JButton start;
	/**
	 * the slider allowing to set the infection time
	 */
	JSlider infectionTimeDays;
	/**
	 * the label showing the infection time
	 */
	JLabel infectionTimeDaysLabel;
	/**
	 * the slider allowing to set the size of the groups
	 */
	JSlider groupSize;
	/**
	 * the label showing the size of the groups
	 */
	JLabel groupSizeLabel;
	
	
//CONSTRUCTOR :
	/**
	 * Creates an instance of StartWindow
	 */
	public StartWindow() {
		
		d = new Disease(0,0);
		
		//setting the main display parameters
		setTitle("New Simulation");
		setSize(500, 200);
		setLocationRelativeTo(null);              
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null); 
		panel.setBackground(Color.white);
		
		//adding the start button
		start = new JButton("Start");
		start.addActionListener(this);
		start.setSize(100,50);
		panel.add(start);
		start.setLocation(200,120);
		start.setVisible(true);
		
		//adding the label showing the infection time
		infectionTimeDaysLabel = new JLabel("Infection Time (days) : ");
		infectionTimeDaysLabel.setVisible(true);
		panel.add(infectionTimeDaysLabel);
		infectionTimeDaysLabel.setLocation(10,10);
		infectionTimeDaysLabel.setSize(250,50);
		
		//adding the slider allowing to set the infection time
		infectionTimeDays = new JSlider();
		infectionTimeDays = new JSlider();
		infectionTimeDays.addChangeListener(this);
		infectionTimeDays.setBackground(Color.white);
		infectionTimeDays.setMaximum(30);
		infectionTimeDays.setMinimum(1);
		infectionTimeDays.setValue(15);
		infectionTimeDays.setPaintTicks(false);
		infectionTimeDays.setPaintLabels(false);
		infectionTimeDays.setVisible(true);
		panel.add(infectionTimeDays);
		infectionTimeDays.setLocation(250,10);
		infectionTimeDays.setSize(200,50);
		
		//adding the label showing the size of the groups
		groupSizeLabel = new JLabel("Number of people per group : ");
		groupSizeLabel.setVisible(true);
		panel.add(groupSizeLabel);
		groupSizeLabel.setLocation(10,50);
		groupSizeLabel.setSize(250,50);
		
		//adding the slider allowing to set the size of the groups
		groupSize = new JSlider();
		groupSize.addChangeListener(this);
		groupSize.setBackground(Color.white);
		groupSize.setMaximum(100);
		groupSize.setMinimum(1);
		groupSize.setValue(60);
		groupSize.setPaintTicks(false);
		groupSize.setPaintLabels(false);
		groupSize.setVisible(true);
		panel.add(groupSize);
		groupSize.setLocation(250,50);
		groupSize.setSize(200,50);
		
		add(panel);
		panel.setVisible(true);
		setVisible(true);
		
	}
	
//METHODS :
	
	/**
	 * actionPerformed method : method reacting when the start button is pressed
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start) {
			setVisible(false); //hiding this window
			SimulationPanel p = new MultipleGroupsPanel(d); //creating a SimulationPanel
			s = new Simulation(p); //creating a Simulation window
			dispose(); //destroying this window
		}
	}	
	
	/**
	 * Method reacting when a slider is moved
	 */
	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider) e.getSource();
		//updating the size of the groups
		if(source==groupSize) {
			d.setGroupSize(groupSize.getValue());
			groupSizeLabel.setText("Number of people per group : "+groupSize.getValue());
		}
		//updating the infection time
		if(source==infectionTimeDays) {
			d.setInfectionTime(infectionTimeDays.getValue());
			infectionTimeDaysLabel.setText("Infection Time (days) : "+infectionTimeDays.getValue());
		}
		repaint();
	}
	
	

}
