package negachat.packets;

public class ACK_Packet extends Packet{
	
	private int packetToACK;
	
	private byte options;
	private String destination, message, hash;
	
	public static final byte TYPE = 0x06;
	
	public static final int TYPELENGTH = 1;
	public static final int SOURCE = 16;
	public static final int DESTINATION = 16;
	public static final int MESSAGE = 128;
	public static final int OPTIONS = 1;
	public static final int HASH = 4;
	public static final int TOTAL = TYPELENGTH + SOURCE + MESSAGE + OPTIONS + HASH;


	public ACK_Packet(String source, int packetToACK) {
		super(source);
		this.packetToACK = packetToACK;
	}

	
	public ACK_Packet(byte[] packetArray)	{
		super(packetArray);
		setType(packetArray[0]);
		byte[] sourceArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH, sourceArray, 0, SOURCE);
		String temp = new String(sourceArray);
		String source = temp.split("=")[0];
		setSource(source);
		
		byte[] destArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH+SOURCE, destArray, 0, DESTINATION);
		String temp2 = new String(destArray);
		String destination = temp2.split("=")[0];
		setDestination(destination);
		
		byte[] messageArray = new byte[128];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE, messageArray, 0, MESSAGE);
		String temp3 = new String(messageArray);
		String message = temp3.split("=")[0];
		setMessage(message);
		
		setOptions(packetArray[TYPELENGTH + SOURCE + MESSAGE]);
		byte[] hashArray = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE + MESSAGE + OPTIONS, hashArray, 0, HASH);
		setHash(new String(hashArray));
	}
	

	@Override
	public byte[] toByteArray() {
		
		byte[] dest, src, msg, hash;
		byte type, opt;
		type = TYPE;
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
		hash = "";
		
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

	public byte getOptions() {
		return options;
	}


	public void setOptions(byte options) {
		this.options = options;
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
