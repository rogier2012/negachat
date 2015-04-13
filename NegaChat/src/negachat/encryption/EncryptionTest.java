package negachat.encryption;

public class EncryptionTest {
	
	
	public static void main(String[] args) {
		
		//Message of 128 bytes
		String message1 = "AIezMweXiGjBr8pbRQSqvcqxXXkt3INaLqqfbGi6G6q9Tp1Au3OfCMHsDU7u0nzL7v32ZK55IpVeVA8ph36MVjjLUvKTqbe2xuCobgrP29NZ4fuj2Kg8qECYKsXp46Rw";
		//Random message of our names
		String message2 = "RONGIJSCHRISTIAANROGIER";
		
		AssymetricEncrypter Ron = new AssymetricEncrypter();
		AssymetricEncrypter Gijs = new AssymetricEncrypter();
		AssymetricEncrypter Christiaan = new AssymetricEncrypter();
		SymmetricEncrypter aes = new SymmetricEncrypter();
				
		byte[] encryptedMessage1ToRon = Christiaan.Encrypt(message1.getBytes(), Ron.getKey());
		byte[] encryptedMessage2ToGijs = Ron.Encrypt(message2.getBytes(), Gijs.getKey());
		//not encrypted with Christiaans public key but with Gijs' key
		byte[] encryptedMessage2ToChristiaan = Gijs.Encrypt(message2.getBytes(), Ron.getKey());
		byte[] encryptedMessage2 = aes.Encrypt(message2.getBytes());
		
		System.out.println("Message 1 = " + message1);
		System.out.println("Message 2 = " + message2);
		
		System.out.println("Ron decrypts message1:");
		System.out.println(new String(Ron.Decrypt(encryptedMessage1ToRon)));
		
		System.out.println("Gijs decrypts message2:");
		System.out.println(new String(Gijs.Decrypt(encryptedMessage2ToGijs)));
		
		System.out.println("Christiaan decrypts message 3");
		System.out.println(new String(Christiaan.Decrypt(encryptedMessage2ToChristiaan)));
		
		System.out.println("Decrypting message 2 with AES implementation");
		System.out.println(new String(aes.Decrypt(encryptedMessage2)));
		String hash;
		
	}
}
