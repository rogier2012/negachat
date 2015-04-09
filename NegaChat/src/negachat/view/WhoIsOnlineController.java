package negachat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import negachat.client.ClientHandler;
import negachat.client.ClientsOnline;

public class WhoIsOnlineController extends Observable implements ActionListener {
	private WhoIsOnline online;
	private ClientsOnline clientList;
	private ClientHandler handler;
	private Set<String> tabs;
	
	public WhoIsOnlineController(WhoIsOnline online, ClientHandler handler){
		this.online = online;
		clientList = new ClientsOnline();
		this.handler = handler;
		tabs = new HashSet<String>();
	}
	
	public void addClient(String name){
		online.addClient(name);
		online.getButton(name).addActionListener(this);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		String name = actionEvent.getActionCommand();
		if(!(tabs.contains(name))){
			
			handler.addChat(name, this);
			tabs.add(name);
			this.setChanged();
			this.notifyObservers(online);
		}
		
	}
	
	public WhoIsOnline getOnline(){
		return online;
	}
	
}
