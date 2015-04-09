package negachat.messages;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Observable;
import java.util.Observer;

import packets.Packet;

public class ReceivingSocket extends Observable implements Runnable {
		private DatagramSocket clientsocket;
		public final int SERVER_PORT = 1488;
		InputStream reader;
		String myName;
		String otherName;
		Packet recvPacket;
		
		public ReceivingSocket(String myName){
			this.myName= myName;
		}
		
		public void run() {
			do {
				byte[] buf = new byte[1000];
				DatagramPacket recv = new DatagramPacket(buf, 144);
				try {
					clientsocket.receive(recv);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Oops... Something went wrong receiving a packet.");
				}
				if (recv.getData()[0] == 0){
					Packet packet = new Packet(recv.getData());	
					handleMessage(packet);
				} else if (recv.getData()[0] == 1){
					
				}
				
			} while (1<2);
		}
		
		public void handleMessage(Packet packet){
			if (myName.equals(packet.getDestination())){
				// verwerk message plz
				if (packet.makeHash() == packet.getHash()){
					long timestamp = System.currentTimeMillis();
					recvPacket = packet;
					// zet hem op de goede chatbox
				}
			} else {
				// zoek dest op in routingtable en stuur door
			}
			
		}
		
		public void handleAODV(Packet packet){
			
		}
		
		
		public void addObserver(Observer observer){
			this.addObserver(observer);
		}
		
		public Packet getRecvPacket(){
			return recvPacket;
		}
		
		
		
}
