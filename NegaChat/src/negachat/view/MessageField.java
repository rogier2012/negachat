package negachat.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MessageField extends JPanel {
	
	
	private JButton btnSend;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MessageField() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {370, 130};
		gridBagLayout.rowHeights = new int[] {240, 60};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		add(btnSend, gbc_btnNewButton);
		
	}
	
	public JButton getButton(){
		return btnSend;
	}
	
	public JTextField getTextField(){
		return textField;
	}
	
	public String getText(){
		String message = textField.getText();
		textField.setText("");
		return "Me: " + message + "\n";
	}


}
