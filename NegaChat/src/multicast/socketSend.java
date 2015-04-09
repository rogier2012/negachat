package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import packets.Packet;
import packets.createPacket;

public class socketSend {
	
	Packet packet;
	InetAddress group;
	MulticastSocket sock;

	public socketSend(InetAddress group, MulticastSocket sock) {
		this.group = group;
		this.sock = sock;
	}
	
	public void send() {
		byte[] bytePacket = packet.composePacket();
		System.out.println("Trying to send packet with length " + bytePacket.length + "...");
		DatagramPacket hi = new DatagramPacket(bytePacket, bytePacket.length,
				group, 6789);
		try {
			sock.send(hi);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops... Something went wrong sending this packet.");

		}
		System.out.println("succesfully sent packet!");
	}

}
