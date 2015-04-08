package negachat.view;

public class ChatBoxController implements Runnable {
	ChatBox chatBox;
	
	public ChatBoxController(ChatBox chatbox){
		this.chatBox = chatbox;
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
