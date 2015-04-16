package negachat.packets;

import negachat.encryption.SymmetricEncrypter;
import negachat.view.NegaView;

//10/4 PACKET FORMAT:

//	[type]	[source] 	[message]	[sequence number]	[hash]
//	1 byte	16 bytes	128 bytes	1 byte				4 bytes

public class GroupMessagePacket extends Packet{
	
	private byte seqNum;
	private String source, message, reserved;


	public static final byte TYPE = 0x05;
	
	public static final int TYPELENGTH = 1;
	public static final int SOURCE = 16;
	public static final int MESSAGE = 128;
	public static final int SEQNUM = 1;
	public static final int RESERVED = 4;
	public static final int TOTAL = TYPELENGTH + SOURCE + MESSAGE + SEQNUM + RESERVED;
	
	public GroupMessagePacket(){
		super();
		source = NegaView.getMyName();
	}
	
	public GroupMessagePacket(byte[] data) {
			super(data);
			this.setType(data[0]);
			byte[] sourceArray = new byte[SOURCE];
			System.arraycopy(data, TYPELENGTH, sourceArray, 0, SOURCE);
			this.setSource(this.removePadding(new String(sourceArray)));
			
			byte[] messageArray = new byte[MESSAGE];
			System.arraycopy(data, TYPELENGTH + SOURCE, messageArray, 0, MESSAGE);
			this.setMessage(this.removePadding(new String(decrypt(messageArray))));
			
			this.setSeqNum(data[TYPELENGTH + SOURCE + MESSAGE]);
			byte[] hashArray = new byte[RESERVED];
			System.arraycopy(data, TYPELENGTH + SOURCE + MESSAGE + SEQNUM, hashArray, 0, RESERVED);
			setReserved(new String(hashArray));
	}

	@Override
	public byte[] toByteArray() {		
		byte[] src, msg, hash;
		byte type, sqn;
		type = TYPE;
		src = this.fillNickname(this.getSource());
		msg = this.fillMessage(this.getMessage());
		sqn = (byte) ((this.getSeqNum() + 1) % 255);
		hash = new byte[RESERVED];

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;
		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE);
		System.arraycopy(msg, 0, bytePacket, TYPELENGTH+SOURCE, MESSAGE);
		bytePacket[TOTAL - RESERVED - 1] = sqn;
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH+SOURCE+MESSAGE+SEQNUM, RESERVED);
		return bytePacket;
	}

	public byte getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(byte seqnum) {
		this.seqNum = seqnum;
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


	public String getMessage() {
		return message;
	}
	
	public void setReserved(String hash){
		this.reserved = hash;
	}
	
	public String getHash(){
		return reserved;
	}
	
	@Override
	public byte[] fillMessage(String message) {
		SymmetricEncrypter aes = new SymmetricEncrypter();
		if (message.length() < MAX_MESSAGE_LENGTH) {
			int length = message.length();
			int todo = MAX_MESSAGE_LENGTH - length;
			for (int i = todo; i > 0; i--) {
				message += "=";
			}
		}
		byte[] encryptedMessage = aes.Encrypt(message.getBytes());
		
		return encryptedMessage;
	}
	
	public String removePadding(String paddedString){
		return (paddedString.split("==")[0]);
	}
	
	public byte[] decrypt(byte[] message){
		SymmetricEncrypter aes = new SymmetricEncrypter();
		byte[] result = aes.Decrypt(message);
		return result;
	}
	
}
