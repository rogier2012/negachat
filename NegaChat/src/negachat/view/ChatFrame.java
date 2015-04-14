package negachat.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class ChatFrame extends JPanel {
	ChatBox chatbox;
	MessageField mField;
	WhoIsOnline online;
	private JScrollPane scrollPane;
	
	
	public MessageField getmField() {
		return mField;
	}

	public WhoIsOnline getOnline() {
		return online;
	}
	
	public void setOnline(WhoIsOnline online){
		this.online = online;
		online.repaint();
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
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		chatbox = new ChatBox();
		DefaultCaret caret = (DefaultCaret)chatbox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(chatbox);

		mField = new MessageField();
		GridBagConstraints gbc_mField = new GridBagConstraints();
		gbc_mField.gridwidth = 2;
		gbc_mField.fill = GridBagConstraints.BOTH;
		gbc_mField.gridx = 0;
		gbc_mField.gridy = 1;
		add(mField, gbc_mField);
	}

}
