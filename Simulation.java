public class Simulation {
	
	public static void main (String[] args) {
		System.out.println("Coucou");
		Disease d = new Disease(0.05,14);
		Group g = new Group(500000,60,d);
		g.group.get(0).infectedTime = 1;
		g.group.get(0).updateState();
		for(int i=0;i<60;i++){
			g.iterate(i);
			System.out.println(g.toString());
		}
		
	}
}

