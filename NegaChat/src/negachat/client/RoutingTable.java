package negachat.client;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

public class RoutingTable extends Observable { 
	
	/*
	 * Constants
	 */
	
	// A route is dropped from the RoutingTable if no HELLO broadcasts have been received from the destination for MAXTTL seconds.
	public static final int MAXTTL = 20;
	
	/*
	 * Instance variables
	 */
	
	// List<Object> : {(String) nextHop, (int) hopCount, (int) TTL}
	private Map<String, List<Object>> table;
	// Map that links nicknames to InetAddresses
	private Map<String, InetAddress> iptable;
	
	// Last destination that was removed to the RoutingTable
	private  String removedDestination;
	// Last destination that was added to the RoutingTable
	private  String addedDestination;
	
	// List of all nodes for which we have sent a RREQ and no RREP has returned
	private  List<String> requestedDestinations;
	
	/*
	 * Constructors
	 */
	
	public RoutingTable(){
		table = new HashMap<String, List<Object>>();
		iptable = new HashMap<String, InetAddress>();
		requestedDestinations = new ArrayList<String>();
	}
	
	/*
	 * Queries
	 */
	
	public String getNextHop(String destination){
		return (String) table.get(destination).get(0); 
	}
	
	public int getHopCount(String destination){
		return (int) table.get(destination).get(1); 
	}
	
	public int getRouteTTL(String destination){
		return (int) table.get(destination).get(2); 
	}
	
	public byte[] getMyIP()	{
		NetworkInterface in;
		Enumeration<InetAddress> hallo = null;
		try {
			in = NetworkInterface.getByName("wlan0");
			hallo = in.getInetAddresses();
			hallo.nextElement();
		} catch	(SocketException e){
			e.printStackTrace();
		}
		return hallo.nextElement().getAddress();
	}
	
	/*
	 * Commands
	 */
	
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
	
	public  void decrementTTL(String destination){
		int TTL = (int) this.table.get(destination).get(2);
		TTL--;
		this.table.get(destination).set(2, TTL);
	}
	
	public int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/*
	 * Getters and Setters
	 */
	
	public  InetAddress getIP(String nickName) {
		return iptable.get(nickName);
	}

	public boolean contains(String nickName) {
		return iptable.containsKey(nickName);
	}
	
	public Map<String, InetAddress> getIPTable() {
		return iptable;
	}
	
	public void add(String nickName, InetAddress IP) {
		iptable.put(nickName, IP);
	}
	
	public String getAddedDestination(){
		return  this.addedDestination;
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
	
	public List<String> getRequestedDestinations() {
		return requestedDestinations;
	}

	public void setRequestedDestinations(List<String> requestedDestinations) {
		this.requestedDestinations = requestedDestinations;
	}
	
}
