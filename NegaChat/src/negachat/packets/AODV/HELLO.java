package negachat.packets.AODV;

import java.net.InetAddress;
import java.net.UnknownHostException;

import negachat.messages.SendingMultiSocket;
import negachat.packets.Packet;

/*
 * HELLO packets are broadcasted over the network to notify other nodes of its existence in the network.
 * 
 * Lay-Out:
 * [Type][Source][myIP]
 * 
 */
public class HELLO extends Packet {
	
	/*
	 * Constants
	 */
	
	// Type ID of packet
	public static final byte TYPE = 1;
	public static final int SOURCELENGTH = 16;
	
	private byte[] myIP;
	
	public HELLO(String source) {
		super();
		try {
			this.myIP = InetAddress.getLocalHost().getAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setType(TYPE);
	}
	
	public HELLO(byte[] byteArray)	{
		super(byteArray);
		this.setType(TYPE);
		byte[] temp = new byte[SOURCELENGTH];
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(this.removePadding(new String(temp)));
		temp = new byte[4];
		System.arraycopy(byteArray, SOURCEINDEX + SOURCELENGTH, temp, 0, 4);
		this.myIP = temp;
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[21];
		byte[] source = fillNickname(this.getSource());
		byte type = this.getType();
		
		result[0] = type;
		
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		System.arraycopy(this.myIP, 0, result, SOURCEINDEX + SOURCELENGTH, 4);
		
		return result;
	}
	
	public void send(Packet toSend) {
		SendingMultiSocket sock = new SendingMultiSocket();
		sock.send(toSend);
	}
}
