package nodes;

import model.PackageItem; 
import exceptions.OverloadException;
import exceptions.SimulationException;

public class WeightStation extends ConveyorNode implements StationHandler {
	private final double maxSafeLoad;  
	
	public WeightStation(String name, double maxSafeLoad) {
		super(name);
		this.maxSafeLoad = maxSafeLoad; 
	}

	public double calculateLoadRatio(int packageWeight) {
		return packageWeight / maxSafeLoad;
	}
	
	@Override 
	public void handlePackage(PackageItem item) throws OverloadException {
		double ratio = calculateLoadRatio(item.getWeight()); 
		System.out.printf("[%s] %s weighs %d (load ratio %.2f)%n", getName(), item.getId(), item.getWeight(), ratio);  
		if (ratio > 1.0) {
			throw new OverloadException(item.getId() + " exceed safe load at " + getName());  
		}
	}
	
	@Override 
	public void tick() throws SimulationException {
	}
}
