package rules;

import model.PackageItem;
import sim.SimulationContext;

public class WarnAction implements Action {
	private final String message; 

	public WarnAction() {
		this("triggered a rule condition"); 
	}

	public WarnAction(String message) {
		this.message = message;
	}

	@Override
	public void execute(PackageItem item, SimulationContext context) {
		System.out.println("WARNING: package " + item.getId() + " " + message + ".");
	}
}
