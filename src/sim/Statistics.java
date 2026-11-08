package sim;

public class Statistics {
	private int totalProcessed;
	private int totalRejected;
	private int totalOverloadEvents;
	private int totalSensorFailures;
	private int totalDelivered; 

	public void recordProcessed() { totalProcessed++; }
	public void recordRejected() { totalRejected++; }
	public void recordOverload() { totalOverloadEvents++; }
	public void recordSensorFailure() { totalSensorFailures++; }
	public void recordDelivered() { totalDelivered++; }

	public void printSummary() {
		System.out.println("=== Simulation Statistics ===");
		System.out.println("Delivered to target: " + totalDelivered);
		System.out.println("Station checks passed: " + totalProcessed);
		System.out.println("Rejected: " + totalRejected);
		System.out.println("Overload events: " + totalOverloadEvents);
		System.out.println("Sensor failures: " + totalSensorFailures);
	}
}
