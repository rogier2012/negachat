package packets.AODV;

import negachat.packets.Packet;

public class RREQ extends Packet {
	
	public static final byte TYPE = 2;
	
	private int lifeSpan;
	private int identifier;
	
	public RREQ(String destination, String source, int lifeSpan, int identifier) {
		super(destination, source);
		this.lifeSpan = lifeSpan;
		this.identifier = identifier;
		this.setType(TYPE);
	}
	
}
