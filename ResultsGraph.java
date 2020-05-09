import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics ;
import java.awt.* ;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class ResultsGraph extends JFrame {
	
	/*public LineChartEx() {

        initUI();
    }*/
	
	public ResultsGraph(){
		super();
		
		SpringLayout layout = new SpringLayout();
		
		this.setSize(800,600);
		this.setLocation(800,200);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setBackground(Color.gray);
		this.setTitle("Results");
		this.setLayout(null);
		
		//JPanel panFondEcran = new JPanel();
		//panFondEcran.add(new TestImagePanel(new ImageIcon("/home/haf/Documents/Travail/INSA/73/Info/Java/DIsease1/Blue.jpeg").getImage()));
		
		JPanel BasePanel = new JPanel();
		BasePanel.setLayout(layout);
		BasePanel.setBounds(0,0,800,600);
		this.setContentPane(BasePanel);
	
		
		Font FONT1 = new Font("Cambria",Font.PLAIN, 11);
		
		
		
		/*BasePanel.add(R0);
		BasePanel.add(Mort);
		
		
		layout.putConstraint(SpringLayout.WEST, R0, 5, SpringLayout.WEST, BasePanel);
		layout.putConstraint(SpringLayout.NORTH,R0, 5, SpringLayout.NORTH, BasePanel);
		
		layout.putConstraint(SpringLayout.WEST, Mort, 5, SpringLayout.WEST, BasePanel);
		layout.putConstraint(SpringLayout.NORTH, Mort, 55, SpringLayout.NORTH, BasePanel); */
		
		
		
		  //private AlphaComposite makeComposite(float alpha) {
			//int type = AlphaComposite.SRC_OVER;
		//	return(AlphaComposite.getInstance(type, alpha));
			
 
                    
	}
	
	
	
	
		
	
	public void actionPerformed (ActionEvent e) { 
		System.out.println("Coucou");
		
		
			}
		//	public void paintComponent(Graphics g) {
         //       super.paintComponent(g);
 
           //     ImageIcon m = new ImageIcon("/home/haf/Documents/Travail/INSA/73/Info/Java/DIsease1/Blue.jpeg");
             //   Image monImage = m.getImage();
               // g.drawImage(monImage, 0, 0,this);
 
//            }
		}
	
