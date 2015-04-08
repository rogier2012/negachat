package multicast;

import java.io.IOException;
import java.net.MulticastSocket;

public class SocketGekloot {
	
	MulticastSocket socket;
	
	public SocketGekloot()	{
		 
		 try {
			 socket = new MulticastSocket(6789);
		 }
		 catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	
}
