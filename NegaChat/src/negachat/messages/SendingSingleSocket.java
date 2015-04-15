package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import negachat.client.RoutingTable;
import negachat.packets.DirectPacket;
import negachat.packets.AODV.RREQ;

public class SendingSingleSocket {
	private DatagramSocket sendingSocket;
	private static final int UDP_PORT = 6110;
	private InetAddress address;
	private RoutingTable table;
	
	
	public SendingSingleSocket(RoutingTable table) {
		this.table = table;
	}
	

	public void sendPacket(DirectPacket packet) {
		// If the RoutingTable does not have a route to this destination:
		if (!table.getTable().containsKey(packet.getDestination()) || table.getTable().get(packet.getDestination()).get(0) == null)	{
			// Pick a random identifier for the RREQ
			byte identifier = (byte) table.randInt(0, 255);
			// Add destination to requested destinations in the routing table
			table.getRequestedDestinations().add(packet.getDestination());
			// Set RREQ lifespan
			byte lifeSpan = 50;
			// As long as a route is still not in the table:
			while (!table.getTable().containsKey(packet.getDestination()) || table.getTable().get(packet.getDestination()).get(0) == null){
				// Send RREQ
				SendingMultiSocket sendSocket = new SendingMultiSocket();
				sendSocket.send(new RREQ(packet.getDestination(), lifeSpan, identifier));
				// Raise lifespan for next RREQ
				lifeSpan++;
				// Delay before sending new RREQ
				try { 
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		address = table.getIP(table.getNextHop(packet.getDestination()));
		byte[] bytePacket = packet.toByteArray();
		DatagramPacket toSend = new DatagramPacket(bytePacket, bytePacket.length, address, ReceivingSingleSocket.UDP_PORT);
		try {
			sendingSocket = new DatagramSocket(UDP_PORT);
			sendingSocket.send(toSend);
			sendingSocket.disconnect();
			sendingSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.\nPlease try again.");
		}
	}
}
