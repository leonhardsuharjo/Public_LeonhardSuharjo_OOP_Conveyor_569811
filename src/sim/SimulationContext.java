package sim;
 
public class SimulationContext {
	private int currentTick ; 
	
	private final Statistics statistics = new Statistics(); 
	
	public int getCurrentTick() {
		return currentTick;
	}
	
	public void advanceTick() {
		currentTick++; 
	}
	
	public Statistics getStatistics(){
		return statistics;
	}
	
}

