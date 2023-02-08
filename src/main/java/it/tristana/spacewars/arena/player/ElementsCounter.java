package it.tristana.spacewars.arena.player;

import java.util.HashMap;
import java.util.Map;

public class ElementsCounter<T> {

	private Map<Class<? extends T>, Integer> items;
	
	public ElementsCounter() {
		items = new HashMap<>();
	}
	
	public int getAmount(Class<? extends T> clazz) {
		Integer result = items.get(clazz);
		return result == null ? 0 : result.intValue();
	}
	
	public void addElement(Class<? extends T> clazz) {
		Integer result = items.get(clazz);
		items.put(clazz, result == null ? 1 : result + 1);
	}
}
