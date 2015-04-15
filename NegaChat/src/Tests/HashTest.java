package Tests;

import java.security.DigestException;
import java.security.MessageDigest;
import java.util.Arrays;

import negachat.packets.MessagePacket;

public class HashTest {
	public static void main(String[] args) {
		
		 MessageDigest md = MessageDigest.getInstance("SHA");

		 try {
		    
		 } catch (CloneNotSupportedException cnse) {
		     throw new DigestException("couldn't make digest of partial content");
		 }
		MessagePacket packet = new MessagePacket("tekst");
		packet.setMessage("ditiseentest!");
		
		MessagePacket received = new MessagePacket(packet.toByteArray());
		
		byte[] hash = received.retrieveHash(packet.toByteArray());
		byte[] packet2 = received.packetWithoutHash(packet.toByteArray());
		
		System.out.println(packet.getMessage().equals(received.getMessage()));
		System.out.println(packet.getSource().equals(received.getSource()));
		System.out.println(packet.getDestination().equals(received.getDestination()));
		
		System.out.println(Arrays.equals(received.getHash(), packet.getHash()));
		
		System.out.println(Arrays.equals(packet.makeHash(packet.getSource(), packet.getDestination(), packet.getMessage()), received.makeHash(packet.getSource(), packet.getDestination(), packet.getMessage())));
		
		
//		System.out.println(Arrays.equals(received.getHash(), a2));
		
		
		
		
		
//		System.out.println(Arrays.equals(packetWithoutHash, test));
//		System.out.println(hash.length);
//		System.out.println(packet2.length);


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
