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
	BufferedReader reader;

	
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
}
