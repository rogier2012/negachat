package negachat.packets;

public class ACK_Packet extends Packet{
	
	private int packetToACK;
	
	private byte type, options;
	private String source, destination, message, hash;
	
	public static final byte TYPE = 0x06;
	
	public static final int TYPELENGTH = 1;
	public static final int SOURCE = 16;
	public static final int DESTINATION = 16;
	public static final int MESSAGE = 128;
	public static final int OPTIONS = 1;
	public static final int HASH = 4;
	public static final int TOTAL = TYPELENGTH + SOURCE + MESSAGE + OPTIONS + HASH;


	public ACK_Packet(String source, String destination, int packetToAck) {
		super(source);
		this.source = source;
		this.packetToACK = packetToACK;
		this.destination = destination;
	}

	
	public ACK_Packet(byte[] packetArray)	{
		super(packetArray);
		setType(packetArray[0]);
		byte[] sourceArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH, sourceArray, 0, SOURCE);
		setSource(new String(sourceArray));
		byte[] destArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH+SOURCE, destArray, 0, DESTINATION);
		setDestination(new String(destArray));
		byte[] messageArray = new byte[128];
		System.arraycopy(packetArray, TYPELENGTH+DESTINATION+SOURCE, messageArray, 0, MESSAGE);
		setMessage(new String(messageArray));
		setOptions(packetArray[140]);
		byte[] hashArray = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH+DESTINATION+SOURCE+MESSAGE, hashArray, 0, HASH);
		setHash(new String(hashArray));
	}
	

	@Override
	public byte[] toByteArray() {
		
		byte[] dest, src, msg, hash;
		byte type, opt;
		type = getType();
		dest = getDestination().getBytes();
		src = getSource().getBytes();
		msg = getMessage().getBytes(); 
		opt = (byte) packetToACK;	
		hash = makeHash().getBytes();

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;
		System.out.println("1: " + new String(bytePacket));
		
		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE - 1);
		System.out.println("2: " + new String(bytePacket));

		System.arraycopy(dest, 0, bytePacket, TYPELENGTH + DESTINATION, DESTINATION - 1);
		System.out.println("3: " + new String(bytePacket));

		System.arraycopy(msg, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE, MESSAGE - 1);
		System.out.println("4: " + new String(bytePacket));

		bytePacket[TOTAL - HASH - 1] = opt;
		
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE + MESSAGE + OPTIONS, HASH - 1);
		System.out.println("5: " + new String(bytePacket));

		
		System.out.println("MessagePackage bytePacket composed");
		System.out.println("length: " + bytePacket.length);
		System.out.println("bytePacket string: " + new String(bytePacket));		
		return bytePacket;
	}

	
	
	public String makeHash() {
		int hashCode = this.hashCode();
		byte[] hash = new byte[]{
				(byte) ((hashCode >> 24) & 0xFF),
		        (byte) ((hashCode >> 16) & 0xFF),   
		        (byte) ((hashCode >> 8) & 0xFF),  
		        (byte) (hashCode & 0xFF)};
		return new String(hash);
	}
	
	
	
	
	
	
	

	public int getPacketToACK() {
		return packetToACK;
	}


	public void setPacketToACK(int packetToACK) {
		this.packetToACK = packetToACK;
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


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}	
}
