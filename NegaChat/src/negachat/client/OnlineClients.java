package negachat.client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import negachat.view.WhoIsOnlineController;

public class OnlineClients implements Observer{
	private ArrayList<String> clients;
	private WhoIsOnlineController online;
	private RoutingTable table;
	
	public OnlineClients(WhoIsOnlineController online, RoutingTable table){
		clients = new ArrayList<String>();
		this.online = online;
		this.table = table;
	}

	public ArrayList<String> getClients() {
		return clients;
	}
	
	public void addClient(String client){
		clients.add(client);
		online.addClient(client);
	}
	
	public void removeClient(String client){
		clients.remove(client);
		online.removeClient(client);
	}

	public void update(Observable obs, Object arg) {
		if(obs == table){
			if ((int)arg == 1){
				this.addClient(table.getAddedDestination());
			} else if ((int)arg == 2){
				this.removeClient(table.getRemovedDestination());
			}
		}
	}
}
