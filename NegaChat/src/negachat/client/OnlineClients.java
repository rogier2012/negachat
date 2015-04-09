package negachat.client;

import java.util.ArrayList;

public class OnlineClients {
	private ArrayList<String> clients;
	
	public OnlineClients(){
		clients = new ArrayList<String>();
	}

	public ArrayList<String> getClients() {
		return clients;
	}
	
	public void addClient(String client){
		clients.add(client);
	}
}
