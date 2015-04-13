package negachat.packets;

import negachat.view.NegaView;

public class ACK_Packet extends Packet{
	
	private int packetToACK;
	
	private byte options;
	private String  destination, message, hash;
	
	public static final byte TYPE = 0x06;
	
	public static final int TYPELENGTH = 1;
	public static final int SOURCE = 16;
	public static final int DESTINATION = 16;
	public static final int OPTIONS = 1;
	public static final int HASH = 4;
	public static final int TOTAL = TYPELENGTH + SOURCE + OPTIONS + HASH;


	public ACK_Packet(int packetToACK) {
		super();
		this.setSource(NegaView.getMyName());
		this.packetToACK = packetToACK;
	}

	
	public ACK_Packet(byte[] packetArray)	{
		super(packetArray);
		this.setType(packetArray[0]);
		byte[] sourceArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH, sourceArray, 0, SOURCE);
		this.setSource(this.removePadding(new String(sourceArray)));
		
		byte[] destArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH+SOURCE, destArray, 0, DESTINATION);
		this.setDestination(this.removePadding(new String(destArray)));
		
		this.setOptions(packetArray[TYPELENGTH + SOURCE]);
		byte[] hashArray = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE + OPTIONS, hashArray, 0, HASH);
		this.setHash(new String(hashArray));
	}
	

	@Override
	public byte[] toByteArray() {
		byte[] bytePacket = new byte[TOTAL];
			
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
