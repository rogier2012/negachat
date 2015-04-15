package negachat.packets;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import negachat.client.RoutingTable;
import negachat.encryption.AssymetricEncrypter;
import negachat.view.NegaView;

public class MessagePacket extends Packet implements DirectPacket {

	public static final byte TYPE = 0;

	// Length of the headers in bytes

	public static final int TYPELENGTH = 1;
	public static final int DESTINATION = 16;
	public static final int SOURCE = 16;
	public static final int MESSAGE = 128;
	public static final int OPTIONS = 1;
	public static final int HASH = 4;

	public static final int TOTAL = TYPELENGTH + SOURCE + DESTINATION + MESSAGE
			+ OPTIONS + HASH;

	private byte seqnum;
	private String source, destination, message;
	private RoutingTable table;
	byte[] hash;

	public MessagePacket(String destination, RoutingTable table) {
		super();
		this.source = NegaView.getMyName();
		this.destination = destination;
	}

	public MessagePacket(byte[] packetArray, RoutingTable table) {
		super(packetArray);
		this.table = table;
		this.setType(packetArray[0]);
		byte[] sourceArray = new byte[16];


		System.arraycopy(packetArray, TYPELENGTH, sourceArray, 0, SOURCE);
		this.setSource(this.removePadding(new String(sourceArray)));

		byte[] destArray = new byte[16];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE, destArray, 0,
				DESTINATION);
		this.setDestination(this.removePadding(new String(destArray)));

		byte[] messageArray = new byte[128];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE + DESTINATION, messageArray, 0,
				MESSAGE);
		this.setMessage((new String(this.decrypt(messageArray, table))));

		this.setSeqNum(packetArray[TYPELENGTH + DESTINATION +SOURCE + MESSAGE]);
		
		
		byte[] hashArray = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH + DESTINATION + SOURCE + MESSAGE + OPTIONS,
				hashArray, 0, HASH);
		hash = hashArray;
	}

	// TODO
	// public Packet(String source, String destination, String message, byte[]
	// options) {
	// this.setSource(source);
	// this.setDestination(destination);
	// this.setMessage(message);
	// this.setOptions(options);
	// }

	// 10/4 PACKET FORMAT:

	// [type] [source] [destination] [message] [options] [hash]
	// 1 byte 16 bytes 16 bytes 128 bytes 1 byte 4 bytes

	@Override
	public byte[] toByteArray() {

		byte[] dest, src, msg;
		byte type, opt;
		type = TYPE;
		dest = this.fillNickname(this.getDestination());
		src = this.fillNickname(this.getSource());
		msg = AssymetricEncrypter.Encrypt(this.getMessage().getBytes(), (PublicKey)table.getTable().get(getDestination()).get(3));
		opt = this.getSeqNum();
		

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;

		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE);

		System.arraycopy(dest, 0, bytePacket, TYPELENGTH + SOURCE, DESTINATION);

		System.arraycopy(msg, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE,
				MESSAGE);

		bytePacket[TYPELENGTH + DESTINATION + SOURCE + MESSAGE] = opt;
		

		hash = makeHash(getSource(), getDestination(), getMessage());
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE + MESSAGE + OPTIONS, HASH);
		
//		System.out.println("Group message has been sent! \n");

		return bytePacket;
	}
	
	public byte[] makeHash(String src, String dest, String msg) {
		byte[] hashCode = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("No such algorithm!");
		}
		byte[] toHash = new byte[msg.length() + dest.length() + source.length()];
		System.arraycopy(src.getBytes(), 0, toHash, 0, src.length());
		System.arraycopy(dest.getBytes(), 0, toHash, src.length(), dest.length());
		System.arraycopy(msg.getBytes(), 0, toHash, src.length() + dest.length(), msg.length());
		try {
		    md.update(toHash);
		    hashCode = ((MessageDigest) md.clone()).digest();
		 } catch (CloneNotSupportedException cnse) {
		     System.out.println("couldn't make digest of this content.");
		 } catch (NullPointerException e) {
			 System.out.println("MessageDigest md not initialized!");
		 }
		return hashCode;
		
		
		
		
//		byte[] toHash = new byte[msg.length() + dest.length() + source.length()];
//	
//		System.arraycopy(src.getBytes(), 0, toHash, 0, src.length());
//
//		System.arraycopy(dest.getBytes(), 0, toHash, src.length(), dest.length());
//
//		System.arraycopy(msg.getBytes(), 0, toHash, src.length() + dest.length(), msg.length());
//		
//		int hashCode = toHash.hashCode();
//		return ByteBuffer.allocate(4).putInt(hashCode).array();
	}

	public byte getSeqNum() {
		return seqnum;
	}

	public void setSeqNum(byte seqnum) {
		this.seqnum = seqnum;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public byte[] decrypt(byte[] encryptedMessage, RoutingTable table){
		byte[] result = AssymetricEncrypter.Decrypt(encryptedMessage, table.getPrivateKey());
		return result;
	}

	@Override
	public String getDestination() {
		return destination;
	}

	public String getMessage() {
		return message;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public byte[] getHash() {
		return hash;
	}
}
