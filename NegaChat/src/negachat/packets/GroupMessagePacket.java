package negachat.packets;

public class GroupMessagePacket extends Packet{

	public static final byte TYPE = 0x05;
	
	public GroupMessagePacket(String source){
		setType(TYPE);
	}

	@Override
	public Packet convertToPacket(byte[] byteArray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
