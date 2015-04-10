package negachat.client;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JTabbedPane;
import negachat.messages.ReceivingSocket;
import negachat.view.ChatFrame;
import negachat.view.ChatFrameController;
import negachat.view.WhoIsOnlineController;

public class ClientHandler {
	private JTabbedPane tabbedPane;
	private ReceivingSocket rsocket;
	private Set<String> tabs;

	
	public ClientHandler(JTabbedPane tabbedPane, ReceivingSocket rsocket){
		this.tabbedPane = tabbedPane;
		this.rsocket = rsocket;
		tabs = new HashSet<String>();
	}
	
	public void addChat(String name, WhoIsOnlineController onlineController){
		if(!(tabs.contains(name))){
			tabs.add(name);
			ChatFrame cFrame1 = new ChatFrame();
			ChatFrameController cFrameControl = new ChatFrameController(cFrame1, name, rsocket);
			rsocket.addObserver(cFrameControl);
			tabbedPane.add(name, cFrame1);
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
		} else {
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
		}
		
	}

}
