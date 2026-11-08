package rules;

import model.PackageItem;
import model.PackageStatus;
import sim.SimulationContext;

public class RejectAction implements Action {
	@Override
	public void execute(PackageItem item, SimulationContext context) {
		item.setStatus(PackageStatus.REJECTED); 
		context.getStatistics().recordRejected(); 
		System.out.println("Package " + item.getId() + " rejected.");
	}
}
