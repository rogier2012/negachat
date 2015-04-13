package negachat.packets;


import negachat.view.NegaView;

//	Class to create a MessagePacket or GroupMessagePacket


public class CreatePacket{
	private String message;
	private String destination;
	private String myName;
	private int seqNumber;
	
	public static final int MAX_MESSAGE_LENGTH = 128;
	public static final int MAX_NAME_LENGTH = 16;
	
	public CreatePacket() {
		seqNumber = 300;
		myName = NegaView.getMyName(); 
	}

	public Packet composePacket() {
		checkLengths();
		if (seqNumber == 300) {
			ACK_Packet ACK = new ACK_Packet(myName, seqNumber);
			ACK.setDestination(destination);
			ACK.setOptions((byte) seqNumber);
			ACK.setSource(myName);
			return ACK;
		} else {
			if (destination.equals("all")) {
				GroupMessagePacket GroupPacket = new GroupMessagePacket(myName);
				GroupPacket.setMessage(message);
				GroupPacket.setOptions((byte) seqNumber);
				checkLengths();
				return GroupPacket;
			} else {
				MessagePacket MessagePacket = new MessagePacket(destination, myName);
				MessagePacket.setMessage(message);
				MessagePacket.setOptions((byte) seqNumber);
				return MessagePacket;
			}

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

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
}
