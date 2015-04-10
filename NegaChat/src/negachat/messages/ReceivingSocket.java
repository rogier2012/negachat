package negachat.messages;

import java.util.Observable;

import negachat.packets.Packet;

public abstract class ReceivingSocket extends Observable implements Runnable {
	protected Packet recvPacket;

	public abstract void run();

	public abstract void handlePacket(Packet packet); 

	public Packet getRecvPacket() {
		return recvPacket;
	}

	

}
