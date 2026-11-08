package nodes;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.PackageItem;
import model.PackageStatus;
import exceptions.OverloadException;
import exceptions.SimulationException;

public class ConveyorLoop {
	private final List<PathStep> path;
	private final Map<PackageItem, Integer> positions = new LinkedHashMap<>(); 
	private final List<PackageItem> rejectedThisTick = new ArrayList<>();
	private final List<PackageItem> deliveredThisTick = new ArrayList<>();
	private int sensorFailuresThisTick;

	public ConveyorLoop(List<PathStep> path) {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("Path cant be empty");
		}
		this.path = path;
	}

	public boolean isStartFree() {
		return !positions.containsValue(0);
	}

	public void spawnPackage(PackageItem item) {
		item.setStatus(PackageStatus.IN_TRANSIT);
		positions.put(item, 0);
	}

	public List<PathStep> getPath() { return Collections.unmodifiableList(path); }
	public Map<PackageItem, Integer> getPositions() { return Collections.unmodifiableMap(positions); }
	public List<PackageItem> getRejectedThisTick() { return Collections.unmodifiableList(rejectedThisTick); }
	public List<PackageItem> getDeliveredThisTick() { return Collections.unmodifiableList(deliveredThisTick); }
	public int getSensorFailuresThisTick() { return sensorFailuresThisTick; }

	public List<PackageItem> tick() {
		rejectedThisTick.clear();
		deliveredThisTick.clear();
		sensorFailuresThisTick = 0;
		List<PackageItem> arrivedAtStation = new ArrayList<>();

		Iterator<Map.Entry<PackageItem, Integer>> iterator = positions.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<PackageItem, Integer> entry = iterator.next();
			PackageItem item = entry.getKey();
			
			int nextIndex = entry.getValue() + 1; 
			if (nextIndex >= path.size() - 1) { 
				item.setStatus(PackageStatus.DELIVERED);
				deliveredThisTick.add(item);
				iterator.remove(); 
				continue; 
			}
			entry.setValue(nextIndex);
			ConveyorNode station = path.get(nextIndex).getStation();
			if (station instanceof StationHandler handler) {
				try {
					handler.handlePackage(item);
					arrivedAtStation.add(item);
				} catch (OverloadException exception) {
					item.setStatus(PackageStatus.REJECTED);
					rejectedThisTick.add(item);
					iterator.remove();
					System.out.println("Overload at " + station.getName() + ": " + exception.getMessage() + " -> " + item.getId() + " removed from belt");
				} catch (SimulationException exception) {
					sensorFailuresThisTick++;
					System.out.println("Recoverable failure at " + station.getName() + " on " + item.getId() + ": " + exception.getMessage());
				}
			}
		}
		return arrivedAtStation;
	}

	public void removeRejected() {
		positions.keySet().removeIf(item -> item.getStatus() == PackageStatus.REJECTED);
	}
}
