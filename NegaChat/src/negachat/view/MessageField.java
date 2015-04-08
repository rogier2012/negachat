package negachat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessageField extends JPanel {
	
	
	private JButton btnSend;
	private JTextArea txtInput;

	/**
	 * Create the panel.
	 */
	public MessageField() {
		btnSend = new JButton("Send");
	    txtInput = new JTextArea();

		
		
	}
	
	public JButton getButton(){
		return btnSend;
	}
	
	public JTextArea getTxtInput(){
		return txtInput;
	}

}
