package negachat.view;

import java.util.Observable;
import java.util.Observer;

import negachat.messages.ReceivingMultiSocket;
import negachat.messages.ReceivingSingleSocket;
import negachat.messages.ReceivingSocket;
import negachat.messages.SendingMultiSocket;
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
	
	private CreatePacket creator;

	public ChatFrameController(ChatFrame cFrame, String chatName,ReceivingSocket socket) {
		this.cFrame = cFrame;
		this.chatName = chatName;
		this.socket = socket;
		initialize();
		creator = new CreatePacket();
	}

	public void initialize() {
		chatbox = cFrame.getChatbox();
		cbController = new ChatBoxController(chatbox);
		mField = cFrame.getmField();
		mfController = new MessageFieldController(mField, this);
	}

	public void update(Observable obs, Object arg) {
		if (obs == mfController) {
			cbController.setMessage(mfController.getcbMessage());
			this.sendPacket();
		} else if (obs instanceof ReceivingSingleSocket && socket.getRecvPacket().getSource().equals(chatName)) {
			cbController.setMessage(socket.getRecvPacket().getSource() + ": " + ((MessagePacket)socket.getRecvPacket()).getMessage() + "\n");
		} else if (obs instanceof ReceivingMultiSocket){
			System.out.println("iets zeggen, got message. maa rmaakt niet uit");
			cbController.setMessage(socket.getRecvPacket().getSource() + ": " + ((GroupMessagePacket)socket.getRecvPacket()).getMessage() + "\n" );
		}
	}
	
	private void sendPacket() {
		creator.setDestination(chatName);
		creator.setMessage(mfController.getMessage());
		Packet toSend = creator.composePacket();
		System.out.println(new String(toSend.toByteArray()));
		SendingMultiSocket sendingsocket = new SendingMultiSocket();
		sendingsocket.send(toSend);
	}

	public String getChatName(){
		return chatName;
	}
}
