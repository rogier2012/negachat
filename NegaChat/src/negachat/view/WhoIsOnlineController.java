package negachat.view;

import negachat.client.ClientsOnline;

public class WhoIsOnlineController implements Runnable {
	private WhoIsOnline online;
	private ClientsOnline clientList;
	
	public WhoIsOnlineController(WhoIsOnline online){
		this.online = online;
		clientList = new ClientsOnline();
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
