/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 */
 
import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics ;
import java.awt.* ;
import org.jfree.chart.JFreeChart;

public class ParameterWindow extends JFrame implements ActionListener{
	JButton MainButton;
	JButton ResetButton;
	JButton ResultsButton;
	JButton HelpButton;
	JTextField firstTF;
	JTextField secondTF;
	JTextField thirdTF;
	JTextField fourthTF;
	JTextField fifthTF;
	JTextArea secondTA;
	//ImageIcon icon;
	
	JScrollPane ScrollPane;
	HelpWindow Help;
	
	
	public ParameterWindow(){
		SpringLayout layout = new SpringLayout();
        
        
		this.setTitle("Parameter Window");
		this.setSize(600,600);
		this.setResizable(false);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Help = new HelpWindow();

		
		firstTF = new JTextField();
		JLabel firstLabel = new JLabel();
		firstLabel.setText("R0 :");
	
		
		secondTF = new JTextField();
		JLabel secondLabel = new JLabel();
		secondLabel.setText("Mortality :");
		
		
		thirdTF = new JTextField();
		JLabel thirdLabel = new JLabel();
		thirdLabel.setText("Initial Population :");
		
		
		fourthTF = new JTextField();
		JLabel fourthLabel = new JLabel();
		fourthLabel.setText("Infectious period :");
	
		
		fifthTF = new JTextField();
		JLabel fifthLabel = new JLabel();
		fifthLabel.setText("Simulation duration :");

		//ImageIcon icon = new ImageIcon("/home/haf/Documents/Travail/INSA/73/Info/Java/IHM/CodePourDebuter/insa_logo.png");
		//JLabel logo = new JLabel(icon);
		//logo.setBounds(200,75,136,55);
		
		MainButton = new JButton("Start simulation");
		MainButton.addActionListener(this);
		
		ResetButton = new JButton("Reset");
		ResetButton.addActionListener(this);
		
		ResultsButton = new JButton("Display results");
		ResultsButton.addActionListener(this);
		
		HelpButton = new JButton("Help");
		HelpButton.addActionListener(this);
		
		
		secondTA = new JTextArea();
		secondTA.setLineWrap(true);
		
		ScrollPane = new JScrollPane(secondTA);
		ScrollPane.setBounds(25,45,350,120);
		
		
		
		
		
		
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(layout);
		firstPanel.add(firstLabel);
		firstPanel.add(firstTF);
		firstPanel.add(secondLabel);
		firstPanel.add(secondTF);
		firstPanel.add(thirdLabel);
		firstPanel.add(thirdTF);
		firstPanel.add(fourthLabel);
		firstPanel.add(fourthTF);
		firstPanel.add(fifthLabel);
		firstPanel.add(fifthTF);
		firstPanel.add(MainButton);
		firstPanel.add(ResetButton);
		firstPanel.add(ResultsButton);
		firstPanel.add(HelpButton);
		
		firstPanel.setBounds(0,0,600,600);
		firstPanel.setBackground(Color.gray);
		
		
		//Position of firstTF and firstLabel
		layout.putConstraint(SpringLayout.WEST, firstLabel, 5, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.NORTH,firstLabel, 5, SpringLayout.NORTH, firstPanel);
		
		layout.putConstraint(SpringLayout.WEST, firstTF, 5, SpringLayout.EAST, firstLabel);
		layout.putConstraint(SpringLayout.NORTH,firstTF, 5, SpringLayout.NORTH, firstPanel);
		
		layout.putConstraint(SpringLayout.EAST, firstTF, 50, SpringLayout.WEST, firstTF);
		
		
		//Position of secondTF and secondLabel
		layout.putConstraint(SpringLayout.WEST, secondLabel, 5, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.NORTH,secondLabel, 30, SpringLayout.NORTH, firstPanel);
		
		layout.putConstraint(SpringLayout.WEST, secondTF, 5, SpringLayout.EAST, secondLabel);
		layout.putConstraint(SpringLayout.NORTH, secondTF, 30, SpringLayout.NORTH, firstPanel);
		layout.putConstraint(SpringLayout.EAST, secondTF, 50, SpringLayout.WEST, secondTF);
		
		//Position of 3rd
		layout.putConstraint(SpringLayout.WEST, thirdLabel, 5, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.NORTH,thirdLabel, 60, SpringLayout.NORTH, firstPanel);
		
		layout.putConstraint(SpringLayout.WEST, thirdTF, 5, SpringLayout.EAST, thirdLabel);
		layout.putConstraint(SpringLayout.NORTH, thirdTF, 60, SpringLayout.NORTH, firstPanel);
		layout.putConstraint(SpringLayout.EAST, thirdTF, 50, SpringLayout.WEST, thirdTF);
		
		//Position of 4th
		layout.putConstraint(SpringLayout.WEST, fourthLabel, 5, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.NORTH,fourthLabel, 90, SpringLayout.NORTH, firstPanel);
		
		layout.putConstraint(SpringLayout.WEST, fourthTF, 5, SpringLayout.EAST, fourthLabel);
		layout.putConstraint(SpringLayout.NORTH, fourthTF, 90, SpringLayout.NORTH, firstPanel);
		layout.putConstraint(SpringLayout.EAST, fourthTF, 50, SpringLayout.WEST, fourthTF);
		
		//Position of 5th
		layout.putConstraint(SpringLayout.WEST, fifthLabel, 5, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.NORTH,fifthLabel, 120, SpringLayout.NORTH, firstPanel);
		
		layout.putConstraint(SpringLayout.WEST, fifthTF, 5, SpringLayout.EAST, fifthLabel);
		layout.putConstraint(SpringLayout.NORTH, fifthTF, 120, SpringLayout.NORTH, firstPanel);
		layout.putConstraint(SpringLayout.EAST, fifthTF, 50, SpringLayout.WEST, fifthTF);
		
		//Position of Button 1
		layout.putConstraint(SpringLayout.WEST, MainButton, 50, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.SOUTH, MainButton, -50, SpringLayout.SOUTH, firstPanel);
		
		//Position of Button 2
		layout.putConstraint(SpringLayout.WEST, ResetButton, 250, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.SOUTH, ResetButton, -50, SpringLayout.SOUTH, firstPanel);
		
		//Position of Button 3
		layout.putConstraint(SpringLayout.WEST, ResultsButton, 450, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.SOUTH, ResultsButton, -50, SpringLayout.SOUTH, firstPanel);
		
		//Position of Help Button
		layout.putConstraint(SpringLayout.WEST, HelpButton, 450, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.NORTH, HelpButton, 50, SpringLayout.NORTH, firstPanel);
	
		
		
		//layout.putConstraint(SpringLayout.SOUTH, firstTF, 10, SpringLayout.SOUTH, firstTF);
		//firstPanel.add(logo);
		
		
		//JPanel secondPanel = new JPanel();
		//secondPanel.setLayout(null);
		//secondPanel.add(MainButton);
		//secondPanel.add(ScrollPane);
		//secondPanel.add(ResetButton);
		//secondPanel.setBounds(0,200,400,200);
		//secondPanel.setBackground(Color.yellow);
		
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.add(firstPanel);
		//Main.add(secondPanel);
		Panel.setBounds(0,0,400,400);
		
		this.setContentPane(Panel);
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == MainButton){
			
			
			int nb = Integer.parseInt(firstTF.getText());
			if(nb<0){
				JOptionPane.showMessageDialog(this,"Please enter a positive number.");
			
			}else{
				System.out.println("Button activated" + nb);
				//secondTA.append(myListeCourbe.get(nb-1).toString()+"\n");
				//SecondWindow.addShape(myListeCourbe.get(nb));
				//SecondWindow.setVisible(true);
				//SecondWindow.t.start();
				//SecondWindow.time=0;

			
		}
		}
		if(e.getSource() == ResetButton){
			secondTA.setText("");
			//SecondWindow.erase();
			
		}
		
		if(e.getSource() == HelpButton){
			Help.setVisible(true);
			
		}
		
	}
	
	
	
	
		
		
}
		

		
		

		
		
		
