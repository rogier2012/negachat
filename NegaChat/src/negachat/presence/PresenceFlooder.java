package negachat.presence;

import java.security.PublicKey;

import negachat.client.RoutingTable;
import negachat.encryption.AssymetricEncrypter;
import negachat.messages.SendingMultiSocket;
import negachat.packets.AODV.HELLO;
import negachat.view.NegaView;

/*
 * This thread broadcasts HELLO packets at regular intervals
 */
public class PresenceFlooder implements Runnable {
	private RoutingTable table;
	public static final int DELAY = 5000;
	private PublicKey key;
	
	
	public PresenceFlooder(RoutingTable table, AssymetricEncrypter encrypter){
		this.table = table;
		key = encrypter.getKey();
		
	}
	
	@Override
	public void run() {
		do {
			// Send new HELLO packet
			HELLO hello = new HELLO(NegaView.getMyName(),table, key);
			
			
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
