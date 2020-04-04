/*
 * TestMain.java
 * 
 * Copyright 2020 Aurore Wendling <Aurore@desktop-a4bfq5c.home>
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


public class TestMain {
	
	public static void main (String[] args) {
		Disease d = new Disease(10, 10, 0.2, 0, 0, 10, 0);
		Group g = new Group(100, 10, 10, 200, 200, d);
		Group[] boxes = new Group[1];
		boxes[0] = g;
		Simulation s = new Simulation(boxes,100);
	
		
	}
}

