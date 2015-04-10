package negachat.packets;

public class GroupMessagePacket extends MessagePacket{

	public GroupMessagePacket(String source){
		super(source);
		setType();
	}
}
