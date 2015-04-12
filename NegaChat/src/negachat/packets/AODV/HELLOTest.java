package negachat.packets.AODV;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HELLOTest {

	private String source;
	
	private byte identifier;
	
	private HELLO packeti;
	private HELLO packetii;
	
	private byte[] byteArray;
	
	@Before
	public void setUp() throws Exception {
		source = "HENKHENKHENKHENK";
		identifier = 15;
		packeti = new HELLO(source, identifier);
		byteArray = packeti.toByteArray();
		packetii = new HELLO(byteArray);
	}

	@Test
	public void test() {
		System.out.println(new String(byteArray));
		System.out.println(byteArray[0]);
		System.out.println(byteArray[17]);
		System.out.println(byteArray[18]);
	}

}
