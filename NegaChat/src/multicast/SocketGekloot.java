package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		getInput();
		// try {
		// socket = new MulticastSocket(6789);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private void getInput() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String string = reader.readLine();
			System.out.println("Did you type: '" + string + "'?");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't read!");
		}
		
		
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
		socketSend = new socketSend(group, s);
		Thread threadReceive = new Thread(socketReceive);
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
