package disease;


public class TestMain {
	
	public static void main (String[] args) {
		Disease d = new Disease(60, 10, 0.1, 0, 0, 3, 50);
		Group g0 = new Group(50, 10, 10, 200, 200, d);
		Group g1 = new Group(10, 10, 10, 200, 200, d);
		Group[] boxes = new Group[9];
		for(int i=0;i<6;i++){
			g0.getGroup().get(i).initialInfect();
		}
		boxes[0] = g0;
		boxes[1] = g1;
		for(int i = 2; i<9;i++){
			boxes[i] = new Group(10, 10, 10, 200, 200, d);
		}
		SimulationGroup sgro = new SimulationGroup(boxes);    
		SimulationGraph sgra = new SimulationGraph(boxes); 
		Simulation s = new Simulation(sgro,sgra,10);
	}
}
