package nodes;


public class PathStep {
	private final GridPosition position; 
	private final ConveyorNode station; 
	
	public PathStep(GridPosition position, ConveyorNode station) {
		this.position = position; 
		this.station = station; 
	}
	
	public GridPosition getPosition() {return position;}
	public ConveyorNode getStation() {return station;}
}
