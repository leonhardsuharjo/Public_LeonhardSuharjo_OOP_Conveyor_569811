package sim;

import exceptions.OverloadException;  
import java.util.ArrayDeque; 
import java.util.Queue;

public class LimitedQueue<T> {
	private final Queue<T> items;  
	private final int capacity; 
	
	public LimitedQueue(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be positive");
		}
		this.capacity = capacity;  
		this.items = new ArrayDeque<>(); 
	}
	
	public void add(T item) throws OverloadException {
		if (items.size() >= capacity) {
			throw new OverloadException("Queue is full.");
		}
		items.offer(item);  
	}
	
	public T remove() {
		return items.poll(); 
	}
	
	public int size() {
		return items.size();
	}
}

