package negachat.packets;

import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.Action;

import multicast.socketReceive;
import multicast.socketSend;
import negachat.client.IPNicknameTable;
import negachat.view.NegaView;

public class CreatePacket{
	private InetAddress group;
	private MulticastSocket s;
	private socketReceive socketReceive;
	private socketSend socketSend;
	private String message;
	private String destination;
	private Action packetHasToBeSend;	
	
	public static final int MAX_MESSAGE_LENGTH = 128;

	public Packet composePacket() {
//		TODO IPNicknameTable moet gemaakt worden
			if (destination.toLowerCase().equals("all")) {
//				TODO maken GroupMessagePacket

//				GroupMessagePacket packet = new GroupMessagePacket(NegaView.getMyName());
//				packet.setMessage(message);
//				packet.setDestination("all");
			} else {
				if (IPNicknameTable => table.contains(destination)) {
					MessagePacket packet = new MessagePacket(destination, NegaView.getMyName());
				
//					als message kleiner is dan 128 bytes => aanvullen
					if(message.length() < MAX_MESSAGE_LENGTH) {
						for (int i = message.length(); i < MAX_MESSAGE_LENGTH; i++) {
							
						}
					}
					
					packet.setMessage(message);
					packet.setDestination(destination);
				}
			}
		return packet;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDestination() { 
		return destination;
	}

	
}
