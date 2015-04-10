package negachat.packets.AODV;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;

public class HELLO extends Packet {
	
	public static final byte TYPE = 1;
	
	private int hopCount;
	private int identifier;
	
	public HELLO(String source) {
		super(source);
		this.setType(TYPE);
		this.hopCount = 0;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
