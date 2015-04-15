package negachat.packets.AODV;

import java.security.PublicKey;

import negachat.client.RoutingTable;
import negachat.encryption.AssymetricEncrypter;
import negachat.packets.Packet;

/*
 * HELLO packets are broadcasted over the network to notify other nodes of its existence in the network.
 * 
 * Lay-Out: 1 + 16 + 4 + 1 + 162 = 190
 * [Type][Source][myIP][hopCount][Publickey]
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
	
	public static final int PUBLICKEYLENGTH = 162;
	
	/*
	 * Instance Variables
	 */
	
	private byte[] myIP;
	
	private byte hopCount;
	
	private PublicKey publickey;
	
	

	public HELLO(String source, RoutingTable table, PublicKey publickey) {
		this.myIP = table.getMyIP();
		this.setType(TYPE);
		hopCount = 0;
		this.publickey = publickey;
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
		this.hopCount = byteArray[21];
		
		temp = new byte[PUBLICKEYLENGTH];
		System.arraycopy(byteArray, 22, temp, 0, PUBLICKEYLENGTH);
		this.setPublickey(AssymetricEncrypter.unwrapKey(temp));
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[184];
		byte[] source = fillNickname(this.getSource());
		byte[] publickey = this.getPublickey().getEncoded();
		byte type = this.getType();
		
		result[0] = type;
		
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		System.arraycopy(this.myIP, 0, result, SOURCEINDEX + SOURCELENGTH, 4);
		
		result[21] = hopCount;
		System.arraycopy(publickey, 0, result, 22, PUBLICKEYLENGTH);
		
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
	
	public PublicKey getPublickey() {
		return publickey;
	}

	public void setPublickey(PublicKey publickey) {
		this.publickey = publickey;
	}
}
