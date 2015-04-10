package negachat.packets.AODV;

import negachat.packets.Packet;

/*
 * RREQ are route requests that are flooded over the network until the destination has been found.
 * 
 * Lay-Out:
 * [Type][Source][Destination][Lifespan][Identifier]
 */

public class RREQ extends Packet {
	
	/*
	 * Constants
	 */
	
	// Type ID of packet
	public static final byte TYPE = 2;
	
	// How many Bytes are reserved for this data
	public static final
	public static final
	public static final
	public static final
	
	
	/*
	 * Instance Variables
	 */
	
	private int lifeSpan;
	private int identifier;
	
	/*
	 * Constructors
	 */
	
	public RREQ(String destination, String source, int lifeSpan, int identifier) {
		super(source);
		this.lifeSpan = lifeSpan;
		this.identifier = identifier;
		this.setType(TYPE);
	}

	/*
	 * Getters and Setters
	 */
	
	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
}