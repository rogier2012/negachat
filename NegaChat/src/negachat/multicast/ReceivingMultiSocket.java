package negachat.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import negachat.messages.ReceivingSocket;
import negachat.packets.GroupMessagePacket;
import negachat.packets.Packet;
import negachat.packets.AODV.HELLO;
import negachat.packets.AODV.RREQ;

public class ReceivingMultiSocket extends ReceivingSocket {
	private InetAddress group;
	private MulticastSocket multisocket;
	
	public static final int MULTICAST_PORT = 6112;
	
	public void run() {
		do{
			byte[] buf = new byte[1000];
			DatagramPacket recv = new DatagramPacket(buf, buf.length);
			try {
				multisocket = new MulticastSocket(MULTICAST_PORT);
				group = InetAddress.getByName("228.5.6.7");
				multisocket.joinGroup(group);
				multisocket.receive(recv);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Oops... Something went wrong receiving a packet.");
			}
			if (recv.getData()[0] == HELLO.TYPE) {
				HELLO packet = new HELLO(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == RREQ.TYPE) {
				RREQ packet = new RREQ(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == GroupMessagePacket.TYPE) {
				GroupMessagePacket packet = new GroupMessagePacket(recv.getData());
				handlePacket(packet);
			}
			
		} while (1 < 2);
	}

	public void handlePacket(Packet packet) {
		if (packet instanceof HELLO){
			
		} else if (packet instanceof RREQ){
			
		} else if (packet instanceof GroupMessagePacket){
			
		}
	}
	
	
}
