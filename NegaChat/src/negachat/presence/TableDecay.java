package negachat.presence;

import negachat.client.RoutingTable;

public class TableDecay implements Runnable {
	private RoutingTable table;
	public TableDecay(RoutingTable table){
		this.table = table;
	}
	
	
	public void run()	{
		boolean treu = true;
		while (treu)	{
			for (String destination : table.getTable().keySet())	{
				if ((int) table.getTable().get(destination).get(2) <= 0)	{
					table.getTable().remove(destination);
				} else	{
					table.decrementTTL(destination);
				}
			}
			try {
			    Thread.sleep(1000);               
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}
