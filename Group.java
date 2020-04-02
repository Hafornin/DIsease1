import java.util.*;

public class Group {
	
	//Attributes
	public ArrayList<Individual> group;
	public int susceptible;
	public int infected;
	public int recovered;
	public ArrayList<Individual>[] configurationHistory;
	public int[][] numHistory;
	public Disease disease;
	public double xmax;
	public double ymax;
	
	//Constructors
	public Group(int size , int t , Disease d, double x, double y){
		xmax = x;
		ymax = y;
		disease = d;
		group = new ArrayList<Individual>();
		for(int i=0;i<size;i++){
			group.add(new Individual(0,disease));
		}
		configurationHistory = new ArrayList[t];
		numHistory = new int[t][3]; //s,i,r
	}
	
	
	//Methods
	
	public void updateVariables(){
		int s = 0;
		int i = 0;
		int r = 0;
		for(int c=0;c<group.size();c++){
			if(group.get(c).state=='s'){
				s++;
			}
			if(group.get(c).state=='i'){
				i++;
			}
			if(group.get(c).state=='r'){
				r++;
			}
		}
		susceptible=s;
		infected=i;
		recovered=r;
	}
				
	
	public void iterate(int j){
		storeConfiguration(j);
		int met = 0;
		for(int i=0;i<group.size();i++){
			for(int c=0;c<5;c++){
				met = (int) (Math.random()*group.size());
				while(met==i){
					met = (int) (Math.random()*group.size());
				}
				group.get(i).meet(group.get(met));
			}
		}
		for(int i=0;i<group.size();i++){
			group.get(i).updateTime();
			group.get(i).updateState();
		}
		updateVariables();
	}
	
	public void storeConfiguration(int i){
		configurationHistory[i] = group;
		numHistory[i][0] = susceptible;
		numHistory[i][1] = infected;
		numHistory[i][2] = recovered;
		
	}
	
	//toString
	
	public String toString(){
		return "susceptible : " + susceptible + " , infected : " + infected + " , recovered : " + recovered;
	}
	
				
			

	
	
	
	
}

