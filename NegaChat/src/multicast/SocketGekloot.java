package multicast;

import java.net.MulticastSocket;

public class SocketGekloot {
	
	MulticastSocket socket;
	
	public SocketGekloot()	{
		 socket = new MulticastSocket(6789);
	}
	
	
}
