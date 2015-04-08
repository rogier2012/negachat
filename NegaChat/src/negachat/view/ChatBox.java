package negachat.view;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;

@SuppressWarnings("serial")
public class ChatBox extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChatBox() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Hallo", 75, 75);
	}

	
	
}
