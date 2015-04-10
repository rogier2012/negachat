package negachat.view;

import java.util.Observable;
import java.util.Observer;

import negachat.messages.ReceivingMultiSocket;
import negachat.messages.ReceivingSingleSocket;
import negachat.messages.ReceivingSocket;
import negachat.packets.CreatePacket;
import negachat.packets.GroupMessagePacket;
import negachat.packets.MessagePacket;
import negachat.packets.Packet;

public class ChatFrameController implements Observer {
	// main view of this Controller
	private ChatFrame cFrame;

	// Sub views
	private ChatBox chatbox;
	private MessageField mField;

	// Controllers of sub views
	private ChatBoxController cbController;
	private MessageFieldController mfController;

	private String chatName;

	private ReceivingSocket socket;

	public ChatFrameController(ChatFrame cFrame, String chatName,ReceivingSocket socket) {
		this.cFrame = cFrame;
		this.chatName = chatName;
		this.socket = socket;
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
			sendPacket();
			cbController.setMessage(mfController.getMessage());
		} else if (obs instanceof ReceivingSingleSocket && socket.getRecvPacket().getSource().equals(chatName)) {
			cbController.setMessage(socket.getRecvPacket().getSource() + ": " + ((MessagePacket)socket.getRecvPacket()).getMessage() + "\n");
		} else if (obs instanceof ReceivingMultiSocket && socket.getRecvPacket().getType() == 5){
			cbController.setMessage(socket.getRecvPacket().getSource() + ": " + ((GroupMessagePacket)socket.getRecvPacket()).getMessage() + "\n" );
		}
	}
	
	private void sendPacket() {
		CreatePacket creator = new CreatePacket();
		creator.setDestination(chatName);
		creator.setMessage(mfController.getMessage());
		Packet toSend = creator.composePacket();
		System.out.println(new String(toSend.toByteArray()));
	}

	public String getChatName(){
		return chatName;
	}
}
