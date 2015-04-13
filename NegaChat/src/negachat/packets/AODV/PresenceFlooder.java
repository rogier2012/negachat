package negachat.packets.AODV;

import negachat.view.NegaView;

public class PresenceFlooder implements Runnable {
	
	public static final int DELAY = 5000;

	public PresenceFlooder(){
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		HELLO hello = new HELLO(NegaView.getMyName());
		hello.send(hello);
	}
}
