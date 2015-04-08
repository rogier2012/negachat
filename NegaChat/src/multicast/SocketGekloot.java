package multicast;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class SocketGekloot {

	MulticastSocket socket;
	Packet packet;

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
		sock.doeiets();
	}
	
	public void doeiets() {
		try {
			sendPacket(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPacket(Packet packet) throws IOException {
		// join a Multicast group and send the group salutations
		InetAddress group = InetAddress.getByName("228.5.6.7");
		MulticastSocket s = new MulticastSocket(6789);

		byte[] bytePacket = packet.composePacket();
		
		s.joinGroup(group);
		
		System.out.println("Trying to send...");
		if (bytePacket != null) {
			DatagramPacket hi = new DatagramPacket(bytePacket, bytePacket.length,
					group, 6789);
			s.send(hi);
			System.out.println("succesfully sent packet");
		} else {
			System.out.println("couldn't send packet! probably bytePacket = null!");
		}
		// // get their responses!
		byte[] buf = new byte[1000];
		DatagramPacket recv = new DatagramPacket(buf, buf.length);
		s.receive(recv);

		System.out.println("recv.toString(): " + recv.toString());
		System.out.println("getdata.tostring: " + recv.getData().toString());
		System.out.println("recv.getdata.length: " + recv.getData().length);
		String received = new String(recv.getData(), 0, recv.getLength());
		System.out.println("final string: " + received);

		// OK, I'm done talking - leave the group...
		s.leaveGroup(group);
		System.out.println("group " + group + " left.");
	}

}
