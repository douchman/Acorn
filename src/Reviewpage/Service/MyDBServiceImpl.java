package Reviewpage.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class MyDBServiceImpl implements MyDBService {
	final String DRIVER = "org.sqlite.JDBC";
	//final String DB = "jdbc:sqlite:src/Shop.db";
	//final String DB = "jdbc:sqlite:C:/자바취업반_이세근/Project/Shop.db";
	final String DB = "jdbc:sqlite:C:/AcornDB/Restaurant.db";
	int idx;
	
	//해당 필드를 찾아서 Map에 String값으로 반환(단, 하나 사용시 selectDB().get(0)으로 사용)
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

	//select를 위한 SQL문
	@Override
	public String InformationSQL(String shopId) {
		return  "select image, res.name, avg(grade), address1, address2, category, view\n" + 
				"from restaurant as res, reviewTable as rev, location as lo, menu as me, restaurant_rev as resrv\n" + 
				"where res.name_id = rev.shop_id and res.name_id = lo.name_id and res.category_id = me.category_id and res.name_id = resrv.name_id\n" + 
				"and res.name_id = " + shopId + "\n" + 
				"group by res.name;";
	}
	@Override
	public String MenuSQL(String shopId) {
		return	"select name, shop_menu, me.price\n" + 
				"from restaurant as res, menuTable as me\n" + 
				"where res.name_id = me.name_id\n" + 
				"and res.name_id = " + shopId + "\n" + 
				"group by shop_menu\n" + 
				"order by menu_id;";
	}
	@Override
	public String ReviewSQL(String shopId) {
		return "select email, review_id, mem.name, grade, writeday, imgURL, review\n" + 
				"from member as mem, reviewTable as rev\n" + 
				"where mem.email = rev.user_email\n" + 
				"and shop_id = " + shopId + ";";
	}
	@Override
	public String BookmarkSQL(String userId) {
		return "select email, name_id\n" + 
				"from bookmarkTable\n" + 
				"where email = '" + userId + "';";
	}
	@Override
	public String WriteSQL(String userId) {
		return "select email, name, substr(email, 1, instr(email,'@')-1), grade, review, writeday\n" + 
				"from member as mem, reviewTable as rev\n" + 
				"where mem.email = rev.user_email\n" + 
				"and email = '" + userId + "';";
	}
	@Override
	public String EditSQL(String reviewId) {
		return "select review_id, email, name, substr(email, 1, instr(email,'@')-1), grade review, writeday\n" + 
				"from member as mem, reviewTable as rev\n" + 
				"where mem.email = rev.user_email\n" + 
				"and review_id = " + reviewId + ";";
	}
	@Override
	public String EmailSQL(String shopId) {
		return "select email, substr(email, 1, instr(email,'@')-1)\n" + 
				"from member as mem, reviewTable as rev\n" + 
				"where mem.email = rev.user_email\n" + 
				"and shop_id = " + shopId + ";";
	}
	
	//식당)조회수 오르면 저장
	@Override
	public void insertView(String shopId) {
		String selectSQL = "select view from restaurant_rev where name_id = " + shopId + ";";
		int view = 0;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectSQL);
			while(rs.next())
				view = rs.getInt("view")+1;
			
			String updateSQL = "update restaurant_rev set view = " + view + " where name_id = " + shopId + ";";
			stmt.executeUpdate(updateSQL);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//식당)찜하면 저장
	@Override
	public void insertBookmark(String shopId, String userId) {	//isBookmark()실행 후 사용
		String selectSQL = "select idx from bookmarkTable;";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			//북마크테이블의 idx값 찾기
			idx = 0;
			ResultSet rs = stmt.executeQuery(selectSQL);
			while(rs.next())	//마지막 idx값
				idx = rs.getInt("idx")+1;	//최신 idx값
			String insertSQL = "insert into bookmarkTable values("+idx+","+ shopId +",'" + userId +"');";
			stmt.executeUpdate(insertSQL);
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//식당)찜 해제시 테이블에서 삭제
	@Override
	public void deleteBookmark(String userId) {	//isBookmark()실행 후 사용
		String selectSQL = "select idx from bookmarkTable where email = '" + userId + "';";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			idx = 0;
			ResultSet rs = stmt.executeQuery(selectSQL);
			while(rs.next())	//마지막 idx값
				idx = rs.getInt("idx");	//최신 idx값
			String deleteSQL = "delete from bookmarkTable where idx = '" + idx + "';";
			stmt.executeUpdate(deleteSQL);
		
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//리뷰작성
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
			while(rs.next())	//마지막 idx값
				idx = rs.getInt("review_id")+1;	//최신 idx값
			if(imgurl == null) {
				insertSQL = "insert into reviewTable(review_id, shop_id, user_email, grade, review, writeday) values("+idx+", "+shopId+", '"+userId+"', "+grade+", '"+text+"', datetime('now'));";
			}else
				insertSQL = "insert into reviewTable values("+idx+", "+shopId+", '"+userId+"', "+grade+", '"+text+"', '"+imgurl+"', datetime('now'));";
			stmt.executeUpdate(insertSQL);
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//writeday로 review_id를 반환하는 메소드
	@Override
	public String FindEditReview(String writeday, String userId) {
		String selectSQL = "select review_id from reviewTable where user_email = '" + userId + "' and writeday = '"+writeday+"';";
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
	//리뷰 수정
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
	//리뷰 삭제
	@Override
	public void DeleteReview(String reviewId) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			System.out.println(reviewId);
			String deleteSQL = "delete from reviewTable where review_id = " + reviewId +";";
			stmt.executeUpdate(deleteSQL);
		
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	/*	//사장님 저장
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
		String SQL = "insert into shopTable values("+(++idx)+",'버거버거버거','햄버거','서울특별시 도토리로 94번길 10','1층 버거버거버거',\r\n" + 
				"0,'버거버거버거입니다.','알립니다. 배달료~','구합니다 사람~','02-123-4567','000-00-00000','OOO');";
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
