public class Disease {
	
	private float R0;
	//private float Latent_time;
	private float mortality;
	//private float Asymptomatic_time;
	private float Symptomatic_time;
	
	public Disease (float Rinit, float mortinit, float symptinit){
		this.R0=Rinit;
		this.mortality=mortinit;
		this.Symptomatic_time=symptinit;
	}
	
}

