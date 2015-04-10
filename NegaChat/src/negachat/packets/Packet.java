package negachat.packets;

public abstract class Packet {
	
	/*
	 * AODV PACKETS TYPE IDENTIFIER BYTES:
	 * 
	 * 00000000 - Normal
	 * 00000001 - HELLO
	 * 00000010 - RREQ
	 * 00000011 - RREP
	 * 00000100 - RERR
	 * 00000101 - GroupMessage
	 */
	
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
