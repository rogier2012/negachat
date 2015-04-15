package negachat.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MessageField extends JPanel {
	
	
	private JButton btnSend;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MessageField() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {272, 85, 0};
		gridBagLayout.rowHeights = new int[]{46, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		textField.setDocument(new MyTextFieldLimit(117));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(20);
		
		btnSend = new JButton("Send");
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.fill = GridBagConstraints.BOTH;
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 0;
		add(btnSend, gbc_btnSend);
		
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
		return message;
	}


}
