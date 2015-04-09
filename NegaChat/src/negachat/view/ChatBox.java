package negachat.view;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ChatBox extends JTextArea {
	
	
	/**
	 * Create the panel.
	 */
	public ChatBox() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setEditable(false);
	}

}
