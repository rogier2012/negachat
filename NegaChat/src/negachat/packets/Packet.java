package negachat.packets;

import negachat.view.NegaView;

public abstract class Packet {
	
	/*
	 * AODV PACKETS TYPE IDENTIFIER BYTES:
	 * 
	 * 00000000 - MessagePacket			0
	 * 00000001 - HELLO					1
	 * 00000010 - RREQ					2
	 * 00000011 - RREP					3
	 * 00000100 - RERR					4
	 * 00000101 - GroupMessage			5
	 * 00000110	- ACK-Packet			6
	 */
	
	/*
	 * Constants
	 */
	
	// How many bytes are reserved for the source header
	public static final int TYPELENGTH = 1;
	public static final int SOURCELENGTH = 16;
	public static final int MAX_MESSAGE_LENGTH = 128;
	public static final int MAX_NAME_LENGTH = 16;
	
	// Index of data
	public static final int TYPEINDEX = 0;
	public static final int SOURCEINDEX = TYPEINDEX + TYPELENGTH;
	
	/*
	 * Instance Variables
	 */
	
	private byte type;
	private String source;
	
	/*
	 * Constructors
	 */
	
	public Packet()	{
		this.source = NegaView.getMyName();
	}
	
	public Packet(byte[] byteArray){
		
	}
	
	/*
	 * Queries
	 */
	
	public abstract byte[] toByteArray();
	
	public byte[] fillNickname(String nickname) {
		String myName = nickname;
		if (myName.length() < MAX_NAME_LENGTH) {
			int length = myName.length();
			int todo = MAX_NAME_LENGTH - length;
			for (int i = todo; i > 0; i--) {
				myName += "=";
			}
		}
		return myName.getBytes();
	}
	
	public byte[] fillMessage(String message) {
		if (message.length() < MAX_MESSAGE_LENGTH) {
			int length = message.length();
			int todo = MAX_MESSAGE_LENGTH - length;
			for (int i = todo; i > 0; i--) {
				message += "=";
			}
		}
		return message.getBytes();
	}
	
	
	public String removePadding(String paddedString){
		return (paddedString.split("==")[0]);
	}
		
//		if (message.length() < MAX_MESSAGE_LENGTH) {
//			int length = message.length();
//			int todo = MAX_MESSAGE_LENGTH - length;
//			for (int i = todo; i > 0; i--) {
//				message += "=";
//			}
//		}
//		
//		if (!destination.equals("all")) {
//			if (destination.length() < MAX_NAME_LENGTH) { 
//				int length = destination.length();
//				int todo = MAX_MESSAGE_LENGTH - length;
//				for (int i = todo; i > 0; i--) {
//					destination += "=";
//				}
//			}
//		}
//	}


	/*
	 * Getters and Setters
	 */
	
	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
