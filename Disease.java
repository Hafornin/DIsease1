/*
 * Disease.java
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


public class Disease {
	
	private float R0;
	//private float Latent_time;
	private float mortality;
	//private float Asymptomatic_time;
	private float Symptomatic_time;
	
	public Disease (float Rinit, float mortinit, float symptinit){
		this.R0=Rinit;
		this.mortality=mortinit;
		this.Symptomatic_time=symptinit;
	}
	
}

