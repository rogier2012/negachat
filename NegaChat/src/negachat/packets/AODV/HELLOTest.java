package negachat.packets.AODV;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HELLOTest {

	private String source;
	
	
	private HELLO packeti;
	private HELLO packetii;
	
	private byte[] byteArray;
	
	@Before
	public void setUp() throws Exception {
		source = "HENKHENKHENKHENK & RON!";
		packeti = new HELLO(source);
		byteArray = packeti.toByteArray();
		packetii = new HELLO(byteArray);
	}

	@Test
	public void test() {
		System.out.println(new String(byteArray));
		System.out.println(byteArray[0]);
		System.out.println(byteArray[17]);
		System.out.println(packetii.getSource());
		
	}

}