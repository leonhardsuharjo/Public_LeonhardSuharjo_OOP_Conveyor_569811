package sim;

import java.util.ArrayList;
import java.util.List;
import nodes.ConveyorNode;
import nodes.ConveyorLoop;
import console.ConsoleRenderer;
import rules.Rule;
import exceptions.OverloadException;
import exceptions.SimulationException;
import model.PackageItem;

public class Simulation {
	private final List<ConveyorNode> nodes = new ArrayList<>();
	private final List<Rule> rules = new ArrayList<>();
	private final SimulationContext context = new SimulationContext();
	private ConveyorLoop loop;
	
	private final LimitedQueue<PackageItem> waiting;
	public Simulation(int queueCapacity) {
		this.waiting = new LimitedQueue<>(queueCapacity);
	}

	public void setLoop(ConveyorLoop loop) { this.loop = loop; }
	public void addNode(ConveyorNode node) { nodes.add(node); }
	public void addRule(Rule rule) { rules.add(rule); }

	public void enqueue(PackageItem item) throws OverloadException {
		waiting.add(item);
	}

	public void applyRulesTo(PackageItem item) {
		for (Rule rule : rules) {
			rule.apply(item, context);
		}
	}

	private void feedLoop() {
		if (waiting.size() > 0 && loop.isStartFree()) {
			loop.spawnPackage(waiting.remove());
		}
	}

	public void runTicks(int tickCount, ConsoleRenderer renderer) {
		Statistics statistics = context.getStatistics();
		for (int i = 0; i < tickCount; i++) {
			context.advanceTick();
			System.out.println("--- Tick " + context.getCurrentTick() + " ---");

			for (ConveyorNode node : nodes) {
				try {
					node.tick();
				} catch (SimulationException exception) {
					System.out.println("Recoverable failure on " + node.getName() + ": " + exception.getMessage());
				}
			}

			if (loop != null) {
				feedLoop();
				List<PackageItem> arrived = loop.tick();

				for (PackageItem delivered : loop.getDeliveredThisTick()) { 
					statistics.recordDelivered();
					System.out.println("Package " + delivered.getId() + " delivered to the target.");
				}
				int overloads = loop.getRejectedThisTick().size();
				for (int n = 0; n < overloads; n++) {
					statistics.recordOverload();
					statistics.recordRejected();
				}
				for (int n = 0; n < loop.getSensorFailuresThisTick(); n++) {
					statistics.recordSensorFailure();
				}
				for (PackageItem item : arrived) {
					statistics.recordProcessed();
					applyRulesTo(item);
				}
				loop.removeRejected();
				renderer.renderLoop(loop);
			}
		}
		statistics.printSummary();
	}
}

