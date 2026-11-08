package app;

import console.ConsoleInput;
import console.ConsoleRenderer;
import exceptions.OverloadException;
import model.FragilePackage;
import model.HeavyPackage;
import model.NormalPackage;
import model.PackageItem;
import model.PriorityPackage;
import model.Processable;
import model.Trackable;
import nodes.ConveyorLoop;
import nodes.LoopLayoutFactory;
import nodes.SensorNode;
import nodes.WeightStation;
import rules.RuleBuilder;
import sim.Simulation;

public class Main {
	private static final int QUEUE_CAPACITY = 10;
	private static final double DEFAULT_MAX_LOAD = 30.0;

	public static void main(String[] args) {
		ConsoleRenderer renderer = new ConsoleRenderer();
		renderer.showWelcome();

		ConsoleInput input = new ConsoleInput();

		Double typedLoad = input.askOptionalDouble("Weight station safe limit (1-100, Enter = " + DEFAULT_MAX_LOAD + ") > ", 1, 100);
		double maxLoad = (typedLoad == null) ? DEFAULT_MAX_LOAD : typedLoad;
		double failureChance = input.askDouble("Sensor failure chance (0 to 1, e.g. 0.1) > ", 0, 1);
		int packageCount = input.askInt("How many packages? (1-15, more than " + QUEUE_CAPACITY + " overflows the queue) > ", 1, 15);

		WeightStation weightStation = new WeightStation("Weight", maxLoad);
		SensorNode sensor = new SensorNode("Sensor", failureChance);
		ConveyorLoop loop = new ConveyorLoop(LoopLayoutFactory.buildDefaultLoop(weightStation, sensor));

		Simulation simulation = new Simulation(QUEUE_CAPACITY);
		simulation.addNode(weightStation);
		simulation.addNode(sensor);
		simulation.setLoop(loop);

		for (int number = 1; number <= packageCount; number++) {
			String type = input.askOption("Package " + number + " type: N)ormal F)ragile H)eavy P)riority > ", "NFHP");
			Integer weight = input.askOptionalInt("Package " + number + " weight (1-100, Enter = default: N=5, F=8, H=20, P=5) > ", 1, 100);
			PackageItem item = createPackage(type, number, weight);
			try {
				simulation.enqueue(item);
			} catch (OverloadException exception) {
				System.out.println("Recovered: " + exception.getMessage() + " " + item.getId() + " was not added.");
			}
		}

		RuleBuilder builder = new RuleBuilder(input);
		do { 
			simulation.addRule(builder.buildRuleFromUser());
		} while (input.askOption("Add another rule? Y)es N)o > ", "YN").equals("Y"));

		demonstrateMultityping();

		int ticks = loop.getPath().size() + packageCount; 
		simulation.runTicks(ticks, renderer);
	}

	private static PackageItem createPackage(String type, int number, Integer weight) {
		String id = type + "-" + number;
		switch (type) {
			case "N":
				return (weight == null) ? new NormalPackage(id) : new NormalPackage(id, weight);
			case "F":
				return new FragilePackage(id, (weight == null) ? 8 : weight);
			case "H":
				return new HeavyPackage(id, (weight == null) ? 20 : weight);
			default:
				return new PriorityPackage(id, (weight == null) ? 5 : weight, "TRK-" + number);
		}
	}

	private static void demonstrateMultityping() {
		PriorityPackage priority = new PriorityPackage("PR-demo", 3, "TRACK-9");
		PackageItem asPackage = priority;
		Trackable asTrackable = priority;
		Processable asProcessable = priority;

		System.out.println("[Multityping demo] as PackageItem : " + asPackage.getLabel());
		System.out.println("[Multityping demo] as Trackable   : " + asTrackable.getTrackingInfo());
		asProcessable.markProcessed();
		System.out.println("[Multityping demo] as Processable : processed = " + asProcessable.isProcessed());
	}
}
