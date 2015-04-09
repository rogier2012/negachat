package negachat.view;

public class ChatBoxController {
	private ChatBox chatBox;
	
	public ChatBoxController(ChatBox chatbox){
		this.chatBox = chatbox;
	}

	public void setMessage(String message){
		chatBox.append(message);
		chatBox.repaint();
	}

	
	
}
