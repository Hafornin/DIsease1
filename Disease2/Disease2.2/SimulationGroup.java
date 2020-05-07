import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class SimulationGroup extends JPanel{
	
	//Attributes :
	Group[] boxes;
	
	//Constructor :
	public SimulationGroup(Group[] b){
		boxes = new Group[b.length];
		for(int i=0;i<boxes.length;i++){
			boxes[i] = b[i];
		}
		
		setLayout(new GridLayout(3,3));
		setSize(750,800);         
		setVisible(true);
		
		for(int i=0;i<boxes.length;i++){
			add(boxes[i]);
		}
	}
	
	//Methods :
	
	//Method performing the iterations
	public void iterate(){
		for(int i=0;i<boxes.length;i++){
			boxes[i].move();
			boxes[i].infect();
			boxes[i].updateValues();
		}
		repaint();
	}
		
	
}

