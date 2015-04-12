package negachat.messages;

import java.util.Observable;

import negachat.client.RoutingTable;
import negachat.packets.Packet;

public abstract class ReceivingSocket extends Observable implements Runnable {
	protected Packet recvPacket;
	protected long timestamp;
	protected RoutingTable table;
	
	public ReceivingSocket(RoutingTable table){
		this.table = table;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public abstract void run();

	public abstract void handlePacket(Packet packet); 

	public void setRecvPacket(Packet recvPacket) {
		this.recvPacket = recvPacket;
	}

	public Packet getRecvPacket() {
		return recvPacket;
	}

	

}
