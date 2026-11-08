package rules;

import model.PackageItem;
import sim.SimulationContext;

public class MinimumWeightCondition implements Condition {
	private final int minimumWeight; 
	
	public MinimumWeightCondition(int minimumWeight) {
		this.minimumWeight = minimumWeight;
	}
	
	@Override 
	public boolean matches(PackageItem item, SimulationContext context) {
		return item.getWeight() >= minimumWeight; 
	}
}
