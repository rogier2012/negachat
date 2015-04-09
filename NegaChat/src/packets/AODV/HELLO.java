package packets.AODV;

import negachat.packets.*;

public class HELLO extends MessagePacket {
	
	public static final byte TYPE = 1;
	
	public HELLO(String destination, String source) {
		super(destination, source);
		this.setType(TYPE);
	}
	
}
