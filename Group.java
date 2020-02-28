import java.util.*;

public class Group {
	
	//Attributes
	private ArrayList<Individual> group;
	private int susceptible;
	private int infected;
	private int recovered;
	
	//Constructors
	public Group(int size){
		group = new ArrayList<Individual>();
		for(int i=0;i<size;i++){
			group.add(new Individual());
		}
	}
	
	//Getters
	public Individual getIndividual(int i){
		if(0<=i && i<group.size()){
			return group.get(i);
		}else{
			System.out.println("getIndividual : index out of bounds");
			return null;
		}
	}
	
	public int getSusceptible(){
		return susceptible;
	}
	
	public int getInfected(){
		return infected;
	}
	
	public int getRecovered(){
		return recovered;
	}
	
	//Methods
	
	public void updateVariables(){
		int s = 0;
		int i = 0;
		int r = 0;
		for(int c=0;c<group.size();c++){
			if(group.get(c).getState()=='s'){
				s++;
			}
			if(group.get(c).getState()=='i'){
				i++;
			}
			if(group.get(c).getState()=='r'){
				r++;
			}
		}
		susceptible=s;
		infected=i;
		recovered=r;
	}
				
	
	public void iterate(){
		int met = 0;
		for(int i=0;i<group.size();i++){
			for(int c=0;c<2;c++){
				/*while(met==i){
					met = (int) Math.random()*group.size();
				}*/
				met = (int) Math.random()*group.size();
				group.get(i).meet(0.3,group.get(met));
			}
		}
		for(int i=0;i<group.size();i++){
			group.get(i).updateTime();
			group.get(i).updateState(7);
		}
		updateVariables();
	}
	
	
	
	//toString
	
	public String toString(){
		return "susceptible : " + susceptible + " , infected : " + infected + " , recovered : " + recovered;
	}
	
				
			

	
	
	
	
}

