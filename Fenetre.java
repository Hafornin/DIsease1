/*
 * Fenetre.java
 * 
 * Copyright 2020 Manuel <haf@Mycroft>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics ;
  
	
	
public class Fenetre extends JFrame {
	JButton Stop;
	JButton Quarantine;
	JButton CentralPoint;
	JSlider Mortality;
	JSlider InfectionCoeff;
	JSlider IndentifiedProportion;
	JSlider SocialDistance;
  public Fenetre(){             
    this.setTitle("Simulation");
    this.setSize(1500, 900);
    this.setLocationRelativeTo(null);
    this.setVisible(true);               
 
    //Instanciation d'un objet JPanel
   
    
		JPanel parametersPanel = new JPanel();
		parametersPanel.setBackground(Color.green);
		add(parametersPanel);
		parametersPanel.setVisible(true);
		parametersPanel.setLocation(750,0);
		parametersPanel.setSize(750,400);
		
		JButton Stop= new JButton("Stop simulation");
		JButton Quarantine= new JButton("Quarantine on/off");
		JButton CentralPoint= new JButton("Activate central point in groups");
		/*Stop.addActionListener(this);
		Quarantine.addActionListener(this);
		CentralPoint.addActionListener(this);*/
		parametersPanel.add(Stop);
		parametersPanel.add(Quarantine);
		parametersPanel.add(CentralPoint);
		
		JSlider Mortality = new JSlider();
		JSlider InfectionCoeff = new JSlider();
		JSlider IdentifiedProportion = new JSlider();
		JSlider SocialDistance = new JSlider();
		
		Mortality.setMaximum(100);
		Mortality.setMinimum(0);
		Mortality.setValue(30);
		Mortality.setMinorTickSpacing(1);
		Mortality.setMajorTickSpacing(10);
		//Mortality.setPosition(200,5);
		
		InfectionCoeff.setMaximum(100);
		InfectionCoeff.setMinimum(0);
		InfectionCoeff.setValue(30);
		
		InfectionCoeff.setMinorTickSpacing(1);
		InfectionCoeff.setMajorTickSpacing(10);
		//InfectionCoeff.setPosition(200,55);
		
		IdentifiedProportion.setMaximum(100);
		IdentifiedProportion.setMinimum(0);
		IdentifiedProportion.setValue(30);
		IdentifiedProportion.setMinorTickSpacing(1);
		IdentifiedProportion.setMajorTickSpacing(10);
		//IdentifiedProportion.setPosition(200,105);
		
		SocialDistance.setMaximum(100);
		SocialDistance.setMinimum(0);
		SocialDistance.setValue(30);
		SocialDistance.setMinorTickSpacing(1);
		SocialDistance.setMajorTickSpacing(10);
		//SocialDistance.setPosition(200,155);
	
		
		//Stop.setBounds(5,5,10,30);
  }       
}

