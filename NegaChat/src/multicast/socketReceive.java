package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class socketReceive implements Runnable {
	
	MulticastSocket sock;

	public socketReceive(MulticastSocket socket) throws IOException {
		sock = socket;
	}
	
	@Override
	public void run() {
		do {
			byte[] buf = new byte[1000];
			DatagramPacket recv = new DatagramPacket(buf, buf.length);
			try {
				sock.receive(recv);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Oops... Something went wrong receiving a packet.");
			}
	
			String received = new String(recv.getData(), 0, recv.getLength());
			System.out.println("received: " + received);	
		} while (true);
	}

}
