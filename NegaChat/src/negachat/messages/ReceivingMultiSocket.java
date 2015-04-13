package negachat.messages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import negachat.client.RoutingTable;
import negachat.packets.GroupMessagePacket;
import negachat.packets.Packet;
import negachat.packets.AODV.HELLO;
import negachat.packets.AODV.RREQ;
import negachat.view.NegaView;

public class ReceivingMultiSocket extends ReceivingSocket {
	private InetAddress group;
	private MulticastSocket multisocket;
	
	public static final int MULTICAST_PORT = 6112;
	
	public ReceivingMultiSocket(RoutingTable table){
		super(table);
		try {
			multisocket = new MulticastSocket(MULTICAST_PORT);
			group = InetAddress.getByName("228.5.6.7");
			multisocket.joinGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		do{
			byte[] buf = new byte[1000];
			DatagramPacket recv = new DatagramPacket(buf, 166);
			try {
				multisocket.receive(recv);
				System.out.println("Packet received?");
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
				if(!packet.getSource().equals(NegaView.getMyName())){
					SendingMultiSocket sendingsocket = new SendingMultiSocket();
					sendingsocket.send(packet);
				}
				
			}
			
		} while (1 < 2);
	}

	public void handlePacket(Packet packet) {
		RoutingTable table = new RoutingTable();
		if (packet instanceof HELLO){
			HELLO pakket = (HELLO) packet;
			String source = pakket.getSource();
			
			if (!table.getTable().containsKey(source))	{
				table.addDestination(source, null, 0);
			}
			
			
			
			
			
		} else if (packet instanceof RREQ){
			// Cast to RREQ
			RREQ pakket = (RREQ) packet;
			
			byte lifeSpan = pakket.getLifeSpan();
			byte identifier = pakket.getIdentifier();
			
			String source = pakket.getSource();
			String destination = pakket.getDestination();
			
			// TODO -- UPDATE ROUTES
			
			if (NegaView.getMyName() == destination)	{
				if (table.getTable().containsKey(destination) && table.getTable().get(destination).get(0) != null)	{
					// TODO -- send RREP
				}
			} else {
				// TODO -- forward RREQ
			}
			
			
			
		} else if (packet instanceof GroupMessagePacket){
//			if (((GroupMessagePacket) packet).makeHash() == ((GroupMessagePacket) packet).getHash()) {
				setTimestamp(System.currentTimeMillis());
				this.setRecvPacket(packet);
				this.setChanged();
				this.notifyObservers();

		}
	}
	
	
}