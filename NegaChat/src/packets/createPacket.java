package packets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MulticastSocket;

import multicast.socketReceive;
import multicast.socketSend;

public class createPacket {
	Packet packet;
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
	
	
}
