package negachat.client;

import java.util.ArrayList;

public class ClientsOnline {
	private ArrayList<String> clients;
	
	public ClientsOnline(){
		clients = new ArrayList<String>();
	}

	public ArrayList<String> getClients() {
		return clients;
	}
	
	public void addClient(String client){
		clients.add(client);
	}
}
