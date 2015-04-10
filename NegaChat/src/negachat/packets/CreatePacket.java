package negachat.packets;

import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.Action;

import multicast.socketReceive;
import multicast.socketSend;
import negachat.client.IPNicknameTable;
import negachat.view.NegaView;

public class CreatePacket{
	InetAddress group;
	MulticastSocket s;
	socketReceive socketReceive;
	socketSend socketSend;
	String userInput;
	Action packetHasToBeSend;
	
	public String getMessage() {
		return userInput;
	}
	public void setMessage(String userInput) {
		this.userInput = userInput;
	}
	

	public MessagePacket composePacket() {
		MessagePacket packet;
		IPNicknameTable table;
		String input = getMessage();
		String dest = input.split("/")[1].trim();
		String message = input.split("/")[2].trim();
		System.out.println("destination: " + dest);
		System.out.println("message: " + message);
		
		if (table.contains(dest)) {
			if (dest.toLowerCase().equals("all")) {
//				TODO
				packet = new GroupMessagePacket(NegaView.getMyName());
				packet.setMessage(message);
				packet.setDestination("all");
			} else {
//				TODO
				packet = new MessagePacket(NegaView.getMyName());
				packet.setMessage(message);
				packet.setDestination(dest);
			}
		}
		return packet;
	}
	
}
