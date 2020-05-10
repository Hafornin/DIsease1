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

public class ParameterWindow2 extends JFrame implements ActionListener{
	JButton MainButton1;
	JButton MainButton2;
	JButton ResetButton;
	
	JButton HelpButton;
	
	JTextField secondTF;
	JTextField thirdTF;
	JTextField fourthTF;
	
	JTextArea secondTA;
	//ImageIcon icon;
	
	JScrollPane ScrollPane;
	HelpWindow Help;
	
	
	
	public ParameterWindow2(){
		SpringLayout layout = new SpringLayout();
        
        
		this.setTitle("Parameter Window");
		this.setSize(600,600);
		this.setResizable(false);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Help = new HelpWindow();
		
		
		
	
		
		secondTF = new JTextField();
		JLabel secondLabel = new JLabel();
		secondLabel.setText("Mortality :");
		
		
		thirdTF = new JTextField();
		JLabel thirdLabel = new JLabel();
		thirdLabel.setText("Initial Population per group :");
		
		
		fourthTF = new JTextField();
		JLabel fourthLabel = new JLabel();
		fourthLabel.setText("Infectious period :");
	
		
		

		//ImageIcon icon = new ImageIcon("/home/haf/Documents/Travail/INSA/73/Info/Java/IHM/CodePourDebuter/insa_logo.png");
		//JLabel logo = new JLabel(icon);
		//logo.setBounds(200,75,136,55);
		
		MainButton1 = new JButton("Start simulation with 1 group");
		MainButton1.addActionListener(this);
		
		MainButton2 = new JButton("Start simulation with several groups");
		MainButton2.addActionListener(this);
		
		ResetButton = new JButton("Reset");
		ResetButton.addActionListener(this);
		
		
		
		HelpButton = new JButton("Help");
		HelpButton.addActionListener(this);
		
		
		secondTA = new JTextArea();
		secondTA.setLineWrap(true);
		
		ScrollPane = new JScrollPane(secondTA);
		ScrollPane.setBounds(25,45,350,120);
		
		
		
		
		
		
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(layout);
		
		
		firstPanel.add(secondLabel);
		firstPanel.add(secondTF);
		firstPanel.add(thirdLabel);
		firstPanel.add(thirdTF);
		firstPanel.add(fourthLabel);
		firstPanel.add(fourthTF);
		
	
		firstPanel.add(MainButton1);
		firstPanel.add(MainButton2);
		firstPanel.add(ResetButton);
		
		firstPanel.add(HelpButton);
		
		firstPanel.setBounds(0,0,600,600);
		firstPanel.setBackground(Color.gray);
		
		
		//Position of firstTF and firstLabel
		
		
		
		
		
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
		
		
		
		//Position of MainButton 1
		layout.putConstraint(SpringLayout.WEST, MainButton1, 25, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.SOUTH, MainButton1, -50, SpringLayout.SOUTH, firstPanel);
		
		//Position of MainButton 2
		layout.putConstraint(SpringLayout.WEST, MainButton2, 275, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.SOUTH, MainButton2, -50, SpringLayout.SOUTH, firstPanel);
		
		//Position of Button 2
		layout.putConstraint(SpringLayout.WEST, ResetButton, 250, SpringLayout.WEST, firstPanel);
		layout.putConstraint(SpringLayout.SOUTH, ResetButton, -50, SpringLayout.SOUTH, firstPanel);
		
		
		
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
	
	
	
	
	
	
	public int getPop(){
		int a = Integer.parseInt(thirdTF.getText());
		return a;
	}
	
	public int getInfectiousPeriod(){
		int a = Integer.parseInt(fourthTF.getText());
		return a;
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == MainButton1){
			
			
		

			
		
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
		

		
		

		
		
		
