package negachat.client;

import java.util.Map;

public class IPNicknameTable {
	
//	Format Map<String NickName, String IP>
	
	private static Map<String, String> table;
	
	public static String getIP(String nickName) {
		return table.get(nickName);
	}

	public boolean contains(String nickName) {
		return table.containsKey(nickName);
	}
	
	public Map<String, String> getTable() {
		return table;
	}
	
	public void add(String nickName, String IP) {
		table.put(nickName, IP);
	}
	
}
