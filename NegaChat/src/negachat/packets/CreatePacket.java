package negachat.packets;

import negachat.view.NegaView;

//	Class to create a MessagePacket or GroupMessagePacket


public class CreatePacket{
	private String message;
	private String destination;
	
	public static final int MAX_MESSAGE_LENGTH = 128;

	public Packet composePacket() {
		Packet packet = null;
			if (destination.equals("all")) {
				GroupMessagePacket GroupPacket = new GroupMessagePacket(NegaView.getMyName());
				GroupPacket.setMessage(message);
				GroupPacket.setType((byte) 5);
				GroupPacket.setOptions((byte) 0);
			} else {
				packet = new MessagePacket(destination, NegaView.getMyName());
				if(message.length() < MAX_MESSAGE_LENGTH) {
					int length = message.length();
					int todo = MAX_MESSAGE_LENGTH - length;
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
