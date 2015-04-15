package negachat.client;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JTabbedPane;

import negachat.messages.ReceivingSocket;
import negachat.view.ChatFrame;
import negachat.view.ChatFrameController;
import negachat.view.WhoIsOnlineController;
import negachat.view.CloseTabButton;

public class ClientHandler {
	private JTabbedPane tabbedPane;
	private ReceivingSocket rsocket;
	private Set<String> tabs;
	private RoutingTable table;

	
	public ClientHandler(JTabbedPane tabbedPane, ReceivingSocket rsocket, RoutingTable table){
		this.tabbedPane = tabbedPane;
		this.rsocket = rsocket;
		tabs = new HashSet<String>();
		this.table = table;
	}
	
	public void addChat(String name, WhoIsOnlineController onlineController){
		if(!(tabs.contains(name))){
			tabs.add(name);
			ChatFrame cFrame1 = new ChatFrame();
			ChatFrameController cFrameControl = new ChatFrameController(cFrame1, name, rsocket, table);
			rsocket.addObserver(cFrameControl);
			tabbedPane.add(name, cFrame1);
			new CloseTabButton(tabbedPane, tabbedPane.indexOfTab(name), this);
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
		} else {
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
		}
		
	}
	
	public void deleteClient(String name){
		if(tabs.contains(name)){
			tabs.remove(name);
		}
	}

}
