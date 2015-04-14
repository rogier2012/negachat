package negachat.packets.AODV;

import negachat.packets.Packet;

import org.junit.Before;
import org.junit.Test;

public class RREQTest {
	
	private String source;
	private String destination;
	
	private byte identifier;
	private byte lifespan;
	
	private RREQ packeti;
	private RREQ packetii;
	
	private byte[] byteArray;
	
	@Before
	public void setUp() throws Exception {
		source = "HENKHENKHENKHENK";
		destination = "BERTBERTBERTBERT";
		identifier = 29;
		lifespan = 100;
		
		packeti = new RREQ(destination, lifespan, identifier);
		packeti.setSource(source);
		packeti.setDestination(destination);
		byteArray = packeti.toByteArray();
		packetii = new RREQ(byteArray);
	}

	@Test
	public void test() {
		System.out.println(byteArray.length);
		System.out.println();
		System.out.println(byteArray[Packet.TYPEINDEX]);
		System.out.println(new String(byteArray));
		System.out.println(byteArray[RREQ.LIFESPANINDEX]);
		System.out.println(byteArray[RREQ.IDENTIFIERINDEX]);
		System.out.println();
		System.out.println(packetii.getSource());
		System.out.println(packetii.getDestination());
		System.out.println(packetii.getLifeSpan());
		System.out.println(packetii.getIdentifier());
		
	}

}
