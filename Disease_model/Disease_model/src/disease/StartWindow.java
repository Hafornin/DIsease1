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
	
	JButton start;
	JSlider infectionTimeDays;
	JLabel infectionTimeDaysLabel;
	JSlider groupSize;
	JLabel groupSizeLabel;
	
	Disease d;
	
	Simulation s;
	
	public StartWindow() {
		
		d = new Disease(0,0);
		
		setTitle("New Simulation");
		setSize(500, 200);
		setLocationRelativeTo(null);              
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null); 
		panel.setBackground(Color.white);
		
		start = new JButton("Start");
		start.addActionListener(this);
		start.setSize(100,50);
		panel.add(start);
		start.setLocation(200,120);
		start.setVisible(true);
		
		infectionTimeDaysLabel = new JLabel("Infection Time (days) : ");
		infectionTimeDaysLabel.setVisible(true);
		panel.add(infectionTimeDaysLabel);
		infectionTimeDaysLabel.setLocation(10,10);
		infectionTimeDaysLabel.setSize(250,50);
		
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
		
		groupSizeLabel = new JLabel("Number of people per group : ");
		groupSizeLabel.setVisible(true);
		panel.add(groupSizeLabel);
		groupSizeLabel.setLocation(10,50);
		groupSizeLabel.setSize(250,50);
		
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
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start) {
			setVisible(false);
			SimulationPanel p = new MultipleGroupsPanel(d);
			s = new Simulation(p);
			dispose();
		}
	}	
	
		
	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider) e.getSource();
		
		if(source==groupSize) {
			d.setGroupSize(groupSize.getValue());
			groupSizeLabel.setText("Number of people per group : "+groupSize.getValue());
		}
		if(source==infectionTimeDays) {
			d.setInfectionTime(infectionTimeDays.getValue());
			infectionTimeDaysLabel.setText("Infection Time (days) : "+infectionTimeDays.getValue());
		}
		repaint();
	}
	
	

}
