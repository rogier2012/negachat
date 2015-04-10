package negachat.packets;

public class GroupMessagePacket extends MessagePacket{

	public static final byte TYPE = 0x05;
	
	public GroupMessagePacket(String source){
		super(source);
		setType(TYPE);
	}
	
	
}
