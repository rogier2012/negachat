package negachat.messages;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import packets.Packet;

public class SendingSocket {
	Socket sendingSocket;
	private static final int PORT = 1488;
	
	
	public SendingSocket(InetAddress address) {
		try {
			sendingSocket = new Socket(address, PORT);
		} catch (IOException e) {
			System.out.println("Couldn't connect to " + address + " on port " + PORT);
		}
	}
	
	public void sendPacket(Packet packet) {
		sendingSocket.
	}
	
	
	
}
