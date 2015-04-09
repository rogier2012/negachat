package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import packets.Packet;

public class socketSend implements Runnable {
	
	Packet packet;
	InetAddress group;
	MulticastSocket sock;

	public socketSend(Packet packet, InetAddress group, MulticastSocket sock) {
		this.packet = packet;
		this.group = group;
		this.sock = sock;
	}
	
	@Override
	public void run() {
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
