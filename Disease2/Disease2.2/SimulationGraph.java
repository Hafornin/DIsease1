import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.event.*;

public class SimulationGraph extends JFrame implements ActionListener{
	
	//Attributes :
	Group[] boxes;
	
	//Constructor :
	public SimulationGraph(Group[] b){
		
		
		setSize(500,400);
		setLocation(1000,400);
		
	}
	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		
		repaint();
	}
		
	
}

