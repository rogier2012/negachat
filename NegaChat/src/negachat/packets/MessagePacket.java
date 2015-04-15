package negachat.packets;

import java.nio.ByteBuffer;


import negachat.packets.DirectPacket;
import negachat.packets.Packet;

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
	byte[] hash;

	public MessagePacket(String destination) {
		super();
		this.source = NegaView.getMyName();
		this.destination = destination;
	}

	public MessagePacket(byte[] packetArray) {
		super(packetArray);
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
		this.setMessage(this.removePadding(new String(messageArray)));

		this.setSeqNum(packetArray[TYPELENGTH + + DESTINATION +SOURCE + MESSAGE]);
		
		
		byte[] hashArray = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE + MESSAGE + OPTIONS,
				hashArray, 0, HASH);
		setHash(hashArray);
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

		byte[] dest, src, msg, hash;
		byte type, opt;
		type = TYPE;
		dest = this.fillNickname(getDestination());
		src = this.fillNickname(this.getSource());
		msg = this.fillMessage(this.getMessage());
		opt = this.getSeqNum();
		

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;

		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE);

		System.arraycopy(dest, 0, bytePacket, TYPELENGTH + SOURCE, DESTINATION);

		System.arraycopy(msg, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE,
				MESSAGE);

		bytePacket[TYPELENGTH + DESTINATION + SOURCE + MESSAGE] = opt;
		

		hash = makeHash(bytePacket);
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE + MESSAGE + OPTIONS, HASH);
		
//		System.out.println("Group message has been sent! \n");

		return bytePacket;
	}
	
	public byte[] retrieveHash(byte[] packetArray) {
		byte[] hash = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH + DESTINATION + SOURCE + MESSAGE + OPTIONS, hash, 0, HASH);
		System.out.println("hash length: " + hash.length);


		return hash;
	}
	
	public byte[] packetWithoutHash(byte[] packetArray) {
		byte[] packet = new byte[162];
		System.arraycopy(packetArray, 0, packet, 0, TYPELENGTH + DESTINATION + SOURCE + MESSAGE + OPTIONS);
		return packet;
	}

	public byte[] makeHash(byte[] bytePacket) {
		hash = new byte[4];
		int hashCode = bytePacket.hashCode();
		hash = ByteBuffer.allocate(4).putInt(hashCode).array();
		return hash;
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
