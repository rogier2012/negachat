package negachat.packets.AODV;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negachat.packets.Packet;
import negachat.packets.AODV.*;

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
		
		packeti = new RREQ(source, destination, lifespan, identifier);
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
	}

}
