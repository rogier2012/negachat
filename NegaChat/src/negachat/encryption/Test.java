package negachat.encryption;

public class Test {
	public static void main(String[] args) {
		
		String test = "AIezMweXiGjBr8pbRQSqvcqxXXkt3INaLqqfbGi6G6q9Tp1Au3OfCMHsDU7u0nzL7v32ZK55IpVeVA8ph36MVjjLUvKTqbe2xuCobgrP29NZ4fuj2Kg8qECYKsXp46Rw";
		byte[] testbyte = test.getBytes();
		SymmetricEncrypter AES = new SymmetricEncrypter();
		
		byte[] encrypted = AES.Encrypt(testbyte);
		System.out.println("encrypted string:");
		String encryptedstring = new String(encrypted);
		System.out.println(encryptedstring);
		System.out.println("decrypted string:");
		byte[] decrypted = AES.Decrypt(encrypted);
		System.out.println(new String(decrypted));
		System.out.println(test);
		
		
		SymmetricEncrypter AES2 = new SymmetricEncrypter();
		byte[] exchange = AES.secretKeyToByte();
		AES2.setKey(exchange);
		
		System.out.println(new String(AES2.Decrypt(encrypted)));
		String keey = new String(AES.secretKeyToByte());
		System.out.println(keey.length());
		System.out.println(AES.getKey());
		
		System.out.println("DE GROTE RSA TEST");
		String rsatest = "OMG IK BEN ZO BENIEUWD OF DIT WERKT OMG OMG OMG";
		
		AssymetricEncrypter Ron = new AssymetricEncrypter();
		AssymetricEncrypter Gijs = new AssymetricEncrypter();
		
		System.out.println(rsatest);
		System.out.println(Ron.getKey());
		
		byte[] encryptedStringRon = Ron.Encrypt(test.getBytes());
//		byte[] encryptedStringGijs = Gijs.Encrypt(rsatest.getBytes(), Ron.getKey());
//		
//		System.out.println(new String(Gijs.Decrypt(encryptedStringRon)));
	}
}
