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
	}
	
	public byte[] composePacket() {
		byte[] type, dest, src, msg, opt, hash;
		type = new byte[]{TYPE};
		dest = getDestination();
		src = getSource();
		msg = getMessage();
		
//		TODO options
		opt = new byte[8];
		
		hash = getHash();

		byte[] bytePacket = new byte[type.length + dest.length + src.length + msg.length + opt.length + hash.length];
		
		System.arraycopy(dest, 0, bytePacket, 0, dest.length);
		System.arraycopy(src, 0, bytePacket, dest.length, src.length);
		System.arraycopy(msg, 0, bytePacket, dest.length+src.length, msg.length);
		System.arraycopy(opt, 0, bytePacket, dest.length + src.length + msg.length, opt.length);
		System.arraycopy(hash, 0, bytePacket, dest.length + src.length + msg.length + opt.length, hash.length);
		
		System.out.println("bytePacket composed");
		
		return bytePacket;
	}
	
	private byte[] getHash() {
		int hashCode = this.hashCode();
		byte[] hash = new byte[]{
				(byte) ((hashCode >> 24) & 0xFF),
		        (byte) ((hashCode >> 16) & 0xFF),   
		        (byte) ((hashCode >> 8) & 0xFF),  
		        (byte) (hashCode & 0xFF)};
		return hash;
	}
	
	
	
}
