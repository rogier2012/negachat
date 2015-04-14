package negachat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import negachat.client.ClientHandler;

public class WhoIsOnlineController extends Observable implements ActionListener {
	private WhoIsOnline online;
	private ClientHandler handler;

	public WhoIsOnlineController(WhoIsOnline online, ClientHandler handler) {
		this.online = online;
		this.handler = handler;
	}

	public void addClient(String name) {
		online.addClient(name);
		online.getButton(name).addActionListener(this);
		online.repaint();
	}
	
	public void removeClient(String name){
		online.removeClient(name);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		String name = actionEvent.getActionCommand();
		handler.addChat(name, this);
		this.setChanged();
		this.notifyObservers(online);

	}

	public WhoIsOnline getOnline() {
		return online;
	}

}
