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
	private WhoIsOnline online;

	// Controllers of sub views
	private ChatBoxController cbController;
	private MessageFieldController mfController;
	private WhoIsOnlineController wioController;

	String otherName;

	ReceivingSocket rsocket;

	public ChatFrameController(ChatFrame cFrame, String otherName,
			ReceivingSocket rsocket) {
		this.cFrame = cFrame;
		this.otherName = otherName;
		this.rsocket = rsocket;
		initialize();
	}

	public void initialize() {
		chatbox = cFrame.getChatbox();
		cbController = new ChatBoxController(chatbox);
		mField = cFrame.getmField();
		mfController = new MessageFieldController(mField, this);
		online = cFrame.getOnline();
		wioController = new WhoIsOnlineController(online);
	}

	public void update(Observable obs, Object arg) {
		if (obs == mfController) {
			cbController.setMessage(mfController.getMessage());
		} else if (obs == rsocket
				&& (rsocket.getRecvPacket().getSource().equals(otherName) || rsocket
						.getRecvPacket().getSource().equals("All"))) {
			cbController.setMessage(rsocket.getRecvPacket().getSource() + ": " + rsocket.getRecvPacket().getMessage());
		}
	}
}
