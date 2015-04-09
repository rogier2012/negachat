package packets.AODV;

import negachat.packets.*;

public class HELLO extends MessagePacket {
	
	public static final byte TYPE = 1;
	
	public HELLO(String source) {
		super(source);
		this.setType(TYPE);
	}
	
}
