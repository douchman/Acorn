package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class MyDBServiceImpl implements MyDBService {
	final String DRIVER = "org.sqlite.JDBC";
	final String DB = "jdbc:sqlite:src/Shop.db";
	//final String DB = "jdbc:sqlite:C:/�ڹ������_�̼���/Project/Shop.db";
	int idx;
	
	//�ش� �ʵ带 ã�Ƽ� Map�� String������ ��ȯ(��, �ϳ� ���� selectDB().get(0)���� ���)
	@Override
	public Map<Integer, String> selectDB(String SQL, String fieldname) {
		 Map<Integer, String> mapstr = new TreeMap<Integer, String>();
		 idx = 0;
			try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()) {
				mapstr.put(idx, rs.getString(fieldname));
				idx++;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return mapstr;
	}

	//select�� ���� SQL��
	@Override
	public String InformationSQL(String shopId) {
		
		
		return  "select imgURL, shop.shop_name, avg(grade), shop_address1, shop_address2, \r\n"
				+ " shop_kind, shop_view\r\n" +
				"from shopTable as shop, reviewTable as rev\r\n" +
				"where shop.shop_id = rev.shop_id and shop.shop_id = "
				+ shopId +"\r\n" + "group by shop.shop_name;";
	}
	@Override
	public String MenuSQL(String shopId) {
		return	"select shop_name, menu, price\r\n" + 
				"from shopTable as shop, menuTable as me\r\n" + 
				"where shop.shop_id = me.shop_id\r\n" + 
				"and shop.shop_id = " + shopId + "\r\n" + 
				"group by menu\r\n" +
				"order by menu_id;";
	}
	@Override
	public String ReviewSQL(String shopId) {
		return "select us.user_id, review_id, user_name, grade, writeday, imgURL, review\r\n" + 
				"from userTable as us, reviewTable as rev\r\n" + 
				"where us.user_id = rev.user_id\r\n" + 
				"and shop_id = " + shopId + ";";
	}
	@Override
	public String BookmarkSQL(String userId) {
		return "select user_id, shop_id\r\n" + 
				"from bookmarkTable\r\n" + 
				"where user_id = " + userId + ";";
	}
	@Override
	public String WriteSQL(String userId) {
		return "select rev.user_id, user_name, substr(user_email, 1, instr(user_email,'@')-1), grade, review, writeday\r\n" + 
				"from userTable as us, reviewTable as rev\r\n" + 
				"where us.user_id = rev.user_id\r\n" + 
				"and rev.user_id = " + userId + ";";
	}
	@Override
	public String EditSQL(String reviewId) {
		return "select review_id, rev.user_id, user_name, substr(user_email, 1, instr(user_email,'@')-1), grade, review, writeday\r\n" + 
				"from userTable as us, reviewTable as rev \r\n" + 
				"where us.user_id = rev.user_id\r\n" + 
				"and review_id = " + reviewId + ";";
	}
	@Override
	public String EmailSQL(String shopId) {
		return "select us.user_id,substr(user_email, 1, instr(user_email,'@')-1)\r\n" + 
				"from userTable as us, reviewTable as rev\r\n" + 
				"where us.user_id = rev.user_id\r\n" + 
				"and shop_id = " + shopId + ";";
	}
	
	//�Ĵ�)��ȸ�� ������ ����
	@Override
	public void insertView(String shopId) {
		String selectSQL = "select shop_view from shopTable where shop_id = " + shopId + ";";
		int view = 0;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectSQL);
			while(rs.next())
				view = rs.getInt("shop_view")+1;
			
			String updateSQL = "update shopTable set shop_view = " + view + " where shop_id = " + shopId + ";";
			stmt.executeUpdate(updateSQL);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�Ĵ�)���ϸ� ����
	@Override
	public void insertBookmark(String shopId, String userId) {	//isBookmark()���� �� ���
		String selectSQL = "select idx from bookmarkTable;";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			//�ϸ�ũ���̺��� idx�� ã��
			idx = 0;
			ResultSet rs = stmt.executeQuery(selectSQL);
			while(rs.next())	//������ idx��
				idx = rs.getInt("idx")+1;	//�ֽ� idx��
			String insertSQL = "insert into bookmarkTable values("+idx+","+ shopId +"," + userId +");";
			stmt.executeUpdate(insertSQL);
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�Ĵ�)�� ������ ���̺��� ����
	@Override
	public void deleteBookmark(String userId) {	//isBookmark()���� �� ���
		String selectSQL = "select idx from bookmarkTable where user_id = '" + userId + "';";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			idx = 0;
			ResultSet rs = stmt.executeQuery(selectSQL);
			while(rs.next())	//������ idx��
				idx = rs.getInt("idx");	//�ֽ� idx��
			String deleteSQL = "delete from bookmarkTable where idx = '" + idx + "';";
			stmt.executeUpdate(deleteSQL);
		
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����ۼ�
	@Override
	public void writeReview(String shopId, String userId, float grade, String text, String imgurl) {
		String selectSQL = "select review_id from reviewTable;";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			idx = 0;
			ResultSet rs = stmt.executeQuery(selectSQL);
			String insertSQL = "";
			while(rs.next())	//������ idx��
				idx = rs.getInt("review_id")+1;	//�ֽ� idx��
			if(imgurl == null) {
				insertSQL = "insert into reviewTable(review_id, shop_id, user_id, grade, review, writeday) values("+idx+", "+shopId+", "+userId+", "+grade+", '"+text+"', datetime('now'));";
			}else
				insertSQL = "insert into reviewTable values("+idx+", "+shopId+", "+userId+", "+grade+", '"+text+"', '"+imgurl+"', datetime('now'));";
			stmt.executeUpdate(insertSQL);
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//writeday�� review_id�� ��ȯ�ϴ� �޼ҵ�
	@Override
	public String FindEditReview(String writeday, String userId) {
		String selectSQL = "select review_id from reviewTable where user_id = " + userId + " and writeday = '"+writeday+"';";
		String review_id = "";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			if(rs.next())
				review_id = rs.getString("review_id");
			
			rs.close();
			stmt.close();
			conn.close();	
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return review_id;
	}
	//���� ����
	@Override
	public void EditReview(String reviewId, String text, String imgurl) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();

			String updateSQL = "Update reviewTable set review = '" + text + "', imgURL = '" + imgurl + "',"
					+ " writeday = datetime('now') where review_id = " + reviewId + ";";
			stmt.executeUpdate(updateSQL);
			
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//���� ����
	@Override
	public void DeleteReview(String reviewId) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			
			String deleteSQL = "delete from reviewTable where review_id = " + reviewId + ";";
			stmt.executeUpdate(deleteSQL);
		
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	/*	//����� ����
	private int focusIndex(String IndexFieldName) {
		String SQL = "select MAX("+IndexFieldName+") from shopTable;";
		int idx = 0;
		try {
			idx = stmt.executeUpdate(SQL);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return idx;
	}
	public void insertOwner(String shopName, String shopKind, String shopAddress1, String shopAddress2, int shopView,
				String shopNotice1, String shopNotice2, String shopNumber, String shopOwnerNumber, String shopOwner) {
		int idx = focusIndex("shop_id");
		String SQL = "insert into shopTable values("+(++idx)+",'���Ź��Ź���','�ܹ���','����Ư���� ���丮�� 94���� 10','1�� ���Ź��Ź���',\r\n" + 
				"0,'���Ź��Ź����Դϴ�.','�˸��ϴ�. ��޷�~','���մϴ� ���~','02-123-4567','000-00-00000','OOO');";
		try {
			stmt.executeUpdate(SQL);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	
}
