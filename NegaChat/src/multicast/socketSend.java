package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import negachat.packets.MessagePacket;

public class socketSend {
	
	MessagePacket packet;
	InetAddress group;
	DatagramSocket sock;

	public socketSend(InetAddress group, DatagramSocket sock) {
		this.group = group;
		this.sock = sock;
	}
	
	public void send() {
		byte[] bytePacket = packet.toByteArray();
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
