package negachat.messages;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Observable;
import java.util.Observer;

import negachat.packets.MessagePacket;

public class ReceivingSocket extends Observable implements Runnable {
		private DatagramSocket clientsocket;
		public final int SERVER_PORT = 1488;
		InputStream reader;
		String myName;
		String otherName;
		MessagePacket recvPacket;
		
		public ReceivingSocket(String myName){
			this.myName= myName;
		}
		
		public void run() {
			do {
				byte[] buf = new byte[1000];
				DatagramPacket recv = new DatagramPacket(buf, 146);
				try {
					clientsocket = new DatagramSocket(SERVER_PORT);
					clientsocket.receive(recv);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Oops... Something went wrong receiving a packet.");
				}
				if (recv.getData()[0] == 0){
					MessagePacket packet = new MessagePacket(recv.getData());	
					handleMessage(packet);
				} else if (recv.getData()[0] == 1){
					
				}
				
			} while (1<2);
		}
		
		public void handleMessage(MessagePacket packet){
			if (myName.equals(packet.getDestination())){
				// verwerk message plz
				if (packet.makeHash() == packet.getHash()){
					long timestamp = System.currentTimeMillis();
					recvPacket = packet;
					setChanged();
				    notifyObservers();
				}
			}
		}
		
	public void handleAODV(MessagePacket packet){
		
	}
	
	public MessagePacket getRecvPacket(){
		return recvPacket;
	}
		
	public void testrun(){
		MessagePacket nPacket = new MessagePacket("All", "Henk");
		nPacket.setMessage("Ik ben Rogier");
		handleMessage(nPacket);
	}
		
		
}
