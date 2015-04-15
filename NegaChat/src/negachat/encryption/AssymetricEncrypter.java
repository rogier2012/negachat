package negachat.encryption;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AssymetricEncrypter {
	Cipher RSACipher;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private final String ALGORITHM = "RSA";

	public AssymetricEncrypter() {
		createCipher();
		generateKeys();
	}

	public void generateKeys() {

		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyGen.initialize(1024);
			KeyPair keyPair = keyGen.generateKeyPair();
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public void createCipher() {

			try {
				RSACipher = Cipher.getInstance(ALGORITHM);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				e.printStackTrace();
			}


	}

	public byte[] Encrypt(byte[] message) {
		byte[] EncryptedMessage = null;
		try {
			RSACipher.init(Cipher.ENCRYPT_MODE, publicKey);
			try {
				EncryptedMessage = RSACipher.doFinal(message);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		} catch (InvalidKeyException e) {
		}
		return EncryptedMessage;
	}

	public byte[] Decrypt(byte[] message) {
		byte[] DecryptedMessage = null;
			try {
				RSACipher.init(Cipher.DECRYPT_MODE, privateKey);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DecryptedMessage = RSACipher.doFinal(message);
				return DecryptedMessage;
				
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				return "could not decrypt".getBytes();
			}
			
		}
	

	public byte[] Encrypt(byte[] message, PublicKey pubKey) {
		byte[] EncryptedMessage = null;
		try {
			RSACipher.init(Cipher.ENCRYPT_MODE, pubKey);
			try {
				EncryptedMessage = RSACipher.doFinal(message);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		} catch (InvalidKeyException e) {
		}
		return EncryptedMessage;
	}

	public PublicKey getKey() {
		return publicKey;
	}
	
	public void setKey(PublicKey publicKey){
		this.publicKey = publicKey;
	}

}
