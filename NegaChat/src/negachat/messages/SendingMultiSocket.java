package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import negachat.packets.Packet;
import adHocDistanceVectorRouting.RoutingTable;

public class SendingMultiSocket {

	private DatagramSocket sendingSocket;
	private static final int MULTICAST_PORT = 6112;
	private InetAddress group;

	public SendingMultiSocket(RoutingTable table) {
		try {
			group = InetAddress.getByName("228.5.6.7");
			sendingSocket = new DatagramSocket(MULTICAST_PORT, group);
		} catch (IOException e) {
			System.out.println("Couldn't connect to port " + MULTICAST_PORT);
		}
	}
	
	public void send(Packet packet) {
		byte[] bytePacket = packet.toByteArray();
		DatagramPacket dPacket = new DatagramPacket(bytePacket, bytePacket.length,
				group, MULTICAST_PORT);
		try {
			sendingSocket.send(dPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.");

		}
	}

}
