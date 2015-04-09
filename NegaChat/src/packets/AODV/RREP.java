package packets.AODV;

import negachat.packets.MessagePacket;

public class RREP extends MessagePacket {

	public static final byte TYPE = 3;
	
	public RREP(String destination, String source) {
		super(destination, source);
		// TODO Auto-generated constructor stub
	}
	
}
