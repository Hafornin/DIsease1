package disease;
import java. awt.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class OneGroupPanel extends SimulationPanel{
	
	//Attributes :
	private Group box;
	
	
	//Constructor :
	public OneGroupPanel(Disease d){
		super(d);
		box = new Group(disease.getGroupSize(), 20, 20, 500, 500, d);
		
		setLayout(null);
		add(box);
		box.setLocation(50, 50);
	}
	
	//Getters :
	
	public DefaultCategoryDataset getData(){
		return dataset;
	}
	
	//Methods :
	
	public void updateDataValues() {
		box.move();
		box.infect();
		box.updateValues();
		numSusceptible = box.getNumSusceptible();
		numInfected = box.getNumInfected();
		numRecovered = box.getNumRecovered();
		numIdentified = box.getNumIdentified();
	}	
	
}

