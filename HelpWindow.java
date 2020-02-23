import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics ;
import java.awt.* ;

public class HelpWindow extends JFrame implements ActionListener{
	

	
	public HelpWindow(){
		super();
		
		SpringLayout layout = new SpringLayout();
		
		this.setSize(800,600);
		this.setLocation(800,200);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setBackground(Color.gray);
		this.setTitle("Help");
		this.setLayout(null);
		
		JPanel BasePanel = new JPanel();
		BasePanel.setLayout(layout);
		BasePanel.setBounds(0,0,800,600);
		this.setContentPane(BasePanel);
		
		Font FONT1 = new Font("Cambria",Font.PLAIN, 11);
		
		JLabel R0 = new JLabel();
		R0.setText("<html>R0 is called the basic reproduction number.<br>It represents the number of healthy individuals an infected one will contaminate on average over the duration of his infection.<html>");
		R0.setFont(FONT1);
		
		JLabel Mort = new JLabel();
		Mort.setText("<html>The mortality of the disease is the ratio of the people the pathogen will eventually kill amongst those it infects.");
		Mort.setFont(FONT1);
		
		BasePanel.add(R0);
		BasePanel.add(Mort);
		
		
		layout.putConstraint(SpringLayout.WEST, R0, 5, SpringLayout.WEST, BasePanel);
		layout.putConstraint(SpringLayout.NORTH,R0, 5, SpringLayout.NORTH, BasePanel);
		
		layout.putConstraint(SpringLayout.WEST, Mort, 5, SpringLayout.WEST, BasePanel);
		layout.putConstraint(SpringLayout.NORTH, Mort, 55, SpringLayout.NORTH, BasePanel);
		
		
		  //private AlphaComposite makeComposite(float alpha) {
			//int type = AlphaComposite.SRC_OVER;
		//	return(AlphaComposite.getInstance(type, alpha));
			
 
                    
	}
	
	
	
		
	
	public void actionPerformed (ActionEvent e) { 
		System.out.println("Coucou");
		
		
			}
			public void paintComponent(Graphics g) {
                super.paintComponent(g);
 
                ImageIcon m = new ImageIcon("/home/haf/Documents/Travail/INSA/73/Info/Java/DIsease1/Blue.jpeg");
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0,this);
 
            }
		}
	
		
	
