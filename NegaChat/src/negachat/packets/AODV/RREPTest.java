package negachat.packets.AODV;

import org.junit.Before;
import org.junit.Test;

public class RREPTest {
	
	private String source;
	private String destination;
	
	private RREP packeti;
	private RREP packetii;
	
	private byte[] byteArray;
	
	@Before
	public void setUp() throws Exception {
		source = "HENKHENKHENKHENK";
		destination = "BERTBERTBERTBERT";
		
		packeti = new RREP(source, destination);
		byteArray = packeti.toByteArray();
		packetii = new RREP(byteArray);
	}

	@Test
	public void test() {
		System.out.println(new String(byteArray));
		System.out.println(byteArray[0]);
		System.out.println();
		System.out.println(packetii.getType());
		System.out.println(packetii.getSource());
		System.out.println(packetii.getDestination());
	}

}
