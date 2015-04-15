package negachat.encryption;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

public class EncryptionTest {

	public static void main(String[] args) {

		// Message of 117 bytes
		String message1 = "AIezMweXiGjBr8pbRQSqvcqxXXkt3INaLqqfbGi6G6q9Tp1Au3OfCMHsDU7u0nzL7v32ZK55IpVeVA8ph36MVjjLUvKTqbe2xuCobgrP29NZ4fuj2Kg8q";

		// Random message of our names
		String message2 = "RONGIJSCHRISTIAANROGIER";
		// Message of 128 bytes
		String message3 = "chrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischrischr";
		AssymetricEncrypter Ron = new AssymetricEncrypter();
		AssymetricEncrypter Gijs = new AssymetricEncrypter();
		AssymetricEncrypter Christiaan = new AssymetricEncrypter();
		SymmetricEncrypter aes = new SymmetricEncrypter();

		byte[] encryptedMessage1ToRon = Christiaan.Encrypt(message1.getBytes(),
				Ron.getKey());
		byte[] encryptedMessage2ToGijs = Ron.Encrypt(message2.getBytes(),
				Gijs.getKey());
		byte[] encryptedMessage2ToChristiaan = Gijs.Encrypt(
				message2.getBytes(), Ron.getKey());
		byte[] encryptedAESmessage = aes.Encrypt(message3.getBytes());

		System.out.println("Message 1 = " + message1);
		System.out.println("Message 2 = " + message2);
		System.out.println("--------------------------------");
		System.out.println("Ron decrypts message1:");
		System.out.println(new String(Ron.Decrypt(encryptedMessage1ToRon,
				Ron.getPrivateKey())));
		System.out.println("--------------------------------");
		System.out.println("Gijs decrypts message2:");
		System.out.println(new String(Gijs.Decrypt(encryptedMessage2ToGijs,
				Gijs.getPrivateKey())));
		System.out.println("--------------------------------");
		System.out.println("Christiaan decrypts message 3");
		System.out.println(new String(Christiaan.Decrypt(
				encryptedMessage2ToChristiaan, Christiaan.getPrivateKey())));

		System.out.println("--------------------------------");
		System.out.println("AES DECRYPTS MESSAGE 3 AS: ");
		System.out.println(new String(aes.Decrypt(encryptedAESmessage)));
		AssymetricEncrypter bob = new AssymetricEncrypter();
		System.out.println("--------------------------------");
		String testcase1 = "aa";
		String testcase2 = "sprhYgLiG8bytmYAadwaISVFozxt6S78WDraUX6UcnPXZA3DzWmQ1GpzPm71HBq39gfaWyGVaeucBmemLi7XDwHgmlUrbcyAxrgzhi8o1PBtzvZczCz6m";
		byte[] t1e = bob.Encrypt(testcase1.getBytes());
		byte[] t2e = bob.Encrypt(testcase2.getBytes());
		System.out.println("Testcase 1 heeft een grootte: "
				+ testcase1.length() + " maar een encrypted byte[] size van "
				+ t1e.length);
		System.out.println("Testcase 2 heeft een grootte: "
				+ testcase2.length() + " maar een encrypted byte[] size van "
				+ t2e.length);
		System.out.println("--------------------------------");
		PublicKey test = AssymetricEncrypter.unwrapKey(bob.wrapKey());
		byte[] test1 = bob.Encrypt(testcase1, test);
		System.out.println(new String(AssymetricEncrypter.Decrypt(test1,
				bob.getPrivateKey())));
	}

}
