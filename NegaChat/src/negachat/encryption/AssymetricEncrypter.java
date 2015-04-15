package negachat.encryption;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AssymetricEncrypter {
	private static Cipher RSACipher;
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

	public static byte[] Decrypt(byte[] message, PrivateKey pkey) {
		byte[] DecryptedMessage = null;
		try {
			RSACipher.init(Cipher.DECRYPT_MODE, pkey);
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

	public byte[] Encrypt(String message, PublicKey pubKey) {
		byte[] EncryptedMessage = null;
		try {
			RSACipher.init(Cipher.ENCRYPT_MODE, pubKey);
			try {
				EncryptedMessage = RSACipher.doFinal(message.getBytes());
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

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public byte[] wrapKey() {
		return publicKey.getEncoded();
	}

	public static PublicKey unwrapKey(byte[] encodedKey) {
		try {
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(
					new X509EncodedKeySpec(encodedKey));
			return publicKey;
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

}
