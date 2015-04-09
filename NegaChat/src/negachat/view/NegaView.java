package negachat.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import negachat.messages.ReceivingSocket;

public class NegaView {

	private JFrame frame;
	private String myName = "Me";

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
		ChatFrame cFrame1 = new ChatFrame();
		String groupChatName = "All";
		ReceivingSocket rsocket = new ReceivingSocket(myName);
		Thread t1 = new Thread(rsocket);
		t1.start();
		ChatFrameController cFrameControl1 = new ChatFrameController(cFrame1, groupChatName, rsocket);
		rsocket.addObserver(cFrameControl1);
		tabbedPane.add(groupChatName, cFrame1);
	}
	
	

}