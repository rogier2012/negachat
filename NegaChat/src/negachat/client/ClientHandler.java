package negachat.client;

import javax.swing.JTabbedPane;

import negachat.messages.ReceivingSocket;
import negachat.view.ChatFrame;
import negachat.view.ChatFrameController;
import negachat.view.WhoIsOnlineController;

public class ClientHandler {
	private JTabbedPane tabbedPane;
	private ReceivingSocket rsocket;
	
	public ClientHandler(JTabbedPane tabbedPane, ReceivingSocket rsocket){
		this.tabbedPane = tabbedPane;
		this.rsocket = rsocket;
	}
	
	public void addChat(String name, WhoIsOnlineController onlineController){
		ChatFrame cFrame1 = new ChatFrame();
		ChatFrameController cFrameControl = new ChatFrameController(cFrame1, name, rsocket);
		rsocket.addObserver(cFrameControl);
		tabbedPane.add(name, cFrame1);
	}

}
