package Tests;

import negachat.packets.MessagePacket;

public class CreatePacketTest {
	public static void main(String[] args) {
		MessagePacket packet = new MessagePacket("tekst");
		packet.setMessage("ditiseentest!");
		MessagePacket received = new MessagePacket(packet.toByteArray());
		
		byte[] hash = received.retrieveHash(packet.toByteArray());
		byte[] packet2 = received.packetWithoutHash(packet.toByteArray());
		
		System.out.println(hash.length);
		System.out.println(packet2.length);
		
		
		

		System.out.println(new String(hash).equals(new String(received.makeHash(packet2))));
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
