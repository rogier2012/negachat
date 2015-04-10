package negachat.packets.AODV;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;

public class RREP extends Packet {

	public static final byte TYPE = 3;
	
	public RREP(String destination, String source) {
		super(source);
		this.setType(TYPE);
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
}