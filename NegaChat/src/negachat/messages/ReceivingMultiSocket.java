package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.HashMap;

import negachat.client.RoutingTable;
import negachat.packets.GroupMessagePacket;
import negachat.packets.Packet;
import negachat.packets.AODV.HELLO;
import negachat.packets.AODV.RREP;
import negachat.packets.AODV.RREQ;
import negachat.view.NegaView;

public class ReceivingMultiSocket extends ReceivingSocket {
	private InetAddress group;
	private MulticastSocket multisocket;
	
	
	private HashMap<String, Byte> lastSeqNumber = new HashMap<String, Byte>();
	
	public static final int MULTICAST_PORT = 6112;
	
	public ReceivingMultiSocket(RoutingTable table){
		super(table);
		
		try {
			multisocket = new MulticastSocket(MULTICAST_PORT);
			group = (Inet4Address)Inet4Address.getByName("228.5.6.7");
			multisocket.joinGroup(new InetSocketAddress(group, MULTICAST_PORT), NetworkInterface.getByName("wlan0"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		do {
			byte[] buf = new byte[1000];
			DatagramPacket recv = new DatagramPacket(buf, 166);
			try {
				multisocket.receive(recv);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Oops... Something went wrong receiving a packet.");
			}
			if (recv.getData()[0] == HELLO.TYPE) {
				HELLO packet = new HELLO(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == RREQ.TYPE) {
				RREQ packet = new RREQ(recv.getData());
				handlePacket(packet);
			} else if (recv.getData()[0] == GroupMessagePacket.TYPE) {
				GroupMessagePacket packet = new GroupMessagePacket(recv.getData());
				handlePacket(packet);
				if(!packet.getSource().equals(NegaView.getMyName())) {
					if (lastSeqNumber.containsKey(packet.getSource())) {
						byte seq = lastSeqNumber.get(packet.getSource());
						if (seq > packet.getSeqNum()) {
							SendingMultiSocket sendingsocket = new SendingMultiSocket();
							sendingsocket.send(packet);
						}
					} else {
						lastSeqNumber.put(packet.getSource(), (byte) 0);
					}
				}
			}
			
		} while (1 < 2);
	}

	public void handlePacket(Packet packet) {
		if (packet instanceof HELLO && !packet.getSource().equals(NegaView.getMyName())){
			// Cast to HELLO
			System.out.println("A wild chatling appeard \n");
			HELLO pakket = (HELLO) packet;
			String source = pakket.getSource();
			byte hopCount = pakket.getHopCount();
			// Do I not know this node?
			if (!table.getTable().containsKey(source) )	{		
				// Update Table
				// Check for neighbour
				if (pakket.getHopCount() == 0)	{
					// Add neighbour to table
					table.addDestination(source, source, RoutingTable.MAXTTL);
				} else { // (No neighbour)
					// Add the destination's existence
					table.addDestination(source, null, hopCount);
				}
				// Add the source's InetAddress to table
				try {
					table.add(source, InetAddress.getByAddress(((HELLO)packet).getMyIP()));
				} catch (UnknownHostException e) {
//					e.printStackTrace();
				}
			} else { // (I do know of this node)
				// Reset the route TTL
				table.getTable().get(source).set(2, RoutingTable.MAXTTL);
			}
			// Is the maximum travel distance for this HELLO packet reached?
			if (hopCount >= HELLO.MAXHOPS)	{
				// Do nothing!
			} else	{ // (Packet should be forwarded)
				SendingMultiSocket sendSocket = new SendingMultiSocket();
				HELLO forward = pakket;
				// Increment hopCount
				forward.setHopCount((byte)(pakket.getHopCount() + 1));
				// Send HELLO!
				sendSocket.send(forward);
			}
			
		} else if (packet instanceof RREQ){
			// Cast to RREQ
			RREQ pakket = (RREQ) packet;
			String source = pakket.getSource();
			String destination = pakket.getDestination();
			
			// TODO -- UPDATE ROUTES
			
			// Am I the requested node? 
			if (NegaView.getMyName() == destination)	{
				// Send reply
				SendingSingleSocket sendSocket = new SendingSingleSocket(table);
				sendSocket.sendPacket(new RREP(source, destination));
			} else { // (I am not the requested node)
				// Do I know a valid route to the requested node?
				if (table.getTable().containsKey(destination) && table.getTable().get(destination).get(0) != null)	{
					// Send reply
					SendingSingleSocket sendSocket = new SendingSingleSocket(table);
					sendSocket.sendPacket(new RREP(source, destination));
				} else	{ // (I do not know a route to the requested destination)
					// Forward RREQ with a decremented TTL
					SendingMultiSocket sendSocket = new SendingMultiSocket();
					sendSocket.send(new RREQ(destination, (byte)(pakket.getLifeSpan() - 1), pakket.getIdentifier()));
				}
			}	
			
		} else if (packet instanceof GroupMessagePacket){
//			if (((GroupMessagePacket) packet).makeHash() == ((GroupMessagePacket) packet).getHash()) {
			if (!packet.getSource().equals(NegaView.getMyName()))	{
				System.out.println("Group Message received! \n");
				setTimestamp(System.currentTimeMillis());
				this.setRecvPacket(packet);
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
}
