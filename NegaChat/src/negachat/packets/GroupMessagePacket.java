package negachat.packets;

public class GroupMessagePacket extends Packet{

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
	
	
}
