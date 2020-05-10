package disease;


public class TestMain {
	
	public static void main (String[] args) {
		Disease d = new Disease(60, 200, 10, 0.2, 0.01, 0, 50);
		SimulationPanel nineg = new MultipleGroupsPanel(d);    
		SimulationPanel oneg = new OneGroupPanel(d); 
		Simulation s = new Simulation(oneg,10);
	}
}
