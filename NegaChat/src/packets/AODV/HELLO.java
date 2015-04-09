package packets.AODV;

import negachat.packets.*;

public class HELLO extends Packet {
	
	public static final byte TYPE = 1;
	
	public HELLO(String destination, String source) {
		super(destination, source);
	}
	
}
