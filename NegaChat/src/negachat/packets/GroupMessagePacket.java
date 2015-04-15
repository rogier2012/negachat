package negachat.packets;

import negachat.encryption.SymmetricEncrypter;
import negachat.view.NegaView;

//10/4 PACKET FORMAT:

//	[type]	[source] 	[message]	[sequence number]	[hash]
//	1 byte	16 bytes	128 bytes	1 byte				4 bytes

public class GroupMessagePacket extends Packet{
	
	private byte seqNum;
	private String source, message, hash;


	public static final byte TYPE = 0x05;
	
	public static final int TYPELENGTH = 1;
	public static final int SOURCE = 16;
	public static final int MESSAGE = 128;
	public static final int SEQNUM = 1;
	public static final int HASH = 4;
	public static final int TOTAL = TYPELENGTH + SOURCE + MESSAGE + SEQNUM + HASH;
	
	public GroupMessagePacket(){
		super();
		source = NegaView.getMyName();
	}
	
	public GroupMessagePacket(byte[] data) {
			super(data);
			this.setType(data[0]);
			byte[] sourceArray = new byte[16];
			System.arraycopy(data, TYPELENGTH, sourceArray, 0, SOURCE);
			this.setSource(this.removePadding(new String(sourceArray)));
			
			byte[] messageArray = new byte[128];
			System.arraycopy(data, TYPELENGTH + SOURCE, messageArray, 0, MESSAGE);
			this.setMessage(this.removePadding(new String(decrypt(messageArray))));
			
			this.setSeqNum(data[TYPELENGTH + SOURCE + MESSAGE]);
			byte[] hashArray = new byte[4];
			System.arraycopy(data, TYPELENGTH + SOURCE + MESSAGE + SEQNUM, hashArray, 0, HASH);
			setHash(new String(hashArray));
	}

	@Override
	public byte[] toByteArray() {		
		byte[] src, msg, hash;
		byte type, opt;
		type = TYPE;
		src = this.fillNickname(this.getSource());
		msg = this.fillMessage(this.getMessage());
		opt = this.getSeqNum();	
		hash = makeHash().getBytes();

		byte[] bytePacket = new byte[TOTAL];
		bytePacket[0] = type;
		System.arraycopy(src, 0, bytePacket, TYPELENGTH, SOURCE - 1);
		System.arraycopy(msg, 0, bytePacket, TYPELENGTH+SOURCE, MESSAGE - 1);
		bytePacket[TOTAL - HASH - 1] = opt;
		System.arraycopy(hash, 0, bytePacket, TYPELENGTH+SOURCE+MESSAGE+SEQNUM, HASH - 1);
		
		
		System.out.println("Group message has been sent! \n");
		return bytePacket;
	}
	
	public String makeHash() {
		hash =  "";
		setHash("0x00");
		int hashCode = this.hashCode();
		byte[] hash = new byte[]{
				(byte) ((hashCode >> 24) & 0xFF),
		        (byte) ((hashCode >> 16) & 0xFF),   
		        (byte) ((hashCode >> 8) & 0xFF),  
		        (byte) (hashCode & 0xFF)};
		return new String(hash);
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
	
	public void setHash(String hash){
		this.hash = hash;
	}
	
	public String getHash(){
		return hash;
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
