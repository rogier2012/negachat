package negachat.packets.AODV;

import java.security.PublicKey;

import negachat.client.RoutingTable;
import negachat.encryption.AssymetricEncrypter;
import negachat.packets.Packet;

/*
 * HELLO packets are broadcasted over the network to notify other nodes of its existence in the network.
 * 
 * Lay-Out: 1 + 16 + 4 + 1 + 162 = 184
 * [Type][Source][myIP][hopCount][Publickey]
 * 
 */
public class HELLO extends Packet {
	
	/*
	 * Constants
	 */
	
	// Maximum travel distance for a HELLO packet
	public static final int MAXHOPS = 4;
	// Type ID of packet
	public static final byte TYPE = 1;
	
	public static final int SOURCELENGTH = 16;
	
	public static final int IPLENGTH = 4;
	
	public static final int HOPCOUNTLENGTH =1;
	
	public static final int PUBLICKEYLENGTH = 162;
	
	public static final int TOTAL = 1 + SOURCELENGTH + IPLENGTH + HOPCOUNTLENGTH + PUBLICKEYLENGTH;
	
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
		temp = new byte[IPLENGTH];
		System.arraycopy(byteArray, SOURCEINDEX + SOURCELENGTH, temp, 0, IPLENGTH);
		
		this.myIP = temp;
		this.hopCount = byteArray[SOURCEINDEX + SOURCELENGTH + IPLENGTH];
		
		temp = new byte[PUBLICKEYLENGTH];
		System.arraycopy(byteArray, SOURCEINDEX + SOURCELENGTH + IPLENGTH + HOPCOUNTLENGTH, temp, 0, PUBLICKEYLENGTH);
		this.setPublickey(AssymetricEncrypter.unwrapKey(temp));
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[TOTAL];
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
