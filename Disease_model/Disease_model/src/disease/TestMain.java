package disease;


public class TestMain {
	
	public static void main (String[] args) {
		Disease d = new Disease(60, 100, 10, 0.1, 0, 0, 50);
		SimulationPanel nineg = new MultipleGroupsPanel(d,8);    
		SimulationPanel oneg = new OneGroupPanel(d); 
		Simulation s = new Simulation(nineg,10);
	}
}
