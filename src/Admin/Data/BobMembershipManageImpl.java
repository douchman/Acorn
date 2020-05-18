package Admin.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Admin.AdminLogin.AddAddress;
import Admin.AdminLogin.Member;



public class BobMembershipManageImpl implements IBobMembershipManage {
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:src/Restaurant.db";
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
		String sql = 	"INSERT INTO restaurant "+
				"(name, name_id, category_id, price, businesshour, breaktime, image) "+
				"VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, member.getName());
			pStmt.setString(2, member.getName_id());
			pStmt.setString(3, member.getCategory_id());
			pStmt.setString(4, member.getPrice());
			pStmt.setString(5, member.getBusinesshour());
			pStmt.setString(6, member.getBreaktime());
			pStmt.setString(7, member.getImage());
			
			
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
	
	@Override
	public boolean BobAddAddressProc(AddAddress member1) 
		{String sql = 	"INSERT INTO location "+
						"(name_id, loca_x, loca_y, address) "+
						"VALUES (?,?,?,?)";

	try {
		PreparedStatement pStmt1 = conn.prepareStatement(sql);

		pStmt1.setString(1, member1.getName_id());
		pStmt1.setString(2, member1.getLoca_x());
		pStmt1.setString(3, member1.getLoca_y());
		pStmt1.setString(4, member1.getAddress());

		pStmt1.executeUpdate();

		pStmt1.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		return false;
	}
	return true;
	}
}
