package negachat.view;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WhoIsOnline extends JPanel {
	HashMap<String, JButton> buttons;
	
	
	/**
	 * Create the panel.
	 */
	public WhoIsOnline() {
		this.setBorder(BorderFactory.createTitledBorder("Online"));
		buttons = new HashMap<String, JButton>();
	}

	public void addClient(String name){
		JButton btn = new JButton(name);
		add(btn);
		buttons.put(name, btn);
	}
	
	public void removeClient(String name){
		this.remove(buttons.get(name));
		buttons.remove(name);
	}
	
	public JButton getButton(String name){
		return buttons.get(name);
	}
}
