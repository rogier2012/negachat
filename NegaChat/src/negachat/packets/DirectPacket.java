package negachat.packets;

public interface DirectPacket {
	public String getDestination();
	
	public byte[] toByteArray();
}
