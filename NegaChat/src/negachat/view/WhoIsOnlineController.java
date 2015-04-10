package negachat.view;

import negachat.client.OnlineClients;

public class WhoIsOnlineController implements Runnable {
	private WhoIsOnline online;
	private OnlineClients clientList;
	
	public WhoIsOnlineController(WhoIsOnline online){
		this.online = online;
		clientList = new OnlineClients();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientList.getClients();
	}
}
