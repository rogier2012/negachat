package negachat.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class RoutingTable extends Observable {
	// nextHop - hopCount
	// List<Object> hops;
	// destination - hops
	private static Map<String, List<Object>> table;
	
	private static String removedDestination;
	private static String addedDestination;
	
	public RoutingTable(){
		table = new HashMap<String, List<Object>>();
	}
	
	public String getNextHop(String destination){
		return (String) table.get(destination).get(1); 
	}
	
	public void addDestination(String destination, String nexthop, int hopCount){
		addedDestination = destination;
		table.put(destination, new ArrayList<Object>());
		table.get(destination).add(nexthop);
		table.get(destination).add(hopCount);
		this.setChanged();
		this.notifyObservers(1);
	}
	
	public void removeDestination(String destination){
		removedDestination = destination;
		table.remove(destination);
		this.setChanged();
		this.notifyObservers(2);
	}
	
	public String getAddedDestination(){
		return this.addedDestination;
	}
	
	public String getRemovedDestination() {
		return removedDestination;
	}

	public Map<String, List<Object>> getTable() {
		return table;
	}

	public void setTable(Map<String, List<Object>> table) {
		this.table = table;
	}
	
}
