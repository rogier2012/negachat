package negachat.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ChatFrame extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChatFrame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{232, 0, 0};
		gridBagLayout.rowHeights = new int[]{184, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		ChatBox chatbox = new ChatBox();
		GridBagConstraints gbc_chatbox = new GridBagConstraints();
		gbc_chatbox.anchor = GridBagConstraints.NORTH;
		gbc_chatbox.fill = GridBagConstraints.BOTH;
		gbc_chatbox.gridx = 0;
		gbc_chatbox.gridy = 0;
		add(chatbox, gbc_chatbox);
		
		WhoIsOnline online = new WhoIsOnline();
		GridBagConstraints gbc_online = new GridBagConstraints();
		gbc_online.fill = GridBagConstraints.BOTH;
		gbc_online.gridx = 1;
		gbc_online.gridy = 0;
		add(online, gbc_online);

		MessageField mField = new MessageField();
		GridBagConstraints gbc_mField = new GridBagConstraints();
		gbc_mField.gridwidth = 2;
		gbc_mField.fill = GridBagConstraints.BOTH;
		gbc_mField.gridx = 0;
		gbc_mField.gridy = 1;
		add(mField, gbc_mField);
		
	}

}
