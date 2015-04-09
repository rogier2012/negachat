package packets.AODV;

import negachat.packets.MessagePacket;

public class RREQ extends MessagePacket {
	
	public static final byte TYPE = 2;
	
	private int lifeSpan;
	private int identifier;
	
	public RREQ(String destination, String source, int lifeSpan, int identifier) {
		super(destination, source);
		this.lifeSpan = lifeSpan;
		this.identifier = identifier;
	}
	
}
