/*
 * PanelFond.java
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

import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics ;
import java.awt.* ;


public class PanelFond extends JPanel
 {
Image image;
   public PanelFond()
   {
     image=(new ImageIcon("/home/haf/Documents/Travail/INSA/73/Info/Java/DIsease1/Blue.jpeg").getImage());
   }
  
   public void paintComponent(Graphics g)
   {
     g.drawImage (image, 0, 0, null); // elle doit etre avant
     //super.paintComponent(g); // lui il s'occupe de redessiner les composant enfant.
   }
   }

