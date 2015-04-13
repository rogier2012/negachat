package negachat.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import negachat.client.ClientHandler;
import negachat.client.IPNicknameTable;
import negachat.client.OnlineClients;
import negachat.client.RoutingTable;
import negachat.messages.ReceivingMultiSocket;
import negachat.messages.ReceivingSingleSocket;

public class NegaView {

	private JFrame frame;
	private static String myName;
	private WhoIsOnline online;
	private WhoIsOnlineController wioController;
	
	public static final String GROUP_CHAT_NAME = "All";
	public static final String NEGA_CHAT = "NegaChat";
	public static final String WHO_IS_ONLINE = "Online";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NegaView window = new NegaView();
					if (myName != null){
						window.frame.setVisible(true);
						window.frame.setResizable(false);
					} else {
						System.exit(0);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NegaView() {
		myName = JOptionPane.showInputDialog(frame,"What is your nickname? Max. 16 characters", null);
		if (myName != null){
			while (myName.length() > 14|| myName.length() < 4){
				if(myName.length() < 4){
					myName = JOptionPane.showInputDialog(frame,"Please use a nickname with more than 3 characters", null);
				} else{
					myName = JOptionPane.showInputDialog(frame,"Please don't use more than 14 characters", null);
				}
				
			} 
			initialize();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(NEGA_CHAT);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		RoutingTable routingTable = new RoutingTable();
		new IPNicknameTable();
		ReceivingSingleSocket rssocket = new ReceivingSingleSocket(myName, routingTable);
		ReceivingMultiSocket rmsocket = new ReceivingMultiSocket(routingTable);
		Thread threadrs = new Thread(rssocket);
		Thread threadrm = new Thread(rmsocket);
//		threadrs.start();
		threadrm.start();
		online = new WhoIsOnline();
		ClientHandler handler = new ClientHandler(tabbedPane, rssocket);
		wioController = new WhoIsOnlineController(online, handler);
		ChatFrame cFrame1 = new ChatFrame();
		ChatFrameController cFrameControl1 = new ChatFrameController(cFrame1, GROUP_CHAT_NAME, rmsocket);
		wioController.addObserver(cFrameControl1);
		rmsocket.addObserver(cFrameControl1);
		tabbedPane.add(GROUP_CHAT_NAME, cFrame1);
		tabbedPane.add(WHO_IS_ONLINE, online);
		OnlineClients clientlist = new OnlineClients(wioController, routingTable);
		routingTable.addObserver(clientlist);
	}
	
	public static String getMyName(){
		return myName;
	}

}