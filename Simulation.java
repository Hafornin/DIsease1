public class Simulation {
	
	public static void main (String[] args) {
		System.out.println("Coucou");
		Group g = new Group(50);
		g.getIndividual(0).setInfectedTime(1);
		g.getIndividual(0).updateState(7);
		for(int i=0;i<20;i++){
			g.iterate();
			System.out.println(g.toString());
		}
		
	}
}

