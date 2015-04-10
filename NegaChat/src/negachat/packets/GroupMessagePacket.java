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
			byte[] sourceArray = null;
			System.arraycopy(data, TYPELENGTH, sourceArray, 0, SOURCE);
			setSource(new String(sourceArray));
			byte[] messageArray = null;
			System.arraycopy(data, TYPELENGTH + SOURCE, messageArray, 0, MESSAGE);
			setMessage(new String(messageArray));
			setOptions(data[TYPELENGTH + SOURCE + MESSAGE]);
			byte[] hashArray = null;
			System.arraycopy(data, TYPELENGTH + SOURCE + MESSAGE + OPTIONS, hashArray, 0, HASH);
			setHash(new String(hashArray));
	}

	@Override
	public byte[] toByteArray() {
		byte[] type, dest, src, msg, opt, hash;
		type = new byte[]{getType()};
		src = getSource().getBytes();
		msg = getMessage().getBytes(); 
		opt = new byte[8];		
		hash = makeHash().getBytes();

		byte[] bytePacket = new byte[TOTAL];
		
		System.arraycopy(type, 0, bytePacket, 0, TYPELENGTH);
		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE);
		System.arraycopy(msg, 0, bytePacket, TYPELENGTH+SOURCE, MESSAGE);
		System.arraycopy(opt, 0, bytePacket, TYPELENGTH+SOURCE+MESSAGE, OPTIONS);
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH+SOURCE+MESSAGE+OPTIONS, HASH);
		
		System.out.println("bytePacket composed");
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
