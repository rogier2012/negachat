package negachat.encryption;

import java.security.Key;
import java.security.KeyPairGenerator;

public class KeyPair {
	private Key privateKey;
	private Key publicKey;
	private final String ENCRYPTIONALGORITHM = "RSA";

	
	public KeyPair(){
		
	}
	
	private void createKeys() {
		
		try{
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(ENCRYPTIONALGORITHM);
			kpg.initialize(1024);
			KeyPair kp = kpg.generateKeyPair();
			
			
		} catch (Exception e){
		
		}
		
}
}

