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

	public final int MAXTTL = 20;
	
	// nextHop - hopCount
	// List<Object> hops; 
	// destination - hops
	private  Map<String, List<Object>> table;
	private Map<String, InetAddress> iptable;
	
	private  String removedDestination;
	private  String addedDestination;
	
	private  List<String> requestedDestinations;

	public RoutingTable(){
		table = new HashMap<String, List<Object>>();
		iptable = new HashMap<String, InetAddress>();
		requestedDestinations = new ArrayList<String>();
	}
	
	public String getNextHop(String destination){
		return (String) table.get(destination).get(0); 
	}
	
	public void addDestination(String destination, String nexthop, int hopCount){
		addedDestination = destination;
		table.put(destination, new ArrayList<Object>());
		table.get(destination).add(nexthop);
		table.get(destination).add(hopCount);
		table.get(destination).add(MAXTTL);	
		System.out.println("Destination added to table enzo :)");
		this.setChanged();
		this.notifyObservers(1);
		for (String namen : table.keySet() ){
			System.out.println(namen);
		}
	}
	
	public void removeDestination(String destination){
		removedDestination = destination;
		table.remove(destination);
		this.setChanged();
		this.notifyObservers(2);
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
	
	public  void decrementTTL(String destination){
		int TTL = (int) this.table.get(destination).get(2);
		this.table.get(destination).set(2, TTL);
	}
	
	public int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public List<String> getRequestedDestinations() {
		return requestedDestinations;
	}

	public void setRequestedDestinations(List<String> requestedDestinations) {
		this.requestedDestinations = requestedDestinations;
	}
	
	public  byte[] getMyIP()	{
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
	
}
