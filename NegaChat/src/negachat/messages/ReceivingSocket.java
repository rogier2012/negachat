package negachat.messages;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ReceivingSocket implements Runnable {
		private ServerSocket ssocket;
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
		
				byte[] packet = recv.getData();	
				handleData(packet);
			} while (1<2);
		}
		
		public void handleData(byte[] packet){
			byte[] destination = new byte[16];
			for (int i = 0; i < 16; i++){
				destination[i] = packet[i+1];
			}
			String destNickname = new String(destination, 0, packet.length);
			if (nickname.equals(destNickname)){
				// verwerk message plz
			} else {
				// zoek dest op in routingtable en stuur door
			}
			
		}
		
		
				
		
		
		
		
}
