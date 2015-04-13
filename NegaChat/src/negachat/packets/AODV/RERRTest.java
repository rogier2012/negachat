package negachat.packets.AODV;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negachat.packets.*;
import negachat.packets.AODV.*;

public class RERRTest {
	
	private String source;
	private String destination;
	
	private String[] lostRoutes;
	
	private RERR packeti;
	private RERR packetii;
	
	private byte[] byteArray;
	
	@Before
	public void setUp() throws Exception {
		source = "HENKHENKHENKHENK";
		destination = "BERTBERTBERTBERT";
		lostRoutes = new String[]{"KEESKEESKEESKEES", "KAASKAASKAASKAAS", "JOOPJOOPJOOPJOOP", "GIJSGIJSGIJSGIJS"};
		
		packeti = new RERR(destination, lostRoutes);
		byteArray = packeti.toByteArray();
		packetii = new RERR(byteArray);
	}

	@Test
	public void test() {
		System.out.println(new String(byteArray));
		System.out.println(byteArray[Packet.TYPEINDEX]);
		System.out.println("source : " + packetii.getSource());
		System.out.println("destination : " + packetii.getDestination());
		System.out.println("lost routes:");
		System.out.println(packetii.getLostRoutes()[0]);
		System.out.println(packetii.getLostRoutes()[1]);
		System.out.println(packetii.getLostRoutes()[2]);
		System.out.println(packetii.getLostRoutes()[3]);
		
	}

}
