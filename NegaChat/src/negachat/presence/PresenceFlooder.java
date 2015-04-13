package negachat.presence;

import negachat.client.RoutingTable;
import negachat.packets.AODV.HELLO;
import negachat.view.NegaView;

public class PresenceFlooder implements Runnable {
	private RoutingTable table;
	public static final int DELAY = 5000;

	public PresenceFlooder(RoutingTable table){
		this.table = table;
	}
	
	@Override
	public void run() {
		do {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			HELLO hello = new HELLO(NegaView.getMyName(),table);
			hello.send(hello);
			System.out.println("\nHELLO sent!");
		} while (true);
	}
}
