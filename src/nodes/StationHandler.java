package nodes;

import model.PackageItem; 

import exceptions.SimulationException;

public interface StationHandler {
	void handlePackage (PackageItem item) throws SimulationException; 
}

