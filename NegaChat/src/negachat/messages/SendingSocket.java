package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import packets.Packet;
import adHocDistanceVectorRouting.RoutingTable;

public class SendingSocket {
	DatagramSocket sendingSocket;
	private static final int PORT = 1488;
	InetAddress address;
	
	
	public SendingSocket(RoutingTable table) {
		try {
			address = InetAddress.getByName(table.getNextHop(88));
			sendingSocket = new DatagramSocket(PORT, address);
		} catch (IOException e) {
			System.out.println("Couldn't connect to port " + PORT);
		}
	}
	
	public void sendPacket(Packet packet) {
		byte[] bytePacket = packet.toByteArray();
		System.out.println("Trying to send packet with length " + bytePacket.length + "...");
		DatagramPacket hi = new DatagramPacket(bytePacket, bytePacket.length,
				InetAddress.getByName(address), 1488);
		try {
			sock.send(hi);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.");

		}
		System.out.println("succesfully sent packet!");
	}
	}
	
	
	
}
