package negachat.view;

import java.util.Observable;
import java.util.Observer;

import packets.createPacket;

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
	

	public ChatFrameController(ChatFrame cFrame){
		this.cFrame = cFrame;
		initialize();
	}
	
	
	public void initialize(){
		 chatbox = cFrame.getChatbox();
		 cbController = new ChatBoxController(chatbox);
		 mField = cFrame.getmField();
		 mfController = new MessageFieldController(mField, this);
		 online = cFrame.getOnline();
		 wioController = new WhoIsOnlineController(online);
	}

	public void update(Observable obs, Object arg) {
		if(obs == mfController){
			cbController.setMessage(mfController.getMessage());
		}
	}
}
