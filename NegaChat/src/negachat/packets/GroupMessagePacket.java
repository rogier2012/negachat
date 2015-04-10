package negachat.packets;

public class GroupMessagePacket extends Packet{
	
	private byte type, options;
	private String source, message, hash;


	public static final byte TYPE = 0x05;
	
	public GroupMessagePacket(String source){
		super(source);
		setType(TYPE);
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
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
