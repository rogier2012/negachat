package adHocDistanceVectorRouting;

import packets.Packet;
import packets.AODV.HELLO;
import packets.AODV.RREQ;

public class Node {
	
	private RoutingTable table;
	
	public HELLO sendHello()	{
		return null;//TODO
	}
	
	public Packet replyRREQ	(RREQ request)	{
		return null;//TODO
	}
	
	public Packet forwardRREQ (RREQ request)	{
		return null;//TODO
	}
	
	public void updateTable(RREQ request)	{
		//TODO
	}
	
	
}
