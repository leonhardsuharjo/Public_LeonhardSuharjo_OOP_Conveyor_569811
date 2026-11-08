package rules;

import model.PackageItem;

import sim.SimulationContext;

public interface Action {
	void execute(PackageItem item, SimulationContext context);
}
