import java.util.ArrayList;
import java.util.List;

import negachat.packets.CreatePacket;

public class TestCreatePacket {
	public static final int MAX_MESSAGE_LENGTH = 128;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String message = "Dit zijn letters engh8oiasssssssssssssssssssssssssssssss;d hfhfiudjgadjfalidsjoiashoigia cijfers 1234567890";
		System.out.println(message.length());
		System.out.println(message.getBytes().length);
		
		
		if(message.length() < MAX_MESSAGE_LENGTH) {
			
			int length = message.length();
			int todo = MAX_MESSAGE_LENGTH - length;
			List<String> messageList = new ArrayList<String>();
			for (int i = todo; i > 0; i--) {
				message += "0";
			}
		}
		
		System.out.println(message);
		System.out.println(message.length());
		System.out.println(message.getBytes().length);
		
		CreatePacket creator = new CreatePacket();
		creator.setMessage("/all/ Dit is een test!:)");
		creator.composePacket();
	}

}
