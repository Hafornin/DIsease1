package disease;
import java. awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Second; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener{
	
	//Attributes :
	private SimulationGroup groups;
	private SimulationGraph graph;
	private int DELTA_T;
	private Timer timer;
	
	
	//Constructor :
	public Simulation(SimulationGroup sgro, SimulationGraph sgra, int dt){
		DELTA_T = dt;
		timer = new Timer(DELTA_T,this);
		groups = sgro;
		graph = sgra;
		
		setTitle("Simulation");
		setSize(1500, 900);
		setLocationRelativeTo(null); 
		setLayout(null);              
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(groups);
		groups.setLocation(30,30);
		
		//add(sgra);
		//graph.setLocation(770,400);
		
		
		JFreeChart lineChart = ChartFactory.createLineChart(
		         "Graph",
		         "Time in days","Number of people",
		         groups.getData(),
		         PlotOrientation.VERTICAL,
		         true,true,false);
		     
		/*XYPlot plot = (XYPlot) lineChart.getPlot();
		AbstractRenderer r1 = (AbstractRenderer) plot.getRenderer(0);
		AbstractRenderer r2 = (AbstractRenderer) plot.getRenderer(1);
		AbstractRenderer r3 = (AbstractRenderer) plot.getRenderer(2);
		AbstractRenderer r4 = (AbstractRenderer) plot.getRenderer(3);
		// change the paint for series 0, 1 and 2...

		r1.setSeriesPaint(0, Color.red);
		r2.setSeriesPaint(1, Color.green);
		r3.setSeriesPaint(2, Color.blue);
		r4.setSeriesPaint(3, Color.yellow);*/
		
		      ChartPanel chartPanel = new ChartPanel( lineChart );
		     /* chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		      setContentPane( chartPanel );
		
		XYDataset dataset = createDataset( );         
	    JFreeChart chart = createChart( dataset );         
	    ChartPanel chartPanel = new ChartPanel( chart );   */      
		chartPanel.setSize(700,450);         
		//chartPanel.setMouseZoomable( true , false );         
	    add( chartPanel );
	    chartPanel.setLocation(770,400);
		
		
		timer.start();
	}

	
	//Methods :
	
	//Method performing the iterations
	public void actionPerformed(ActionEvent e){
		groups.iterate();
		
		/*graph.setNumSusceptible(groups.getNumSusceptible());		
		graph.setNumInfected(groups.getNumInfected());
		graph.setNumRecovered(groups.getNumRecovered());
		graph.setNumIdentified(groups.getNumIdentified());*/
		
		repaint();
	}
		
	
}


