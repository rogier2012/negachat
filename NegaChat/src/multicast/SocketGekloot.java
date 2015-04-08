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
		 String msg = "Hello:)";
		 InetAddress group = InetAddress.getByName("228.5.6.7");
		 MulticastSocket s = new MulticastSocket(6789);
		
		 s.joinGroup(group);
		 DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
		                             group, 6789);
		 s.send(hi);
		 System.out.println("sent hi");
//		 // get their responses!
		 byte[] buf = new byte[1000];
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);
		 s.receive(recv);
		 
		 
		 System.out.println("recv.toString(): " + recv.toString());
		 System.out.println("getdata.tostring: " + recv.getData().toString());
		 System.out.println("recv.getdata.length: " + recv.getData().length);
		 String received = new String(recv.getData(), 0, recv.getLength());
		 System.out.println("final string: " + received);
 		 
		 // OK, I'm done talking - leave the group...
//		 s.leaveGroup(group);
	}
	
}
