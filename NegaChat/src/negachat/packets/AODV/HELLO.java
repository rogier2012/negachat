package negachat.packets.AODV;

import negachat.messages.SendingMultiSocket;
import negachat.packets.Packet;

/*
 * HELLO packets are broadcasted over the network to notify other nodes of its existence in the network.
 * 
 * Lay-Out:
 * [Type][Source]
 * 
 */
public class HELLO extends Packet {
	
	/*
	 * Constants
	 */
	
	// Type ID of packet
	public static final byte TYPE = 1;
	public static final int SOURCELENGTH = 16;
	
	public HELLO(String source) {
		super();
		this.setType(TYPE);
	}
	
	public HELLO(byte[] byteArray)	{
		super(byteArray);
		this.setType(TYPE);
		byte[] temp = new byte[SOURCELENGTH];
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(new String(temp));
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[17];
		byte[] source = this.fillNickname(this.getSource());
		byte type = this.getType();
		
		result[0] = type;
		
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		
		return result;
	}
	
	public void send(Packet toSend) {
		SendingMultiSocket sock = new SendingMultiSocket();
		sock.send(toSend);
	}
}
