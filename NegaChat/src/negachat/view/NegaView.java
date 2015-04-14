package negachat.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import negachat.client.ClientHandler;
import negachat.client.OnlineClients;
import negachat.client.RoutingTable;
import negachat.messages.ReceivingMultiSocket;
import negachat.messages.ReceivingSingleSocket;
import negachat.presence.PresenceFlooder;
import negachat.presence.TableDecay;

public class NegaView {

	private JFrame frame;
	private static String myName;
	private WhoIsOnlineController wioController;
	
	public static final String GROUP_CHAT_NAME = "All";
	public static final String NEGA_CHAT = "NegaChat";
	public static final String WHO_IS_ONLINE = "Online";

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
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
						System.out.println("FATAL ERROR: exiting program!");
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
	 * @wbp.parser.entryPoint
	 */
	public NegaView() {
		myName = JOptionPane.showInputDialog(frame,"Please enter a nickname between 3 and 14 characters", null);
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
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame(NEGA_CHAT);
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		WhoIsOnline online = new WhoIsOnline();
		frame.getContentPane().add(online, BorderLayout.EAST);
		
		RoutingTable routingTable = new RoutingTable();
		PresenceFlooder flooder = new PresenceFlooder(routingTable);
		TableDecay tabledecay = new TableDecay(routingTable);
		Thread threadf = new Thread(flooder);
		threadf.start();
		Thread threadt = new Thread(tabledecay);
		threadt.start();
		
		ReceivingSingleSocket rssocket = new ReceivingSingleSocket(routingTable);
		ReceivingMultiSocket rmsocket = new ReceivingMultiSocket(routingTable);
		Thread threadrs = new Thread(rssocket);
		Thread threadrm = new Thread(rmsocket);
		threadrs.start();
		threadrm.start();
		ClientHandler handler = new ClientHandler(tabbedPane, rssocket, routingTable);
		wioController = new WhoIsOnlineController(online, handler);

		ChatFrame cFrame1 = new ChatFrame();
		ChatFrameController cFrameControl1 = new ChatFrameController(cFrame1, GROUP_CHAT_NAME, rmsocket, routingTable);
		wioController.addObserver(cFrameControl1);
		rmsocket.addObserver(cFrameControl1);
		tabbedPane.add(GROUP_CHAT_NAME, cFrame1);
//		tabbedPane.add("online", new JPanel());
		
		OnlineClients clientlist = new OnlineClients(wioController, routingTable);
		routingTable.addObserver(clientlist);
	}
	
	public static String getMyName(){
		return myName;
	}

}
