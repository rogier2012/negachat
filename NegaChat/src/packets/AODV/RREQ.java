package packets.AODV;

import packets.Packet;

public class RREQ extends Packet {
	
	public RREQ(String destination, String source) {
		super(destination, source);
		// TODO Auto-generated constructor stub
	}
	private int lifeSpan;
	private int identifier;
	
}
