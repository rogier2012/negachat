package Tests;

import negachat.packets.MessagePacket;

public class HashTest {
	public static void main(String[] args) {
		MessagePacket packet = new MessagePacket("tekst");
		packet.setMessage("ditiseentest!");
		MessagePacket received = new MessagePacket(packet.toByteArray());


	}
}















//package Tests;
//
//import negachat.packets.CreatePacket;
//
//public class CreatePacketTest {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		CreatePacket creator = new CreatePacket();
//		creator.setDestination("all");
//		System.out.println(creator.getDestination());
//		creator.setMessage("Dit is een test");
//		System.out.println(creator.getMessage());
//		
//
//	}
//
//}
