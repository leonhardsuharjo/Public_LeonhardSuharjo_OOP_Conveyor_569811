package nodes;


import java.util.ArrayList;
import java.util.List;

public class LoopLayoutFactory {

	public static List<PathStep> buildDefaultLoop(WeightStation weightStation, SensorNode sensor) {
		List<PathStep> path = new ArrayList<>();

		addPlain(path, 0, 2); 
		addPlain(path, 1, 2);
		addPlain(path, 2, 2);
		addPlain(path, 3, 2);
		addPlain(path, 4, 2); 

		path.add(new PathStep(new GridPosition(4, 1), weightStation)); 
		addPlain(path, 4, 0);
		addPlain(path, 5, 0);
		addPlain(path, 6, 0);
		addPlain(path, 6, 1);
		addPlain(path, 6, 2); 
		addPlain(path, 7, 2);
		addPlain(path, 8, 2); 

		path.add(new PathStep(new GridPosition(8, 1), sensor)); 
		addPlain(path, 8, 0); 
		addPlain(path, 9, 0);
		addPlain(path, 10, 0);
		addPlain(path, 11, 0); 

		return path;
	}

	private static void addPlain(List<PathStep> path, int x, int y) {
		path.add(new PathStep(new GridPosition(x, y), null));
	}
}
