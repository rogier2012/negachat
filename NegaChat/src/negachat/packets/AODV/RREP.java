package negachat.packets.AODV;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;

public class RREP extends Packet {
	
	/*
	 * Constants
	 */

	public static final byte TYPE = 3;
	
	/*
	 * Constructors
	 */
	
	public RREP(String destination, String source) {
		super(source);
		this.setType(TYPE);
	}
	
	public RREP(byte[]data){
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