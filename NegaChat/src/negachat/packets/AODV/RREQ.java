package negachat.packets.AODV;

import negachat.packets.Packet;

public class RREQ extends Packet {
	
	/*
	 * Constants
	 */
	
	public static final byte TYPE = 2;
	
	/*
	 * Intance Variables
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