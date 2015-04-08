package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class SocketGekloot {
	
	MulticastSocket socket;
	
	public SocketGekloot()	{ 
//		 try {
//			socket = new MulticastSocket(6789);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		SocketGekloot sock = new SocketGekloot();
		try {
			sock.nogmeerGekloot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void nogmeerGekloot() throws IOException {
		// join a Multicast group and send the group salutations
		 String msg = "Hello";
		 InetAddress group = InetAddress.getByName("228.5.6.7");
		 MulticastSocket s = new MulticastSocket();
		
		 System.out.println(group);
		 System.out.println("s.getPort(): " + s.getPort());
		
		 s.joinGroup(group);
		 DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
		                             group, 6789);
		 s.send(hi);
		 // get their responses!
		 byte[] buf = new byte[1000];
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);
		 s.receive(recv);
		 
		 System.out.println();
		 
		 
		 
		 // OK, I'm done talking - leave the group...
//		 s.leaveGroup(group);
	}
	
}
