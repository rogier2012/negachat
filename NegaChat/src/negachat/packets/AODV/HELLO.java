package negachat.packets.AODV;

import negachat.client.RoutingTable;
import negachat.messages.SendingMultiSocket;
import negachat.packets.Packet;

/*
 * HELLO packets are broadcasted over the network to notify other nodes of its existence in the network.
 * 
 * Lay-Out:
 * [Type][Source][myIP][hopCount]
 * 
 */
public class HELLO extends Packet {
	
	/*
	 * Constants
	 */
	
	// Maximum travel distance for a HELLO packet
	public static final byte MAXHOPS = 4;
	// Type ID of packet
	public static final byte TYPE = 1;
	
	public static final int SOURCELENGTH = 16;
	
	/*
	 * Instance Variables
	 */
	
	private byte[] myIP;
	
	private byte hopCount;
	
	public HELLO(String source, RoutingTable table) {
		this.myIP = table.getMyIP();
		this.setType(TYPE);
		hopCount = 0;
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
		this.hopCount = byteArray[byteArray.length - 1];
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[22];
		byte[] source = fillNickname(this.getSource());
		byte type = this.getType();
		
		result[0] = type;
		
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		System.arraycopy(this.myIP, 0, result, SOURCEINDEX + SOURCELENGTH, 4);
		
		result[result.length - 1] = hopCount;
		return result;
	}

	public byte[] getMyIP() {
		return myIP;
	}

	public void setMyIP(byte[] myIP) {
		this.myIP = myIP;
	}

	public byte getHopCount() {
		return hopCount;
	}

	public void setHopCount(byte hopCount) {
		this.hopCount = hopCount;
	}
}
