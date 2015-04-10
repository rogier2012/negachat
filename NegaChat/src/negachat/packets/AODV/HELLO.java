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
	
	public static final byte TYPE = 1;
	
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
