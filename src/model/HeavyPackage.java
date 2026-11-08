package model;


public class HeavyPackage extends PackageItem {
	
	public HeavyPackage(String id, int weight) {
		super(id, weight); 
	}

	@Override
	public String getLabel() {
		return "Heavy"; 
	}

	@Override
	public boolean isFragile() {
		return false;
	}

}
