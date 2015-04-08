package multicast;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class SocketGekloot {

	MulticastSocket socket;
	Packet packet;
	InetAddress group;
	MulticastSocket s;


	public SocketGekloot() {
		packet = new Packet("pens", "jens", "jensiepensie");
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
			sendPacket(packet);
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

	public void sendPacket(Packet packet) throws IOException {
		// send the message
		byte[] bytePacket = packet.composePacket();
		System.out.println("Trying to send packet with length " + bytePacket.length + "...");
		if (bytePacket != null) {
			DatagramPacket hi = new DatagramPacket(bytePacket, bytePacket.length,
					group, 6789);
			s.send(hi);
			System.out.println("succesfully sent packet!");
		} else {
			System.out.println("couldn't send packet!");
		}
		// // get their responses!
		byte[] buf = new byte[1000];
		DatagramPacket recv = new DatagramPacket(buf, buf.length);
		s.receive(recv);

		String received = new String(recv.getData(), 0, recv.getLength());
		System.out.println("received: " + received);

		// OK, I'm done talking - leave the group...
		s.leaveGroup(group);
		System.out.println("group " + group + " left.");
	}

}
