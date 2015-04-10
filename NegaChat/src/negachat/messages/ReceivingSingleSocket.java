package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;
import negachat.packets.AODV.RERR;
import negachat.packets.AODV.RREP;

public class ReceivingSingleSocket extends ReceivingSocket {
	private DatagramSocket clientsocket;
	public final int UDP_PORT = 6113;
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
				clientsocket = new DatagramSocket(UDP_PORT);
				clientsocket.receive(recv);
			} catch (IOException e) {
				e.printStackTrace();
				System.out
						.println("Oops... Something went wrong receiving a packet.");
			}
			if (recv.getData()[0] == MessagePacket.TYPE) {
				MessagePacket packet = new MessagePacket(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == RREP.TYPE) {
				RREP packet = new RREP(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == RERR.TYPE) {
				RERR packet = new RERR(recv.getData());
				handlePacket(packet);
			}

		} while (1 < 2);
	}

	public void handlePacket(Packet packet) {
		if (packet instanceof MessagePacket
				&& myName.equals(((MessagePacket) packet).getDestination())) {
			if (((MessagePacket) packet).makeHash() == ((MessagePacket) packet).getHash()) {
				setTimestamp(System.currentTimeMillis());
				recvPacket = packet;
				setChanged();
				notifyObservers();
			}
		} else if (packet instanceof RREP){
			
		} else if (packet instanceof RERR){
			
		}
	}

	public void testrun() {
		MessagePacket nPacket = new MessagePacket("All", "Henk");
		nPacket.setMessage("Ik ben Rogier");
		handlePacket(nPacket);
	}

}
