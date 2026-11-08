package model;


public abstract class PackageItem {
	private final String id; 
	private final int weight; 
	private PackageStatus status; 

	protected PackageItem(String id, int weight) {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("ID cannot be blank");
		}
		if (weight <= 0) {
			throw new IllegalArgumentException("Weight must be positive");
		}
		this.id = id;
		this.weight = weight;
		this.status = PackageStatus.WAITING; 
	}

	public String getId() { return id; }
	public int getWeight() { return weight; }
	public PackageStatus getStatus() { return status; }
	public void setStatus(PackageStatus status) { this.status = status; }

	public abstract String getLabel(); 
	public abstract boolean isFragile(); 
}
