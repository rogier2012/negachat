package negachat.client;

import java.util.Map;

public class IPNicknameTable {
	
//	Format Map<String NickName, String IP>
	
	private Map<String, String> table;
	
	public String getIP(String nickName) {
		return table.get(nickName);
	}
	
	public String getNickName(String IP) {
		return null;
	}

	public boolean contains(String nickName) {
		return table.containsKey(nickName);
	}
}
