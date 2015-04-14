package negachat.presence;

import negachat.client.RoutingTable;

/*
 * This thread ensures the TTL of the routes that are stored in the Routing Table is decremented. When a route is out-dated it is removed.
 */
public class TableDecay implements Runnable {
	private RoutingTable table;
	public TableDecay(RoutingTable table){
		this.table = table;
	}
	
	public void run()	{
		boolean treu = true;
		while (treu)	{
			// For each route in the routing table
			for (String destination : table.getTable().keySet())	{
				// If for too long the destination's presence has not been confirmed 
				if ((int) table.getTable().get(destination).get(2) <= 0)	{
					// Remove this route
					table.removeDestination(destination);
				} else	{ // (Route is still valid)
					// Decrement route TTL
					table.decrementTTL(destination);
				}
			}
			// Wait one second
			try {
			    Thread.sleep(1000);               
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}
