package rules;
 
import model.PackageItem; 
import sim.SimulationContext; 


public interface Condition {
	boolean matches(PackageItem item, SimulationContext context); 
}
