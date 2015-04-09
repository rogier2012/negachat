package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import packets.Packet;

public class SocketGekloot {

	Packet packet;
	InetAddress group;
	MulticastSocket s;
	socketReceive socketReceive;
	socketSend socketSend;
	BufferedReader reader;


	public SocketGekloot() {
//		reader.ready();
		packet = new Packet("Ron", "Gijs", "Hey Ron!");
		System.out.println("Packet made...");
		// try {
		// socket = new MulticastSocket(6789);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static void main(String[] args) {
		SocketGekloot sock = new SocketGekloot();
		sock.run();
	}
	
	public void run() {
		try {
			connectToGroup();
			socketReceive = new socketReceive(s);
			startThreads(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connectToGroup() throws IOException {
		// join a Multicast group
		group = InetAddress.getByName("228.5.6.7");
		s = new MulticastSocket(6789);
		s.joinGroup(group);
	}

	public void startThreads(Packet packet) throws IOException {
//		socketSend = new socketSend(packet, group, s);
		Thread threadSend = new Thread(socketSend);
		Thread threadReceive = new Thread(socketReceive);
		threadSend.start();
		threadReceive.start();

		
//		// send the message
//		byte[] bytePacket = packet.composePacket();
//		System.out.println("Trying to send packet with length " + bytePacket.length + "...");
//		if (bytePacket != null) {
//			DatagramPacket hi = new DatagramPacket(bytePacket, bytePacket.length,
//					group, 6789);
//			s.send(hi);
//			System.out.println("succesfully sent packet!");
//		} else {
//			System.out.println("couldn't send packet!");
//		}
//		// get their responses!
//		byte[] buf = new byte[1000];
//		DatagramPacket recv = new DatagramPacket(buf, buf.length);
//		s.receive(recv);
//
//		String received = new String(recv.getData(), 0, recv.getLength());
//		System.out.println("received: " + received);
//
//		// OK, I'm done talking - leave the group...
//		s.leaveGroup(group);
//		System.out.println("group " + group + " left.");
	}

}
