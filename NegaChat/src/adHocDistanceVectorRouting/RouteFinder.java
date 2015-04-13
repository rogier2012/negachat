package adHocDistanceVectorRouting;

import negachat.messages.ReceivingMultiSocket;
import negachat.messages.ReceivingSingleSocket;
import negachat.messages.ReceivingSocket;
import negachat.messages.SendingMultiSocket;
import negachat.messages.SendingSingleSocket;
import negachat.packets.AODV.RREQ;

public class RouteFinder implements Runnable {
	
	/*
	 * Constants
	 */
	
	public static final int MAX_ROUTE_AGE = 1000000;
	public static final int INITIALRREQLIFESPAN = 10000;
	
	/*
	 * Instance Variables
	 */
	
	private String source;
	private String destination; 
	
	private ReceivingSingleSocket singleReceiver;
	private ReceivingMultiSocket multiReceiver;
	private SendingSingleSocket singleSender;
	private SendingMultiSocket multiSender;
	
	/*
	 * Constructors
	 */
	
	public RouteFinder(String source, String destination, ReceivingSocket receiveSocket)	{
		if (receiveSocket instanceof ReceivingSingleSocket)	{
			singleReceiver = (ReceivingSingleSocket) receiveSocket;
		} else if (receiveSocket instanceof ReceivingMultiSocket)	{
			multiReceiver = (ReceivingMultiSocket) receiveSocket;
		}
		
		this.source = source;
		this.destination = destination;
	}
	
	/*
	 * Queries
	 */
	
	private int randomWithRange(int min, int max)	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	/*
	 * Commands
	 */
	
	public void run()	{
		// Send RREQ
		RREQ request = new RREQ(this.destination, (byte) INITIALRREQLIFESPAN, (byte) randomWithRange(0, 127)); 
		multiSender = new SendingMultiSocket();
		multiSender.send(request);
		
		// Wait for RREP
		
		
		
		
		
		
		
	}
	
	
}
