package negachat.packets.AODV;

import negachat.packets.Packet;

public class RERR extends Packet {
	
	public static final byte TYPE = 4;
	
	/*
	 * Constructors
	 */
	
	public RERR(String source)	{
		super(source);
		this.setSource(source);
	}
	
	public RERR(byte[] data){
		super(data);
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
