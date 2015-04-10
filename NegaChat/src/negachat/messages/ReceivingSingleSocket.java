package negachat.messages;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;

public class ReceivingSingleSocket extends ReceivingSocket{
	private DatagramSocket clientsocket;
	public final int SERVER_PORT = 1488;
	private String myName;
	
	
	public ReceivingSingleSocket(String myName) {
		this.myName = myName;
	}

	@Override
	public void run() {
		do {
			byte[] buf = new byte[1000];
			DatagramPacket recv = new DatagramPacket(buf, 146);
			try {
				clientsocket = new DatagramSocket(SERVER_PORT);
				clientsocket.receive(recv);
			} catch (IOException e) {
				e.printStackTrace();
				System.out
						.println("Oops... Something went wrong receiving a packet.");
			}
			if (recv.getData()[0] == 0) {
				MessagePacket packet = new MessagePacket(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == 1) {

			}

		} while (1 < 2);
	}

	public void handlePacket(Packet packet) {
		if (packet instanceof MessagePacket && myName.equals(((MessagePacket)packet).getDestination())) {
			// verwerk message plz
			if (((MessagePacket)packet).makeHash() == ((MessagePacket)packet).getHash()) {
				long timestamp = System.currentTimeMillis();
				recvPacket = packet;
				setChanged();
				notifyObservers();
			}
		}
	}
	
	public void testrun() {
		MessagePacket nPacket = new MessagePacket("All", "Henk");
		nPacket.setMessage("Ik ben Rogier");
		handlePacket(nPacket);
	}
	
}
