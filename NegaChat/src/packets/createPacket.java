package packets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MulticastSocket;
import multicast.socketReceive;
import multicast.socketSend;

public class createPacket {
	InetAddress group;
	MulticastSocket s;
	socketReceive socketReceive;
	socketSend socketSend;
	String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void composePacket() {
		String msg = getMessage();
		
		String dest = msg.split("/")[1];
		String message = msg.split("/")[2].trim();
		
		System.out.println("dest: " + dest);
		System.out.println("rest: " + message);
		
		
		
		
		
//		return packet;
	}
	
}
