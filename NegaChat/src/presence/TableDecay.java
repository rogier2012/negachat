package presence;

import negachat.client.RoutingTable;

public class TableDecay implements Runnable {
	
	public void run()	{
		boolean treu = true;
		RoutingTable tabel = new RoutingTable();
		while (treu)	{
			for (String destination : tabel.getTable().keySet())	{
				if ((int) tabel.getTable().get(destination).get(2) <= 0)	{
					tabel.getTable().remove(destination);
				} else	{
					RoutingTable.decrementTTL(destination);
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
