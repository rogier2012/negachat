package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

//import negachat.packets.CreatePacket;
import negachat.packets.MessagePacket;

public class SocketController {
	MessagePacket packet;
	InetAddress group;
	MulticastSocket socket;
	socketReceive socketReceive;
	socketSend socketSend;
	BufferedReader reader;
//	CreatePacket creator;

//	public SocketController(CreatePacket creator) {
//		SocketGekloot sock = new SocketGekloot();
//		this.creator = creator;
//		sock.run();
//	}

	public void run() {
		try {
			connectToGroup();
			socketReceive = new socketReceive(socket);
			startThreads();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connectToGroup() throws IOException {
		// join a Multicast group
		group = InetAddress.getByName("228.5.6.7");
		socket = new MulticastSocket(6789);
		socket.joinGroup(group);
	}

	public void startThreads() throws IOException {
		socketSend = new socketSend(group, socket);
		
		Thread threadReceive = new Thread(socketReceive);
		threadReceive.start();

	}
}
