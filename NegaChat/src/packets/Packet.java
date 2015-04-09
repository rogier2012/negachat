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
	
	public  final byte TYPE = 0;
	
	private byte type, options;
	private String source, destination, message, hash;

	public Packet(String destination, String source) {
		this.source = source;
		this.destination = destination;
	}
	
	public Packet(byte[] packetArray){
		setType(packetArray[0]);
		byte[] destArray = null;
		System.arraycopy(packetArray, 1, destArray, 0, 16);
		setDestination(new String(destArray));
		byte[] sourceArray = null;
		System.arraycopy(packetArray, 17, sourceArray, 0, 16);
		setSource(new String(sourceArray));
		byte[] messageArray = null;
		System.arraycopy(packetArray, 33, messageArray, 0, 16);
		setMessage(new String(messageArray));
		setOptions(packetArray[140]);
		byte[] hashArray = null;
		System.arraycopy(packetArray, 141, hashArray, 0, 16);
		setHash(new String(hashArray));
	}
	
	
	
	
//	TODO options nog verder uitwerken... hoe gaan we die setten en wat betekent het?
//	public Packet(String source, String destination, String message, byte[] options) {
//		this.setSource(source);
//		this.setDestination(destination);
//		this.setMessage(message);
//		this.setOptions(options);
//	}
	
	
//	PACKET FORMAT:
	
//	[prococol]	[destination]	[source]	[message]	[options]	[hash]
//		1 byte		16 bytes	16 bytes	128 bytes	1 byte		4 bytes

	public byte[] toByteArray() {
		byte[] type, dest, src, msg, opt, hash;
		type = new byte[]{TYPE};
		dest = getDestination().getBytes();
		src = getSource().getBytes();
		msg = getMessage().getBytes();
		
//		TODO options
		opt = new byte[8];
		
		hash = getHash().getBytes();

		byte[] bytePacket = new byte[type.length + dest.length + src.length + msg.length + opt.length + hash.length];
		
		System.arraycopy(dest, 0, bytePacket, 0, dest.length);
		System.arraycopy(src, 0, bytePacket, dest.length, src.length);
		System.arraycopy(msg, 0, bytePacket, dest.length+src.length, msg.length);
		System.arraycopy(opt, 0, bytePacket, dest.length + src.length + msg.length, opt.length);
		System.arraycopy(hash, 0, bytePacket, dest.length + src.length + msg.length + opt.length, hash.length);
		
		System.out.println("bytePacket composed");
		
		return bytePacket;
	}
	
	
	// Deze functie werkt nog niet zoals die moet
	public String makeHash() {
		int hashCode = this.hashCode();
		byte[] hash = new byte[]{
				(byte) ((hashCode >> 24) & 0xFF),
		        (byte) ((hashCode >> 16) & 0xFF),   
		        (byte) ((hashCode >> 8) & 0xFF),  
		        (byte) (hashCode & 0xFF)};
		return null;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getOptions() {
		return options;
	}

	public void setOptions(byte options) {
		this.options = options;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDestination(String destination){
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

	public String getMessage() {
		return message;
	}
	
	public void setHash(String hash){
		this.hash = hash;
	}
	
	public String getHash(){
		return hash;
	}
}
