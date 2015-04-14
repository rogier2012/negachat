package negachat.presence;

import negachat.client.RoutingTable;
import negachat.messages.SendingMultiSocket;
import negachat.packets.AODV.HELLO;
import negachat.view.NegaView;

/*
 * This thread broadcasts HELLO packets at regular intervals
 */
public class PresenceFlooder implements Runnable {
	private RoutingTable table;
	public static final int DELAY = 5000;

	public PresenceFlooder(RoutingTable table){
		this.table = table;
	}
	
	@Override
	public void run() {
		do {
			// Send new HELLO packet
			HELLO hello = new HELLO(NegaView.getMyName(),table);
			
			
			SendingMultiSocket sock = new SendingMultiSocket();
			sock.send(hello);
			// Wait before new transmission
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while (true);
	}
}
