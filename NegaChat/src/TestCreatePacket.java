import negachat.packets.CreatePacket;


public class TestCreatePacket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreatePacket creator = new CreatePacket();
		creator.setMessage("/all/ Dit is een test!:)");
		creator.composePacket();
	}

}
