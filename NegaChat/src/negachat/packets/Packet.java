package negachat.packets;

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
	
	public Packet(String source)	{
		this.source = source;
	}
	
	public Packet(byte[] byteArray){
		
	}
	
	/*
	 * Queries
	 */
	
	public abstract byte[] toByteArray();

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
