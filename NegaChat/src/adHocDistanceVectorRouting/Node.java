package adHocDistanceVectorRouting;

import negachat.packets.MessagePacket;
import negachat.packets.Packet;
import negachat.packets.AODV.HELLO;
import negachat.packets.AODV.RERR;
import negachat.packets.AODV.RREP;
import negachat.packets.AODV.RREQ;

public class Node {
	
	public static final int MAX_ROUTE_AGE = 1000000;
	
	private String IP = null;
	
	private RoutingTable table;
	
	public HELLO sendHello()	{
		return null;
	}
	
	public MessagePacket replyRREQ	(RREQ request)	{
		return null;//TODO
	}
	
	public MessagePacket forwardPacket (MessagePacket packet)	{
		return null;//TODO
	}
	
	public void updateTable(Packet packet)	{
		if (packet instanceof HELLO)	{
			
			
			
			
		}
	}
	
	public boolean isDestination(RREQ request)	{
		return false;
	}
	
	public Node getFreshRoute(Node destination)	{
		return null;
	}
	
	public boolean isOriginator(RREP reply)	{
		return false;
	}
	
	/*
	 * Returns true if any routes have been removed.
	 */
	public boolean removeAffectedRoutes(RERR error)	{
		return false;
	}
	
}
