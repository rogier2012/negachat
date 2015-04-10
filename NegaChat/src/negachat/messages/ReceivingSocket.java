package negachat.messages;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import packets.Packet;

public class ReceivingSocket implements Runnable {
		private DatagramSocket clientsocket;
		public final int SERVER_PORT = 1488;
		InputStream reader;
		String nickname;
		
		public ReceivingSocket(String nickname){
			this.nickname = nickname;
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
			
			if (nickname.equals(packet.getDestination())){
				// verwerk message plz
			} else {
				// zoek dest op in routingtable en stuur door
			}
			
		}
		
		public void handleAODV(Packet packet){
			
		}
		
		
				
		
		
		
		
}
