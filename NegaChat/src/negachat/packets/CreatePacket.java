package negachat.packets;

import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import multicast.socketReceive;
import multicast.socketSend;
import negachat.client.IPNicknameTable;
import negachat.view.NegaView;

//	Class to create a MessagePacket or GroupMessagePacket


public class CreatePacket{
	private InetAddress group;
	private MulticastSocket s;
	private String message;
	private String destination;
	private Action packetHasToBeSend;	
	
	public static final int MAX_MESSAGE_LENGTH = 128;
	
	public CreatePacket() {
		
	}

	public Packet composePacket() {
//		TODO IPNicknameTable moet gemaakt worden
		Packet packet;
			if (destination.equals("all")) {
//				TODO maken GroupMessagePacket

//				GroupMessagePacket packet = new GroupMessagePacket(NegaView.getMyName());
//				packet.setMessage(message);
//				packet.setDestination("all");
			} else {

				
//				TODO IPNicknameTable class moet uitgebreid worden
				if (IPNicknameTable => table.contains(destination)) {
					packet = new MessagePacket(destination, NegaView.getMyName());
				
					if(message.length() < MAX_MESSAGE_LENGTH) {
						
						int length = message.length();
						int todo = MAX_MESSAGE_LENGTH - length;
						List<String> messageList = new ArrayList<String>();
						for (int i = todo; i > 0; i--) {
							message += "0";
						}					
						((MessagePacket) packet).setMessage(message);
						((MessagePacket) packet).setType((byte) 0);
						((MessagePacket) packet).setOptions((byte) 0);
					} else if (message.length() > MAX_MESSAGE_LENGTH) {
						System.out.println("Message exceeds maximum length!\nMaximum length is " + MAX_MESSAGE_LENGTH + ", you have " + message.length() + " characters.");
					}
					

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

	public InetAddress getGroup() {
		return group;
	}

	public void setGroup(InetAddress group) {
		this.group = group;
	}

	public MulticastSocket getS() {
		return s;
	}

	public void setS(MulticastSocket s) {
		this.s = s;
	}

	public Action getPacketHasToBeSend() {
		return packetHasToBeSend;
	}

	public void setPacketHasToBeSend(Action packetHasToBeSend) {
		this.packetHasToBeSend = packetHasToBeSend;
	}

	
}
