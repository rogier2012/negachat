package negachat.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

public class RoutingTable extends Observable { 

	public static final int MAXTTL = 20;
	
	// nextHop - hopCount
	// List<Object> hops;
	// destination - hops
	private static Map<String, List<Object>> table;
	
	private static String removedDestination;
	private static String addedDestination;
	
	private static List<String> requestedDestinations;

	public RoutingTable(){
		table = new HashMap<String, List<Object>>();
		requestedDestinations = new ArrayList<String>();
	}
	
	public String getNextHop(String destination){
		return (String) table.get(destination).get(1); 
	}
	
	public void addDestination(String destination, String nexthop, int hopCount){
		addedDestination = destination;
		table.put(destination, new ArrayList<Object>());
		table.get(destination).add(nexthop);
		table.get(destination).add(hopCount);
		table.get(destination).add(MAXTTL);	
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
		return  RoutingTable.addedDestination;
	}
	
	public String getRemovedDestination() {
		return removedDestination;
	}

	public Map<String, List<Object>> getTable() {
		return table;
	}

	public void setTable(Map<String, List<Object>> table) {
		RoutingTable.table = table;
	}
	
	public  static void decrementTTL(String destination){
		int TTL = (int) RoutingTable.table.get(destination).get(2);
		RoutingTable.table.get(destination).set(2, TTL);
	}
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public static List<String> getRequestedDestinations() {
		return requestedDestinations;
	}

	public static void setRequestedDestinations(List<String> requestedDestinations) {
		RoutingTable.requestedDestinations = requestedDestinations;
	}
	
}
