package negachat.packets;

import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.Action;

import multicast.socketReceive;
import multicast.socketSend;

public class CreatePacket{
	InetAddress group;
	MulticastSocket s;
	socketReceive socketReceive;
	socketSend socketSend;
	String message;
	Action packetHasToBeSend;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	public MessagePacket composePacket() {
		MessagePacket packet = null;
		String msg = getMessage();
		
		String dest = msg.split("/")[1].trim();
		String message = msg.split("/")[2].trim();
		
		System.out.println("dest: " + dest);
		System.out.println("message: " + message);
		
		
		
		
		
		return packet;
	}
	
}
