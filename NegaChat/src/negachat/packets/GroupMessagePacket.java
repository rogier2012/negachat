package negachat.packets;

//10/4 PACKET FORMAT:

//	[type]	[source] 	[message]	[options]	[hash]
//	1 byte	16 bytes	128 bytes	1 byte		4 bytes

public class GroupMessagePacket extends Packet{
	
	private byte type, options;
	private String source, message, hash;


	public static final byte TYPE = 0x05;
	
	public static final int TYPELENGTH = 1;
	public static final int SOURCE = 16;
	public static final int MESSAGE = 128;
	public static final int OPTIONS = 1;
	public static final int HASH = 4;
	public static final int TOTAL = TYPELENGTH + SOURCE + MESSAGE + OPTIONS + HASH;
	
	public GroupMessagePacket(String source){
		super(source);
		setSource(source);
		setType(TYPE);
	}
	
	public GroupMessagePacket(byte[] data){
			super(data);
			setType(data[0]);
			byte[] sourceArray = new byte[16];
			System.arraycopy(data, TYPELENGTH, sourceArray, 0, SOURCE);
			String temp = new String(sourceArray);
			String source = temp.split("=")[0];
			setSource(source);
			
			byte[] messageArray = new byte[128];
			System.arraycopy(data, TYPELENGTH + SOURCE, messageArray, 0, MESSAGE);
			String temp2 = new String(messageArray);
			String message = temp2.split("=")[0];
			setMessage(message);
			
			setOptions(data[TYPELENGTH + SOURCE + MESSAGE]);
			byte[] hashArray = new byte[4];
			System.arraycopy(data, TYPELENGTH + SOURCE + MESSAGE + OPTIONS, hashArray, 0, HASH);
			setHash(new String(hashArray));
	}

	@Override
	public byte[] toByteArray() {		
		byte[] src, msg, hash;
		byte type, opt;
		type = getType();
		src = getSource().getBytes();
		msg = getMessage().getBytes(); 
		opt = (byte) 0;	
		hash = makeHash().getBytes();

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;
		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE - 1);
		System.arraycopy(msg, 0, bytePacket, TYPELENGTH+SOURCE, MESSAGE - 1);
		bytePacket[TOTAL - HASH - 1] = opt;
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH+SOURCE+MESSAGE+OPTIONS, HASH - 1);
		
		System.out.println("GroupMessagePackage bytePacket composed");
		System.out.println("length: " + bytePacket.length);
		System.out.println("bytePacket string: " + new String(bytePacket));
		
		return bytePacket;
	}
	
	public String makeHash() {
		hash =  "";
		setHash("0x00");
		int hashCode = this.hashCode();
		byte[] hash = new byte[]{
				(byte) ((hashCode >> 24) & 0xFF),
		        (byte) ((hashCode >> 16) & 0xFF),   
		        (byte) ((hashCode >> 8) & 0xFF),  
		        (byte) (hashCode & 0xFF)};
		return new String(hash);
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
