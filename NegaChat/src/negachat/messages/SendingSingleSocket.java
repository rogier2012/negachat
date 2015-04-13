package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import negachat.client.IPNicknameTable;
import negachat.client.RoutingTable;
import negachat.packets.DirectPacket;
import negachat.packets.AODV.RREQ;

public class SendingSingleSocket {
	private DatagramSocket sendingSocket;
	private static final int UDP_PORT = 6113;
	private InetAddress address;
	private RoutingTable table;
	
	
	public SendingSingleSocket(RoutingTable table) {
		this.table = table;
	}
	

	public void sendPacket(DirectPacket packet) {
		try {
			
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
			
			address = IPNicknameTable.getIP(table.getNextHop(packet.getDestination()));
			sendingSocket = new DatagramSocket(UDP_PORT);
		} catch (IOException e) {
			System.out.println("Couldn't connect to port " + UDP_PORT);
		}
		byte[] bytePacket = packet.toByteArray();
		DatagramPacket toSend = new DatagramPacket(bytePacket, bytePacket.length, address, UDP_PORT);
		try {
			sendingSocket.send(toSend);		
			sendingSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.\nPlease try again.");
		}
	}
}
