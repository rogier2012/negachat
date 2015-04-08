package negachat.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ChatBox extends JTextArea {
	
	public void setMessage(String message){
		this.append(message);
	}

	/**
	 * Create the panel.
	 */
	public ChatBox() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setEditable(false);
	}

}
