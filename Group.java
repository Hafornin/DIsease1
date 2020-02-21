public class Group {
	
	//Attributes
	private Individual[] group;
	
	//Constructors
	public Group(int size){
		group = new Individual[size];
		for(int i=0;i<size;i++){
			group[i] = new Individual();
		}
	}
	
	//Getters
	public Individual getIndividual(int i){
		if(0<=i && i<group.length){
			return group[i];
		}else{
			System.out.println("getIndividual : index out of bounds");
			return null;
		}
	}
	
	//Methods

	
	
	
	
}

