package negachat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageFieldController {
	private MessageField mField;
	private ActionListener actionListener;
	
	public MessageFieldController(MessageField mField){
		this.mField = mField;
	}
	
	public void control(){        
        actionListener = new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {                  
                  linkBtnAndLabel();
              }
        };                
        mField.getButton().addActionListener(actionListener);   
    }
	
	public void linkBtnAndLabel(){
		
	}
}
