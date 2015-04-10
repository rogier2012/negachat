package negachat.packets.AODV;

import negachat.packets.Packet;

/*
 * RERR packets are sent when an expected route is unavailable.
 * 
 * Lay-Out:
 * [Type][Source][Destination][LostRoutes]
 */


public class RERR extends Packet {
	
	
	// Type ID of packet
	public static final byte TYPE = 4;
	
	// How many Bytes are reserved for this data
	public static final int DESTINATIONLENGTH = 16;
	
	// Index of data
	public static final int DESTINATIONINDEX = SOURCEINDEX + SOURCELENGTH;
	public static final int LOSTROUTESINDEX = DESTINATIONINDEX + DESTINATIONLENGTH;
	
	/*
	 * Instance Variables
	 */
	
	private String destination;
	private String[] lostRoutes;
	
	/*
	 * Constructors
	 */
	
	public RERR(String source, String destination, String[] lostRoutes)	{
		super(source);
		this.setDestination(destination);
		this.lostRoutes = lostRoutes;
	}
	
	public RERR(byte[] byteArray)	{
		super(byteArray);
		this.setType(TYPE);
		
		byte[] temp = null;
		
		this.setType(byteArray[TYPEINDEX]);
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(new String(temp));
		
		temp = null; 
		System.arraycopy(byteArray, DESTINATIONINDEX, temp, 0, DESTINATIONLENGTH);
		this.setDestination(new String(temp));
		
		temp = null;
		System.arraycopy(byteArray, LOSTROUTESINDEX, temp, 0, byteArray.length - LOSTROUTESINDEX);
		
		this.lostRoutes = new String[]{};
		
		int index = 1;
		byte[] element = null;
		String nickname;
		for (int counter = temp.length/DESTINATIONLENGTH; counter >= 0; counter--)	{
			// Get element
			System.arraycopy(temp, 0, element, 0, DESTINATIONLENGTH);
			// Convert it to nickname
			nickname = new String(element);
			// Add element to String array
			System.arraycopy(nickname, 0, this.lostRoutes, lostRoutes.length - 1, DESTINATIONLENGTH);
			// Remove element from temp
			System.arraycopy(temp, DESTINATIONLENGTH, temp, 0, temp.length - DESTINATIONLENGTH);
			// Raise index
			index += DESTINATIONLENGTH;
		}
	}

	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[]{this.getType()};
		byte[] source = this.getSource().getBytes();
		byte[] destination = this.getDestination().getBytes();
		System.arraycopy(source, 0, result, TYPEINDEX, TYPELENGTH);
		System.arraycopy(destination, 0, result, DESTINATIONINDEX, DESTINATIONLENGTH);
		for (String element : this.getLostRoutes())	{
			System.arraycopy(element.getBytes(), 0, result, result.length, DESTINATIONLENGTH);
		}
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

	public String[] getLostRoutes() {
		return lostRoutes;
	}

	public void setLostRoutes(String[] lostRoutes) {
		this.lostRoutes = lostRoutes;
	}

}
