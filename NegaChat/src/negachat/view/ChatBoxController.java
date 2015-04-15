package negachat.view;

import negachat.audio.AudioPlayer;

public class ChatBoxController {
	private ChatBox chatBox;
	
	private AudioPlayer audioPlayer;
	private static final String NOTIFIER = "Notifier.wav";
	
	public ChatBoxController(ChatBox chatbox){
		this.chatBox = chatbox;
		audioPlayer = new AudioPlayer();
	}

	public void setMessage(String message){
		chatBox.append(message);
		audioPlayer.play(NOTIFIER);
		chatBox.repaint();
	}

	public void playSound(String file){
		
	}
	
}
