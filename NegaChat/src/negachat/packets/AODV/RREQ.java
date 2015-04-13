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
	public static final int LIFESPANLENGTH = 1;
	public static final int IDENTIFIERLENGTH = 1;
	public static final int DESTINATIONLENGTH = 16;
	
	// Index of data
	public static final int DESTINATIONINDEX = SOURCEINDEX + SOURCELENGTH;
	public static final int LIFESPANINDEX = DESTINATIONINDEX + DESTINATIONLENGTH;
	public static final int IDENTIFIERINDEX = LIFESPANINDEX + LIFESPANLENGTH;
	
	/*
	 * Instance Variables
	 */
	
	private byte lifeSpan;
	private byte identifier;

	private String destination;
	
	/*
	 * Constructors
	 */
	
	public RREQ(String destination,  byte lifeSpan, byte identifier) {
		super();
		this.destination = destination;
		this.lifeSpan = lifeSpan;
		this.identifier = identifier;
		this.setType(TYPE);
	}
	
	public RREQ(byte[] byteArray)	{
		super(byteArray);
		this.setType(TYPE);
		
		byte[] temp = new byte[SOURCELENGTH];
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(new String(temp));
		
		temp = new byte[DESTINATIONINDEX];
		System.arraycopy(byteArray, DESTINATIONINDEX, temp, 0, DESTINATIONLENGTH);
		this.setDestination(new String(temp));
		
		this.lifeSpan = byteArray[LIFESPANINDEX];
		this.identifier = byteArray[IDENTIFIERINDEX];
		
	}

	/*
	 * Getters and Setters
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] source = this.fillNickname(this.getSource());
		byte[] destination = this.fillNickname(this.getDestination());
		byte[] lifeSpan = new byte[]{this.lifeSpan};
		byte[] identifier = new byte[]{this.identifier};
		
		byte[] result = new byte[IDENTIFIERINDEX + IDENTIFIERLENGTH];
		result[TYPEINDEX] = this.getType();
		result[IDENTIFIERINDEX] = this.getIdentifier();
		result[LIFESPANINDEX] = this.getLifeSpan();
		
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		System.arraycopy(destination, 0, result, DESTINATIONINDEX, DESTINATIONLENGTH);
		System.arraycopy(lifeSpan, 0, result, LIFESPANINDEX, LIFESPANLENGTH);
		System.arraycopy(identifier, 0, result, IDENTIFIERINDEX, IDENTIFIERLENGTH);
		
		return result;
	}
	
	/*
	 * Getters and Setters
	 */

	public byte getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(byte lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public byte getIdentifier() {
		return identifier;
	}

	public void setIdentifier(byte identifier) {
		this.identifier = identifier;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}