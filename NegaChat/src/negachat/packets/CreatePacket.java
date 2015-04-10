package negachat.packets;


import negachat.view.NegaView;

//	Class to create a MessagePacket or GroupMessagePacket


public class CreatePacket{
	private String message;
	private String destination;
	private String myName = NegaView.getMyName();
	
	public static final int MAX_MESSAGE_LENGTH = 128;
	public static final int MAX_NAME_LENGTH = 16;

	public Packet composePacket() {
		checkLengths();
		if (destination.equals("all")) {
			GroupMessagePacket GroupPacket = new GroupMessagePacket(myName);
			GroupPacket.setMessage(message);
			GroupPacket.setType((byte) 5);
			GroupPacket.setOptions((byte) 0);
			checkLengths();
			return GroupPacket;
		} else {
			MessagePacket MessagePacket = new MessagePacket(destination, myName);
			MessagePacket.setMessage(message);
			MessagePacket.setType((byte) 0);
			MessagePacket.setOptions((byte) 0);
			return MessagePacket;
			}
		}
	
//	Checken van de lengtes van source, destination en message
	
	private void checkLengths() {
		if (myName.length() < MAX_NAME_LENGTH) {
			int length = myName.length();
			int todo = MAX_NAME_LENGTH - length;
			for (int i = todo; i > 0; i--) {
				myName += "=";
			}
		}
		
		if (message.length() < MAX_MESSAGE_LENGTH) {
			int length = message.length();
			int todo = MAX_MESSAGE_LENGTH - length;
			for (int i = todo; i > 0; i--) {
				message += "=";
			}
		}
		
		if (!destination.equals("all")) {
			if (destination.length() < MAX_NAME_LENGTH) { 
				int length = destination.length();
				int todo = MAX_MESSAGE_LENGTH - length;
				for (int i = todo; i > 0; i--) {
					destination += "=";
				}
			}
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
