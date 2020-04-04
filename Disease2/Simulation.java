import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener{
	
	//Attributes :
	Group[] boxes;
	int DELTA_T;
	Timer timer;
	
	//Constructor :
	public Simulation(Group[] b, int dt){
		DELTA_T = dt;
		timer = new Timer(DELTA_T,this);
		boxes = new Group[b.length];
		for(int i=0;i<boxes.length;i++){
			boxes[i] = b[i];
		}
		
		setTitle("Simulation");
		setSize(400, 400);
		setLocationRelativeTo(null);               
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		for(int i=0;i<boxes.length;i++){
			add(boxes[i]);
		}
		timer.start();
	}
	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		for(int i=0;i<boxes.length;i++){
			boxes[i].move();
			boxes[i].infect();
		}
		repaint();
	}
		
	
}

