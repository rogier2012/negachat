package negachat.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SymmetricEncrypter {
	private SecretKey SecKey;
	private final String ENCRYPTIONALGORITHM = "AES";
	Cipher AESCipher;
	private final String PASS = "donthackusplease";
	
	public SymmetricEncrypter() {
		createCipher();
		this.setKey(PASS.getBytes());
	}

	public void createCipher() {

		try {
			AESCipher = Cipher.getInstance(ENCRYPTIONALGORITHM);
			
		} catch (Exception e) {
			}	

	}
	
	public byte[] Encrypt(byte[] message){
		byte[] EncryptedMessage = null;
		try{
			AESCipher.init(Cipher.ENCRYPT_MODE, SecKey);
			EncryptedMessage = AESCipher.doFinal(message);
			return EncryptedMessage;
		} catch(Exception e){
			
		}
		return EncryptedMessage;
		
		
	}
	
	public byte[] Decrypt(byte[] message){
		byte[] DecryptedMessage = null;
		try{
			AESCipher.init(Cipher.DECRYPT_MODE, SecKey);
			DecryptedMessage = AESCipher.doFinal(message);
			return DecryptedMessage;
		} catch(Exception e){
			
		}
		return DecryptedMessage;
		
		
	}
	
	public SecretKey getKey(){
		return SecKey;
	}
	
	public byte[] secretKeyToByte(){
		return SecKey.getEncoded();
	}
	
	public void setKey(byte[] key){
		SecKey = new SecretKeySpec(key, 0, key.length, "AES");
	}
}
