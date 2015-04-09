package packets.AODV;

import packets.Packet;

public class RREQ extends Packet {
	
	public RREQ(String destination, String source, String message) {
		super(destination, source, message);
		// TODO Auto-generated constructor stub
	}
	private int lifeSpan;
	private int identifier;
	
}
