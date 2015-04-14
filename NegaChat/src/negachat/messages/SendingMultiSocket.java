package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import negachat.packets.Packet;

public class SendingMultiSocket {

	private MulticastSocket sendingSocket;
	
	public static final int MULTICAST_PORT = 6115;
	
	private Inet4Address group;

	public SendingMultiSocket() {
		try {
			group = (Inet4Address)Inet4Address.getByName("228.5.6.7");
			sendingSocket = new MulticastSocket(MULTICAST_PORT);
			sendingSocket.joinGroup(new InetSocketAddress(group, MULTICAST_PORT), NetworkInterface.getByName("wlan0"));
		} catch (IOException e) {
			System.out.println("Couldn't connect to port " + MULTICAST_PORT);
		}
	}
	
	public void send(Packet packet) {
		byte[] bytePacket = packet.toByteArray();
		DatagramPacket dPacket;
		
		dPacket = new DatagramPacket(bytePacket, bytePacket.length,
					group, ReceivingMultiSocket.MULTICAST_PORT);
		
		try {
			sendingSocket.send(dPacket);
			System.out.println("Packet sent!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.");

		}
	}

}
