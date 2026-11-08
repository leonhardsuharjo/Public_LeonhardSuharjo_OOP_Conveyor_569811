package model;

public class NormalPackage extends PackageItem {

	public NormalPackage(String id) {
		this(id, 5); 
	}	
	
	public NormalPackage(String id, int weight) {
		super(id, weight);
	}

	@Override
	public String getLabel() {
		return "Normal";
	}
	
	@Override 
	public boolean isFragile() {
		return false;
	}
	
}
