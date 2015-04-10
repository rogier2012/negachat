package negachat.view;

import java.util.Observable;
import java.util.Observer;

import negachat.messages.ReceivingSocket;

public class ChatFrameController implements Observer {
	// main view of this Controller
	private ChatFrame cFrame;

	// Sub views
	private ChatBox chatbox;
	private MessageField mField;
	private WhoIsOnlineController online;

	// Controllers of sub views
	private ChatBoxController cbController;
	private MessageFieldController mfController;

	String chatName;

	ReceivingSocket rsocket;

	public ChatFrameController(ChatFrame cFrame, String chatName,ReceivingSocket rsocket) {
		this.cFrame = cFrame;
		this.chatName = chatName;
		this.rsocket = rsocket;
		initialize();
	}

	public void initialize() {
		chatbox = cFrame.getChatbox();
		cbController = new ChatBoxController(chatbox);
		mField = cFrame.getmField();
		mfController = new MessageFieldController(mField, this);
	}

	public void update(Observable obs, Object arg) {
		if (obs == mfController) {
			cbController.setMessage(mfController.getMessage());
		} else if (obs == rsocket
				&& (rsocket.getRecvPacket().getSource().equals(chatName)|| rsocket.getRecvPacket().getType() == 5)) {
			cbController.setMessage(rsocket.getRecvPacket().getSource() + ": " + rsocket.getRecvPacket().getMessage() + "\n");
		} 
	}
	
	public String getChatName(){
		return chatName;
	}
}
