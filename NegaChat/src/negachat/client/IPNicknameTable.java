package negachat.client;

import java.util.Map;

public class IPNicknameTable {
	
//	Format Map<NickName, IP>
	
	private Map<String, String> table;
	
	public String getIP(String nickName) {
		return table.get(nickName);
	}
	
	public String getNickName(String IP) {
		return null;
	}
}
