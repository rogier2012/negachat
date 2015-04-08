package negachat.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ChatFrame extends JPanel {
	ChatBox chatbox;
	MessageField mField;
	WhoIsOnline online;
	
	
	public MessageField getmField() {
		return mField;
	}

	public WhoIsOnline getOnline() {
		return online;
	}
	
	public ChatBox getChatbox() {
		return chatbox;
	}

	
	/**
	 * Create the panel.
	 */
	public ChatFrame() {
		initialize();
		
	}
	
	public void initialize(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{230, 0};
		gridBagLayout.rowHeights = new int[]{184, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		chatbox = new ChatBox();
		GridBagConstraints gbc_chatbox = new GridBagConstraints();
		gbc_chatbox.anchor = GridBagConstraints.NORTH;
		gbc_chatbox.fill = GridBagConstraints.BOTH;
		gbc_chatbox.gridx = 0;
		gbc_chatbox.gridy = 0;
		add(chatbox, gbc_chatbox);
		
		online = new WhoIsOnline();
		GridBagConstraints gbc_online = new GridBagConstraints();
		gbc_online.fill = GridBagConstraints.BOTH;
		gbc_online.gridx = 1;
		gbc_online.gridy = 0;
		add(online, gbc_online);

		mField = new MessageField();
		GridBagConstraints gbc_mField = new GridBagConstraints();
		gbc_mField.gridwidth = 2;
		gbc_mField.fill = GridBagConstraints.BOTH;
		gbc_mField.gridx = 0;
		gbc_mField.gridy = 1;
		add(mField, gbc_mField);
	}

}
