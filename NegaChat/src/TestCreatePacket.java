import packets.createPacket;


public class TestCreatePacket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createPacket creator = new createPacket();
		creator.setMessage("/all/ Dit is een test!:)");
		creator.composePacket();
	}

}
