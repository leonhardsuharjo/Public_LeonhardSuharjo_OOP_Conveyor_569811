package rules;

import model.PackageItem;
import sim.SimulationContext;

public class FragileCondition implements Condition{ 
	@Override 
	public boolean matches(PackageItem item, SimulationContext context) {
		return item.isFragile();   
	}
}
