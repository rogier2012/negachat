package packets;

public class Packet {
	
	/*
	 * AODV PACKETS TYPE IDENTIFIER BYTES:
	 * 
	 * 00000000 - Normal
	 * 00000001 - HELLO
	 * 00000010 - RREQ
	 * 00000011 - RREP
	 * 00000100 - RERR
	 * 
	 */
	
	private byte[] source, destination, message, options, hash;

	public Packet(String destination, String source) {
		this.setSource(source);
		this.setDestination(destination);
	}
	
	
//	TODO options nog verder uitwerken... hoe gaan we die setten en wat betekent het?
//	public Packet(String source, String destination, String message, byte[] options) {
//		this.setSource(source);
//		this.setDestination(destination);
//		this.setMessage(message);
//		this.setOptions(options);
//	}
	
	public byte[] composePacket() {
		byte[] dest, src, msg, opt, hash;
		dest = getDestination();
		src = getSource();
		msg = getMessage();
		
//		TODO options
		opt = new byte[8];
		
		hash = getHash();

		byte[] bytePacket = new byte[dest.length + src.length + msg.length + opt.length + hash.length];
		
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
	

	public byte[] getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination.getBytes();
	}
	public byte[] getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source.getBytes();
	}
	public byte[] getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message.getBytes();
	}

	public byte[] getOptions() {
		return options;
	}

	public void setOptions(byte[] options) {
		this.options = options;
	}
}
