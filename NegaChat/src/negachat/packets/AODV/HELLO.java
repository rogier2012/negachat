package negachat.packets.AODV;

import negachat.packets.Packet;

/*
 * HELLO packets are broadcasted over the network to notify other nodes of its existence in the network.
 * 
 * Lay-Out:
 * [Type][Source][Hop count][Identifier]
 * 
 */
public class HELLO extends Packet {
	
	/*
	 * Constants
	 */
	
	// Type ID of packet
	public static final byte TYPE = 1;
	
	// How many Bytes are reserved for this data
	public static final int TYPELENGTH = 1;
	public static final int HOPCOUNTLENGTH = 1;
	public static final int IDENTIFIERLENGTH = 1;
	
	// Index of data
	public static final int HOPCOUNTINDEX = TYPELENGTH;
	public static final int IDENTIFIERINDEX = HOPCOUNTINDEX + HOPCOUNTLENGTH;
	
	/*
	 * Instance Variables
	 */
	
	private byte hopCount;
	private byte identifier;
	
	/*
	 * Constructors
	 */
	
	public HELLO(String source, byte identifier) {
		super(source);
		this.setType(TYPE);
		this.hopCount = 0;
		this.identifier = identifier;
	}
	
	public HELLO(byte[] byteArray)	{
		super(byteArray);
		this.setType(TYPE);
		this.hopCount = byteArray[HOPCOUNTINDEX];
		this.identifier = byteArray[IDENTIFIERINDEX];
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		return new byte[]{this.getType(), this.hopCount, this.identifier};
	}

	/*
	 * Getters and Setters
	 */
	
	public byte getHopCount() {
		return hopCount;
	}

	public void setHopCount(byte hopCount) {
		this.hopCount = hopCount;
	}

	public byte getIdentifier() {
		return identifier;
	}

	public void setIdentifier(byte identifier) {
		this.identifier = identifier;
	}
	
}
