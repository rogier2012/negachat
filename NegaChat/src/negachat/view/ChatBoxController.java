package negachat.view;

import negachat.audio.AudioPlayer;

public class ChatBoxController {
	private ChatBox chatBox;
	
	private AudioPlayer audioPlayer;
	private static final String NOTIFIER = "Notifier.wav";
	private static final String WOLOLO = "Wololo.wav";
	
	public ChatBoxController(ChatBox chatbox){
		this.chatBox = chatbox;
		audioPlayer = new AudioPlayer();
	}

	public void setMessage(String message){
		chatBox.append(message);
		
		if (message.charAt(0) == 3 && message.charAt(1) == 0)	{
			playSound(WOLOLO);
		} else	{
			playSound(NOTIFIER);
		}
		chatBox.repaint();
	}

	public void playSound(String file){
		audioPlayer.play(file);
	}
	
}
