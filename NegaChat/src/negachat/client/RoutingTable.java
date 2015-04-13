package negachat.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class RoutingTable extends Observable {
	// Destination  - Next Hop
	private Map<String, String> table;
	private String removedDestination;
	private String addedDestination;
	
	public RoutingTable(){
		table = new HashMap<String, String>();
	}
	
	public String getNextHop(String destination){
		return table.get(destination); 
	}
	
	public void addDestination(String destination, String nexthop){
		addedDestination = destination;
		table.put(destination, nexthop);
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
	
}
