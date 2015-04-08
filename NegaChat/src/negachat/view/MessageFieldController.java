package negachat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageFieldController implements ActionListener {
	private MessageField mField;
	
	public MessageFieldController(MessageField mField){
		this.mField = mField;
		this.mField.getButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent actionEvent) {                  
        linkBtnAndLabel();
    }
	public void linkBtnAndLabel(){
		
	}
}
