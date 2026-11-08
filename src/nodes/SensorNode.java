package nodes;

import exceptions.SensorFailureException;
import exceptions.SimulationException;
import model.PackageItem;  

public class SensorNode extends ConveyorNode implements StationHandler {
	private int detectionCount;  
	private final double failureChance; 
	
	public SensorNode(String name, double failureChance) {
		super(name);
		this.failureChance = failureChance; 
	}
	
	@Override
	public void handlePackage(PackageItem item) throws SensorFailureException {
		if (Math.random() < failureChance) { 
			throw new SensorFailureException("Sensor "+ getName() + " failed to detect " + item.getId()); 
		}
		detectionCount++; 
		System.out.println("[" + getName() + "] detected " + item.getId()); 
	}
			
	@Override 
	public void tick() throws SimulationException { 
	}
	
	public int getDetectionCount() { 
		return detectionCount; 
	}
}
