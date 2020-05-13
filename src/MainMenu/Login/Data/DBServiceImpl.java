package MainMenu.Login.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServiceImpl implements DBService {
	
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/AcornDB/Restaurant.db";
	
	Connection conn;

	public DBServiceImpl() {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			
		}
	}
}
