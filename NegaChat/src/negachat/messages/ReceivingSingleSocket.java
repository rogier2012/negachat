package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import negachat.client.ClientHandler;
import negachat.client.RoutingTable;
import negachat.packets.DirectPacket;
import negachat.packets.MessagePacket;
import negachat.packets.Packet;
import negachat.packets.AODV.RERR;
import negachat.packets.AODV.RREP;
import negachat.view.NegaView;

public class ReceivingSingleSocket extends ReceivingSocket {
	private DatagramSocket clientsocket;
	public static final int UDP_PORT = 6116;
	private String myName;
	

	public ReceivingSingleSocket(RoutingTable table) {
		super(table);
		this.myName = NegaView.getMyName();
		try {
			clientsocket = new DatagramSocket(UDP_PORT, InetAddress.getByAddress(table.getMyIP()));
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		do {
			byte[] buf = new byte[1000];
			DatagramPacket recv = new DatagramPacket(buf, 166);
			try {
				clientsocket.receive(recv);
			} catch (IOException e) {
				e.printStackTrace();
				System.out
						.println("Oops... Something went wrong receiving a packet.");
			}
			if (recv.getData()[0] == MessagePacket.TYPE) {
				MessagePacket packet = new MessagePacket(recv.getData());
				System.out.println("MessagePacket received!");
				handlePacket(packet);
			} else if (recv.getData()[0] == RREP.TYPE) {
				RREP packet = new RREP(recv.getData());
				if (packet.getDestination().equals(NegaView.getMyName())){
					handlePacket(packet);
				} else {
					packet.setHopcount((byte)(packet.getHopcount()+1));
					SendingSingleSocket sendingsocket = new SendingSingleSocket(table);
					sendingsocket.sendPacket(packet);
				}
				
			} else if (recv.getData()[0] == RERR.TYPE) {
				RERR packet = new RERR(recv.getData());
				handlePacket(packet);
			}

		} while (true);
	}

	public void handlePacket(Packet packet) {
		if (packet instanceof MessagePacket) {
			if (myName.equals(((MessagePacket) packet).getDestination())){
//				if (((MessagePacket) packet).makeHash() == ((MessagePacket) packet).getHash()) {
					setTimestamp(System.currentTimeMillis());
					recvPacket = packet;
					setChanged();
					notifyObservers();
//				}
			}
			
		} else if (packet instanceof RREP){
			// Cast to RREP
			RREP pakket = (RREP) packet;
			System.out.println("RREP arrived!");
			String source = pakket.getSource();
			byte hopCount = pakket.getHopcount();
			String lastSource = pakket.getLastSource();
			
			// Update Table
			table.addDestination(source, lastSource, hopCount);
			
			// Did I send the request for this RREP?
			if (table.getRequestedDestinations().contains(source))	{
				// Remove from destinations that are still in request
				table.getRequestedDestinations().remove(source);
			} else	{ // (I did not send the RREQ for this RREP)
				// Forward RREP to destination
				SendingSingleSocket sendSocket = new SendingSingleSocket(table);
				((RREP) packet).setLastSource(NegaView.getMyName());
				sendSocket.sendPacket((DirectPacket)packet);
			}
			
		} else if (packet instanceof RERR){
			// Cast to RERR
			RERR pakket = (RERR) packet;
			int initialsize = table.getTable().keySet().size();
			
			// Remove routes for these destinations
			for (String element : pakket.getLostRoutes())	{
				table.getTable().get(element).set(0, null);
				table.getTable().get(element).set(1, 0);
			}
			// Clear Routes where these destinations are next hops
			for (String route : table.getTable().keySet())	{
				// Loop through nexthops that should be cleared
				for (String nextHop : pakket.getLostRoutes()){
					// If the route's nextHop is equal to the nexthop that is to be removed
					if (table.getNextHop(route) == nextHop)	{
						// Clear Route
						table.getTable().get(route).set(0, null);
						table.getTable().get(route).set(1, 0);
					}
				}
			}
			
			
			if (initialsize > table.getTable().keySet().size())	{
				// Forward RERR
			}
			
			
		} 
	}

	public void testrun() {
		MessagePacket nPacket = new MessagePacket("All");
		nPacket.setMessage("Ik ben Rogier");
		handlePacket(nPacket);
	}

}
