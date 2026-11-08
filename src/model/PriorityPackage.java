package model;

public class PriorityPackage extends PackageItem implements Trackable, Processable {  
	private final String trackingCode ; 
	private boolean processed; 
	
	public PriorityPackage(String id, int weight, String trackingCode) {
		super(id, weight); 
		this.trackingCode = trackingCode; 
		this.processed = false;
	}
	
	@Override
	public void markProcessed() {
		processed = true; 
	}

	@Override
	public boolean isProcessed() {
		return processed;
	}

	@Override
	public String getTrackingCode() {
		return trackingCode ;
	}

	@Override
	public String getTrackingInfo() {
		return "Package " + getId() + ", tracking code:" + trackingCode;
	}

	@Override
	public String getLabel() {
		return "Priority";
	}

	@Override
	public boolean isFragile() {
		return false;
	}

}

