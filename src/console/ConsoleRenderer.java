package console;


import java.util.Arrays;
import java.util.Map;
import model.PackageItem;
import nodes.ConveyorLoop;
import nodes.GridPosition;
import nodes.PathStep;

public class ConsoleRenderer {
	private static final int GRID_WIDTH = 12; 
	private static final int GRID_HEIGHT = 3; 

	public void showWelcome() {
		System.out.println("========================================");
		System.out.println(" CONVEYOR RULE SIMULATOR - TERMINAL MODE ");
		System.out.println("========================================");
	}

	public void renderLoop(ConveyorLoop loop) {
		char[][] grid = new char[GRID_HEIGHT][GRID_WIDTH]; 
		for (char[] row : grid) Arrays.fill(row, ' '); 

		for (PathStep step : loop.getPath()) { 
			int x = step.getPosition().getX();
			int y = step.getPosition().getY();
			grid[y][x] = step.getStation() != null ? Character.toUpperCase(step.getStation().getName().charAt(0)) : '.';
		}

		GridPosition start = loop.getPath().get(0).getPosition();
		GridPosition target = loop.getPath().get(loop.getPath().size() - 1).getPosition(); 
		grid[start.getY()][start.getX()] = 'I';
		grid[target.getY()][target.getX()] = 'O';
		
		for (Map.Entry<PackageItem, Integer> entry : loop.getPositions().entrySet()) { 
			PathStep current = loop.getPath().get(entry.getValue());
			grid[current.getPosition().getY()][current.getPosition().getX()] = '#';
		}

		StringBuilder output = new StringBuilder();
		for (char[] row : grid) {
			for (char c : row) output.append(c).append(' ');
			output.append('\n');
		}
		System.out.println(output);
	}
}
