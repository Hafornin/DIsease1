import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics ;

public class HelpWindow extends JFrame implements ActionListener{
	

	
	public HelpWindow(){
		
		SpringLayout layout = new SpringLayout();
		
		this.setSize(600,600);
		this.setLocation(800,200);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.gray);
		this.setTitle("Help");
		this.setLayout(null);
		
		JPanel BasePanel = new JPanel();
		BasePanel.setLayout(layout);
		BasePanel.setBounds(0,0,600,600);
		this.setContentPane(BasePanel);
		
		Font FONT1 = new Font("Cambria",Font.PLAIN, 11);
		
		JLabel R0 = new JLabel();
		R0.setText("<html>R0 is called the basic reproduction number.<br>It represents the number of healthy individuals an infected one <br>will contaminate on average over the duration of his infection.<html>");
		R0.setFont(FONT1);
		
		BasePanel.add(R0);
		
		
		layout.putConstraint(SpringLayout.WEST, R0, 5, SpringLayout.WEST, BasePanel);
		layout.putConstraint(SpringLayout.NORTH,R0, 5, SpringLayout.NORTH, BasePanel);
	}
	
	
	
		
	
	public void actionPerformed (ActionEvent e) { 
		System.out.println("Coucou");
		
		
			}
		}
	
		
	
