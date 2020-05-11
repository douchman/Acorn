package Admin.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import AdminLogin.Member;

public class BobMembershipManageImpl implements IBobMembershipManage {
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/DB/ProjectDB.db";
	Connection conn;
	public BobMembershipManageImpl() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean BobMembershipProc(Member member) {
		String sql = 	"INSERT INTO Bob "+
						"(BobNum, BobImage, BobName, BobPhone, BobAddress, BobLocate, BobMain, Category, Date) "+
						"VALUES (?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, member.getBobNum());
			pStmt.setString(2, member.getBobImage());
			pStmt.setString(3, member.getBobName());
			pStmt.setString(4, member.getBobPhone());
			pStmt.setString(5, member.getBobAddress());
			pStmt.setString(6, member.getBobLocate());
			pStmt.setString(7, member.getBobMain());
			pStmt.setString(8, member.getCategory());
			pStmt.setString(9, member.getDate());
			
			
			pStmt.executeUpdate();
			
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int DelProc(String BobNum) {
		String sql = 	"delete from Bob "+
						"WHERE BobNum=?";
		return 0;
		
	}

}
