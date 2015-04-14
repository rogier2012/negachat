package negachat.packets.AODV;

import negachat.packets.DirectPacket;
import negachat.packets.Packet;
import negachat.view.NegaView;

/*
 * Route replies are sent back to the node that requested the route.
 * 
 * Lay-Out:
 * [Type][Source][Destination][Hopcount][LastSource]
 */

public class RREP extends Packet implements DirectPacket {
	
	/*
	 * Constants
	 */
	
	// Type ID of packet
	public static final byte TYPE = 3;
	
	// How many Bytes are reserved for this data
	public static final int DESTINATIONLENGTH = 16;
	public static final int HOPCOUNTLENGTH = 1;
	public static final int LASTSOURCELENGTH = 16;
	
	// Index of data
	public static final int DESTINATIONINDEX = SOURCEINDEX + SOURCELENGTH;
	public static final int HOPCOUNTINDEX = DESTINATIONINDEX + DESTINATIONLENGTH;
	public static final int LASTSOURCEINDEX = HOPCOUNTINDEX + LASTSOURCELENGTH;
	
	/*
	 * Instance Variables
	 */
	
	private String destination;
	private String lastSource;
	private byte hopcount;
	
	/*
	 * Constructors
	 */
	
	public RREP(String destination, String source) {
		super();
		this.setType(TYPE);
		this.destination = destination;
		hopcount = 0;
		this.setLastSource(NegaView.getMyName());
		
	}
	
	public RREP(byte[] byteArray)	{
		super(byteArray);

		this.setType(byteArray[TYPEINDEX]);
		
		byte[] temp = new byte[SOURCELENGTH];
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(this.removePadding(new String(temp)));
		
		temp = new byte[DESTINATIONLENGTH]; 
		System.arraycopy(byteArray, DESTINATIONINDEX, temp, 0, DESTINATIONLENGTH);
		this.setDestination(this.removePadding(new String(temp)));
		this.setHopcount(byteArray[HOPCOUNTINDEX]);
		temp = new byte[LASTSOURCELENGTH]; 
		System.arraycopy(byteArray, LASTSOURCEINDEX, temp, 0, LASTSOURCELENGTH);
		this.setLastSource(this.removePadding(new String(temp)));
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[DESTINATIONINDEX + DESTINATIONLENGTH + HOPCOUNTLENGTH + LASTSOURCELENGTH];
		result[0] = this.getType();
		
		byte[] source = this.fillNickname(this.getSource());
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		
		byte[] destination = this.fillNickname(this.getDestination());
		System.arraycopy(destination, 0, result, DESTINATIONINDEX, DESTINATIONLENGTH);
		result[HOPCOUNTINDEX] = this.getHopcount();
		
		byte[] lastSource = this.fillNickname(this.getLastSource());
		System.arraycopy(lastSource, 0, result, LASTSOURCEINDEX, LASTSOURCELENGTH);
		return result;
	}
	
	/*
	 * Getters and Setters
	 */
	
	@Override
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public byte getHopcount() {
		return hopcount;
	}

	public void setHopcount(byte hopcount) {
		this.hopcount = hopcount;
	}
	
	public String getLastSource() {
		return lastSource;
	}

	public void setLastSource(String lastSource) {
		this.lastSource = lastSource;
	}
}
