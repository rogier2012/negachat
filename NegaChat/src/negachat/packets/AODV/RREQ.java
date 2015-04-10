package negachat.packets.AODV;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;

public class RREQ extends Packet {
	
	public static final byte TYPE = 2;
	
	private int lifeSpan;
	private int identifier;
	
	public RREQ(String destination, String source, int lifeSpan, int identifier) {
		this.lifeSpan = lifeSpan;
		this.identifier = identifier;
		this.setType(TYPE);
	}

	@Override
	public Packet convertToPacket(byte[] byteArray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
}