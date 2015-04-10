package negachat.packets.AODV;

import negachat.packets.Packet;

public class RERR extends Packet {
	
	/*
	 * Constructors
	 */
	
	public RERR(String source)	{
		super(source);
		this.setSource(source);
	}

	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
