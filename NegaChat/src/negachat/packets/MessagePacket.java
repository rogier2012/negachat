package negachat.packets;

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

	private byte options;
	private String source, destination, message, hash;

	public MessagePacket(String destination) {
		super();
		this.source = NegaView.getMyName();
		this.destination = destination;
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
		System.arraycopy(packetArray, TYPELENGTH + SOURCE, messageArray, 0,
				MESSAGE);
		this.setMessage(this.removePadding(new String(messageArray)));

		this.setOptions(packetArray[TYPELENGTH + + DESTINATION +SOURCE + MESSAGE]);
		byte[] hashArray = new byte[4];
		System.arraycopy(packetArray, TYPELENGTH + SOURCE + MESSAGE + OPTIONS,
				hashArray, 0, HASH);
		setHash(new String(hashArray));
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

	public byte[] toByteArray() {

		byte[] dest, src, msg, hash;
		byte type, opt;
		type = getType();
		dest = this.fillNickname(getDestination());
		src = this.fillNickname(this.getSource());
		msg = this.fillMessage(this.getMessage());
		opt = this.getOptions();
		hash = makeHash().getBytes();

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;

		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE);

		System.arraycopy(dest, 0, bytePacket, TYPELENGTH + SOURCE, DESTINATION);

		System.arraycopy(msg, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE,
				MESSAGE);

		bytePacket[TYPELENGTH + DESTINATION + SOURCE + MESSAGE] = opt;

		System.arraycopy(hash, 0, bytePacket, TYPELENGTH + DESTINATION + SOURCE
				+ MESSAGE + OPTIONS, HASH);

		System.out.println("length: " + bytePacket.length);
		System.out.println("bytePacket string: " + new String(bytePacket));
		return bytePacket;
	}

	public String makeHash() {
		hash = "";
		int hashCode = this.hashCode();
		byte[] hash = new byte[] { (byte) ((hashCode >> 24) & 0xFF),
				(byte) ((hashCode >> 16) & 0xFF),
				(byte) ((hashCode >> 8) & 0xFF), (byte) (hashCode & 0xFF) };
		return new String(hash);
	}

	public byte getOptions() {
		return options;
	}

	public void setOptions(byte options) {
		this.options = options;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

	public String getMessage() {
		return message;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}
}
