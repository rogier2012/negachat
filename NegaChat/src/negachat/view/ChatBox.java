package negachat.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ChatBox extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChatBox() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Hallo", 75, 75);
	}

	
	
}
