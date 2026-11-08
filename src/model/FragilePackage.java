package model;

public class FragilePackage extends PackageItem{
	
	public FragilePackage(String id, int weight) {
		super(id, weight);
	}
	
	@Override
	public String getLabel() {
		return "Fragile - Handle with care";
	}
	
	@Override
	public boolean isFragile() {
		return true; 
	}
	
}
