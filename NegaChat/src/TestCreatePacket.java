public class TestCreatePacket {
	public static final int MAX_MESSAGE_LENGTH = 128;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String message = "Dit zijn letters en cijfers 1234567890";
		System.out.println(message.length());
		System.out.println(message.getBytes().length);
		
		
		if(message.length() < MAX_MESSAGE_LENGTH) {
			for (int i = message.length(); i < MAX_MESSAGE_LENGTH; i++) {
				
			}
		}
//		
//		CreatePacket creator = new CreatePacket();
//		creator.setMessage("/all/ Dit is een test!:)");
//		creator.composePacket();
	}

}
