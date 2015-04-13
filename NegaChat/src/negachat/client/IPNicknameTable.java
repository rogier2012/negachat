package negachat.client;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;


public class IPNicknameTable {
	
//	Format Map<String NickName, String IP>
	
	private static Map<String, InetAddress> table;
	
	public static byte[] getMyIP()	{
		NetworkInterface in;
		Enumeration<InetAddress> hallo = null;
		try {
			in = NetworkInterface.getByName("wlan0");
			hallo = in.getInetAddresses();
			hallo.nextElement();
		} catch	(SocketException e){
			e.printStackTrace();
		}
		return hallo.nextElement().getAddress();
	}
	
	
	
	public static InetAddress getIP(String nickName) {
		return table.get(nickName);
	}

	public boolean contains(String nickName) {
		return table.containsKey(nickName);
	}
	
	public Map<String, InetAddress> getTable() {
		return table;
	}
	
	public static void add(String nickName, InetAddress IP) {
		table.put(nickName, IP);
	}
	
}
