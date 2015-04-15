package negachat.view;

import negachat.audio.AudioPlayer;

public class ChatBoxController {
	private ChatBox chatBox;
	private AudioPlayer audioPlayer;
	private static final String NOTIFIER = "Notification.wav";
	private static final String WOLOLO = "30_Wololo.wav";
	private static final String HERB_LAUGH = "11_Herb_laugh.wav";
	
	public ChatBoxController(ChatBox chatbox){
		this.chatBox = chatbox;
		audioPlayer = new AudioPlayer();
	}

	public void setMessage(String message){
		chatBox.append(message);
		String msg = message.split(":")[1].trim();
		System.out.println("0: " + msg.charAt(0));
		System.out.println("1: " + msg.charAt(1));
		if (msg.charAt(0) == '3' && msg.charAt(1) == '0')	{
			playSound(WOLOLO);
		} else if(msg.charAt(0) == '1' && msg.charAt(1) == '1')	{
			playSound(HERB_LAUGH);
		} else	{
			playSound(NOTIFIER);
		}
		chatBox.repaint();
	}

	public void playSound(String file){
		audioPlayer.play(file);
	}
	
}
