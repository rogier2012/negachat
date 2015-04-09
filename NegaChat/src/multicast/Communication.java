package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import packets.Packet;
import packets.createPacket;

public class Communication {
	Packet packet;
	InetAddress group;
	MulticastSocket s;
	socketReceive socketReceive;
	socketSend socketSend;
	BufferedReader reader;
	createPacket creatour;

	public static void main(String[] args) {
		SocketGekloot sock = new SocketGekloot();
		sock.run();
	}

	public void run() {
		try {
			connectToGroup();
			socketReceive = new socketReceive(s);
			startThreads(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connectToGroup() throws IOException {
		// join a Multicast group
		group = InetAddress.getByName("228.5.6.7");
		s = new MulticastSocket(6789);
		s.joinGroup(group);
	}

	public void startThreads(Packet packet) throws IOException {
		socketSend = new socketSend(packet, group, s);
		Thread threadSend = new Thread(socketSend);
		Thread threadReceive = new Thread(socketReceive);
		threadSend.start();
		threadReceive.start();

	}
}
