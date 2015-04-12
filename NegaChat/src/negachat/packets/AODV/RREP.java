package negachat.packets.AODV;

import negachat.packets.DirectPacket;
import negachat.packets.Packet;

/*
 * Route replies are sent back to the node that requested the route.
 * 
 * Lay-Out:
 * [Type][Source][Destination]
 */

public class RREP extends Packet implements DirectPacket {
	
	/*
	 * Constants
	 */
	
	// Type ID of packet
	public static final byte TYPE = 3;
	
	// How many Bytes are reserved for this data
	public static final int DESTINATIONLENGTH = 16;
	
	// Index of data
	public static final int DESTINATIONINDEX = SOURCEINDEX + SOURCELENGTH;
	
	/*
	 * Instance Variables
	 */
	
	private String destination;
	
	/*
	 * Constructors
	 */
	
	public RREP(String destination, String source) {
		super(source);
		this.setType(TYPE);
		this.destination = destination;
	}
	
	public RREP(byte[] byteArray)	{
		super(byteArray);

		this.setType(byteArray[TYPEINDEX]);
		
		byte[] temp = new byte[SOURCELENGTH];
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(new String(temp));
		
		temp = new byte[DESTINATIONLENGTH]; 
		System.arraycopy(byteArray, DESTINATIONINDEX, temp, 0, DESTINATIONLENGTH);
		this.setDestination(new String(temp));
		
	}
	
	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[DESTINATIONINDEX + DESTINATIONLENGTH];
		result[0] = this.getType();
		
		byte[] source = this.getSource().getBytes();
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		
		byte[] destination = this.getDestination().getBytes();
		System.arraycopy(destination, 0, result, DESTINATIONINDEX, DESTINATIONLENGTH);
		
		return result;
	}
	
	/*
	 * Getters and Setters
	 */
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
