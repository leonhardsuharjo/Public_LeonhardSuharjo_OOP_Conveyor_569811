package nodes;

import exceptions.SimulationException;


public abstract class ConveyorNode { 
	private final String name; 
	private boolean active; 
	
	protected ConveyorNode (String name) {
		if (name == null || name.isBlank()) { 
			throw new IllegalArgumentException("Node name cant be blank");  
		}
		this.name = name;
		this.active = true; 
	}
	
	public String getName() {
		return name; 
	}
	
	public boolean isActive() { 
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract void tick() throws SimulationException;  
	

	
}
