package negachat.audio;

public class SoundThread implements Runnable	{
	
	private String file;
	private AudioPlayer audioPlayer;
	
	public SoundThread(String file)	{
		audioPlayer  = new AudioPlayer();
		this.file = file;
	}
	
	public void run()	{
		audioPlayer.play(file);
	}
	
}