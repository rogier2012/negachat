package negachat.packets.AODV;

import negachat.packets.DirectPacket;
import negachat.packets.Packet;

/*
 * RERR packets are sent when an expected route is unavailable.
 * 
 * Lay-Out:
 * [Type][Source][Destination][LostRoutes]
 */


public class RERR extends Packet implements DirectPacket{
	
	
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
	
	public RERR(String destination, String[] lostRoutes)	{
		super();
		this.setDestination(destination);
		this.lostRoutes = lostRoutes;
	}
	
	public RERR(byte[] byteArray)	{
		super(byteArray);
		this.setType(byteArray[TYPEINDEX]);
		
		byte[] temp = new byte[SOURCELENGTH];
		System.arraycopy(byteArray, SOURCEINDEX, temp, 0, SOURCELENGTH);
		this.setSource(this.removePadding(new String(temp)));
		
		temp = new byte[DESTINATIONLENGTH]; 
		System.arraycopy(byteArray, DESTINATIONINDEX, temp, 0, DESTINATIONLENGTH);
		this.setDestination(this.removePadding(new String(temp)));
		
		temp = new byte[byteArray.length - DESTINATIONLENGTH - DESTINATIONINDEX];
		System.arraycopy(byteArray, LOSTROUTESINDEX, temp, 0, byteArray.length - LOSTROUTESINDEX);
		
		this.lostRoutes = new String[temp.length/DESTINATIONLENGTH];
		
		int index = 0;
		byte[] element = new byte[DESTINATIONLENGTH];
		String nickname;
		for (int counter = temp.length/DESTINATIONLENGTH; counter > 0; counter--)	{
			// Get element
			System.arraycopy(temp, 0, element, 0, DESTINATIONLENGTH);
			// Convert it to nickname
			nickname = this.removePadding(new String(element));
			// Add element to String array
			lostRoutes[(index) / DESTINATIONLENGTH] = nickname;
			// Remove element from temp
			System.arraycopy(temp, DESTINATIONLENGTH, temp, 0, temp.length - DESTINATIONLENGTH);
			// Raise index
			index += DESTINATIONLENGTH;
//			System.arraycopy(src, srcPos, dest, destPos, length);
		}
	}

	/*
	 * Queries
	 */
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[LOSTROUTESINDEX + this.getLostRoutes().length*SOURCELENGTH];
		result[TYPEINDEX] = this.getType();
		
		byte[] source = this.fillNickname(this.getSource());
		System.arraycopy(source, 0, result, SOURCEINDEX, SOURCELENGTH);
		
		byte[] destination = this.fillNickname(this.getDestination());
		System.arraycopy(destination, 0, result, DESTINATIONINDEX, DESTINATIONLENGTH);
		
		int counter = 0;
		for (String element : this.getLostRoutes())	{
			System.arraycopy(this.fillNickname(element), 0, result, DESTINATIONINDEX + ((counter + 1) * DESTINATIONLENGTH), DESTINATIONLENGTH);
			counter++;
		}
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

	public String[] getLostRoutes() {
		return lostRoutes;
	}

	public void setLostRoutes(String[] lostRoutes) {
		this.lostRoutes = lostRoutes;
	}

}
