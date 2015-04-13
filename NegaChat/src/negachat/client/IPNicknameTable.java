package negachat.client;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;


public class IPNicknameTable {
	
//	Format Map<String NickName, String IP>
	
	private static Map<String, String> table;
	

	
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
	
	
	
	public static String getIP(String nickName) {
		return table.get(nickName);
	}

	public boolean contains(String nickName) {
		return table.containsKey(nickName);
	}
	
	public Map<String, String> getTable() {
		return table;
	}
	
	public static void add(String nickName, String IP) {
		table.put(nickName, IP);
	}
	
}
