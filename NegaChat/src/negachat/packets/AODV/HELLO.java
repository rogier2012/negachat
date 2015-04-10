package negachat.packets.AODV;

import negachat.packets.MessagePacket;

public class HELLO extends MessagePacket {
	
	public static final byte TYPE = 1;
	
	private int hopCount;
	private int identifier;
	
	public HELLO(String source) {
		super(source);
		this.setType(TYPE);
		this.hopCount = 0;
	}
	
}
