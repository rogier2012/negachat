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
		
		
		if (!table.getTable().containsKey(packet.getDestination()) || table.getTable().get(packet.getDestination()).get(0) == null)	{
			byte identifier = (byte) table.randInt(0, 127);
			table.getRequestedDestinations().add(packet.getDestination());
			byte lifeSpan = 50;
			while (!table.getTable().containsKey(packet.getDestination()) || table.getTable().get(packet.getDestination()).get(0) == null){
				SendingMultiSocket sendSocket = new SendingMultiSocket();
				sendSocket.send(new RREQ(packet.getDestination(), lifeSpan, identifier));
				
				lifeSpan++;
				try { 
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		address = table.getIP(table.getNextHop(packet.getDestination()));
		byte[] bytePacket = packet.toByteArray();
		DatagramPacket toSend = new DatagramPacket(bytePacket, bytePacket.length, address, UDP_PORT);
		try {
			sendingSocket = new DatagramSocket(UDP_PORT);
			sendingSocket.send(toSend);		
			sendingSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.\nPlease try again.");
		}
	}
}
