package negachat.view;

import java.util.Observable;
import java.util.Observer;

import negachat.client.RoutingTable;
import negachat.messages.ReceivingMultiSocket;
import negachat.messages.ReceivingSingleSocket;
import negachat.messages.ReceivingSocket;
import negachat.messages.SendingMultiSocket;
import negachat.messages.SendingSingleSocket;
import negachat.packets.GroupMessagePacket;
import negachat.packets.MessagePacket;

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
	private RoutingTable table;
	
	private int counter;
	
	public ChatFrameController(ChatFrame cFrame, String chatName,ReceivingSocket socket, RoutingTable table) {
		this.cFrame = cFrame;
		this.chatName = chatName;
		this.socket = socket;
		counter = 1;
		this.table = table;
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
			cbController.setMessage(mfController.getcbMessage());
			this.sendPacket();
		} else if (obs instanceof ReceivingSingleSocket && socket.getRecvPacket().getSource().equals(chatName)) {
			cbController.setMessage(socket.getRecvPacket().getSource() + ": " + ((MessagePacket)socket.getRecvPacket()).getMessage() + "\n");
		} else if (obs instanceof ReceivingMultiSocket){
			cbController.setMessage(socket.getRecvPacket().getSource() + ": " + ((GroupMessagePacket)socket.getRecvPacket()).getMessage() + "\n" );
		}
	}
	
	private void sendPacket() {
		System.out.println(chatName);
		if (chatName.toLowerCase().equals("all")) {
			GroupMessagePacket groupMessage = new GroupMessagePacket();
			groupMessage.setMessage(mfController.getMessage());
			groupMessage.setSeqNum((byte) counter);
			if (counter > 254) {
				counter = 1;
			} else {
				counter++;
			}
			SendingMultiSocket sock = new SendingMultiSocket();
			sock.send(groupMessage);			
		} else {
			MessagePacket message = new MessagePacket(chatName);
			message.setMessage(mfController.getMessage());
			message.setSeqNum((byte) counter);
			if (counter > 254) {
				counter = 1;
			} else {
				counter++;
			}
			SendingSingleSocket sendingsocket = new SendingSingleSocket(table);
			sendingsocket.sendPacket(message);
		}
	}

	public String getChatName(){
		return chatName;
	}
}
