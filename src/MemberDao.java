import java.util.ArrayList;

public class MemberDao {
	private DBUtil db = new DBUtil();
	
	public ArrayList<Member> getMembers() {
		String sql = "select * from member";
		return db.getMemberRows(sql);
	}
	public int insertSignup(String id, String pw,String nn) {
		String sql = "insert into reply set id = ?, pw = ?,nn= ?";
		return db.updateQuery(sql, id, pw,nn);
	}
	
}
