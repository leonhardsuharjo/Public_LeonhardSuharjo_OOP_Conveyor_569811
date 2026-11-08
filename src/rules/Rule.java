package rules;


import model.PackageItem; 

import sim.SimulationContext;

import java.util.ArrayList; 
import java.util.List;

 

public class Rule {
	
	private final List<Condition> conditions; 
	private final List<Action> actions; 
	
	public Rule() {
		conditions = new ArrayList<>(); 
		actions = new ArrayList<>(); 
	}
	
	public void addCondition(Condition condition) { 
		conditions.add(condition);
	}
	
	public void addAction(Action action) { 
		actions.add(action);
	}
	
	public void apply (PackageItem item, SimulationContext context) {
		for (Condition condition : conditions) {
			if (!condition.matches(item, context)) {  
				return; 
			}
		}
		for (Action action : actions) { 
			action.execute(item, context); 
		}
	}
}

