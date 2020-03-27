public class Individual {
	
	//Attributes
	public char state; //in susceptible (s) , infected (i), just infected (j) recovered (r)
	public int infectedTime; //number of days from infection (0 = not infected)
	public Disease disease;
	
	//Constructors
	public Individual(){
		state = 's';
		infectedTime = 0;
	}
	
	public Individual(int t,Disease d){
		disease = d;
		infectedTime = t;
		updateState();
	}
	
	//Methods
	
	public void updateState(){
		if(infectedTime==0){
			state = 's';
		}else if(infectedTime <= disease.infectionTime){
			state = 'i';
		}else{
			state = 'r';
		}
	}
	
	public void updateTime(){
		if(state=='j'){
			infectedTime = 1;
		}
		if(state=='i' || state=='r'){
			infectedTime++;
		}
	}
		
	
	public void meet(Individual met){
		double r = Math.random();
		if(r<=disease.r0 && this.state=='i' && met.state=='s'){
			met.state='j';
		}
		if(r<=disease.r0 && this.state=='s' && met.state=='i'){
			this.state='j';
		}
	}
	
	
}

