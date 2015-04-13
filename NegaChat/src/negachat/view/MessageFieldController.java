package negachat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MessageFieldController extends Observable implements ActionListener {
	private MessageField mField;
	private String cbmessage;
	private String message;
	
	public String getcbMessage() {
		return cbmessage;
	}
	public String getMessage(){
		return message;
	}

	public MessageFieldController(MessageField mField, Observer observer){
		this.mField = mField;
		this.mField.getButton().addActionListener(this);
		this.mField.getTextField().addActionListener(this);
		this.addObserver(observer);
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		message = mField.getText();
		if (!message.equals("")){
			cbmessage = NegaView.getMyName() + ": " + message + "\n";
			setChanged();
		    notifyObservers();
		}
		
    }
	
}
