package Tests;

import negachat.packets.MessagePacket;

public class HashTest {
	public static void main(String[] args) {
		byte[] test = {3,4,5,6,7,8};
		int iets = test.hashCode();
		System.out.println(test.hashCode());
		System.out.println(test.hashCode() == iets);
		
		
		
		
		
		MessagePacket packet = new MessagePacket("tekst");
		packet.setMessage("ditiseentest!");
		packet.toByteArray();
		
		MessagePacket received = new MessagePacket(packet.toByteArray());
		
//		byte[] hash = received.retrieveHash(packet.toByteArray());
		byte[] packet2 = received.packetWithoutHash(packet.toByteArray());
		
//		System.out.println(hash.length);
		System.out.println(packet2.length);

//		System.out.println(hash == received.makeHash(packet2));
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
