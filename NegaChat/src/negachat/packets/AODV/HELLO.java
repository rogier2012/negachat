package negachat.packets.AODV;

import negachat.packets.Packet;

public class HELLO extends Packet {
	
	/*
	 * Constants
	 */
	
	public static final byte TYPE = 1;
	
	/*
	 * Instance Variables
	 */
	
	private int hopCount;
	private int identifier;
	
	/*
	 * Constructors
	 */
	
	public HELLO(String source) {
		super(source);
		this.setType(TYPE);
		this.hopCount = 0;
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Getters and Setters
	 */
	
	public int getHopCount() {
		return hopCount;
	}

	public void setHopCount(int hopCount) {
		this.hopCount = hopCount;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
}
