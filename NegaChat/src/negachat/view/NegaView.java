package negachat.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import negachat.client.ClientHandler;
import negachat.messages.ReceivingSingleSocket;
import negachat.multicast.ReceivingMultiSocket;

public class NegaView {

	private JFrame frame;
	private static String myName;
	private WhoIsOnline online;
	private WhoIsOnlineController wioController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NegaView window = new NegaView();
					
					window.frame.setVisible(true);
					window.frame.setResizable(false);
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
		myName = JOptionPane.showInputDialog(frame,"What is your nickname?", null);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("NegaChat");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		String groupChatName = "All";
		
		ReceivingSingleSocket rssocket = new ReceivingSingleSocket(myName);
		ReceivingMultiSocket rmsocket = new ReceivingMultiSocket();
		Thread threadrs = new Thread(rssocket);
		Thread threadrm = new Thread(rmsocket);
		threadrs.start();
		threadrm.start();
		online = new WhoIsOnline();
		ClientHandler handler = new ClientHandler(tabbedPane, rssocket);
		wioController = new WhoIsOnlineController(online, handler);
		ChatFrame cFrame1 = new ChatFrame();
		ChatFrameController cFrameControl1 = new ChatFrameController(cFrame1, groupChatName, rmsocket);
		wioController.addObserver(cFrameControl1);
		rmsocket.addObserver(cFrameControl1);
		tabbedPane.add(groupChatName, cFrame1);
		tabbedPane.add("Online", online);
		
		
		wioController.addClient("Rogier");
		wioController.addClient("Gijs");
	}
	
	public static String getMyName(){
		return myName;
	}

}