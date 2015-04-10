package negachat.packets;

import negachat.view.NegaView;

//	Class to create a MessagePacket or GroupMessagePacket


public class CreatePacket{
	private String message;
	private String destination;
	
	public static final int MAX_MESSAGE_LENGTH = 128;

	public Packet composePacket() {
			if (destination.equals("all")) {
				GroupMessagePacket GroupPacket = new GroupMessagePacket(NegaView.getMyName());
				GroupPacket.setMessage(message);
				GroupPacket.setType((byte) 5);
				GroupPacket.setOptions((byte) 0);
				checkLengths();
				return GroupPacket;
			} else {
				MessagePacket packet= new MessagePacket(destination, NegaView.getMyName());
					((MessagePacket) packet).setMessage(message);
					((MessagePacket) packet).setType((byte) 0);
					((MessagePacket) packet).setOptions((byte) 0);
					return packet;
				} else if (message.length() > MAX_MESSAGE_LENGTH) {
					System.out.println("Message exceeds maximum length!\nMaximum length is " + MAX_MESSAGE_LENGTH + ", you have " + message.length() + " characters.");
					return null;
				}
				

			}
			return null;
	}
	
//	Checken van de lengtes van source, destination en message
	
	private void checkLengths() {
		// TODO Auto-generated method stub
						if(message.length() < MAX_MESSAGE_LENGTH) {
					int length = message.length();
					int todo = MAX_MESSAGE_LENGTH - length;
					for (int i = todo; i > 0; i--) {
						message += "0";
					}					

	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDestination(String destination) {
		this.destination = destination.toLowerCase();
	}
	public String getDestination() { 
		return destination;
	}
}
