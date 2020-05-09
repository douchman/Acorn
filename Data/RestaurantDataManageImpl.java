package MainPage.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MainPage.Restaurant;

public class RestaurantDataManageImpl implements RestaurantDataManage{
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/Users/class/Desktop/STUDY/DOTORI/data/Restaurant.db";
	
	Connection conn;
	
	public RestaurantDataManageImpl() {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			
		}
	}

	@Override
	public List<Restaurant> getRestaurant() {
		String sql = "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address " +
					 "FROM restaurant r, location l, menu m "+
					 "WHERE r.category_id = m.category_id "+
					 "AND r.name_id = l.name_id";

		 List<Restaurant> lstR = new ArrayList<Restaurant>();
		 
		 try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Restaurant restaurant = new Restaurant();
				
				restaurant.setName(rs.getString("name"));
				restaurant.setCategory(rs.getString("category"));
				restaurant.setPrice(rs.getInt("price"));
				restaurant.setBusinesshour(rs.getString("businesshour"));
				restaurant.setBreaktime(rs.getString("breaktime"));
				restaurant.setImage(rs.getString("image"));
				restaurant.setLoca_x(rs.getInt("loca_x"));
				restaurant.setLoca_y(rs.getInt("loca_y"));
				restaurant.setAddress(rs.getString("address"));
				
				lstR.add(restaurant);
			}
			
			rs.close();
			pStmt.close();
			conn.close();
			
		} catch (SQLException e) {
			return null;
		}
		
		return lstR;
	} 
	
	@Override
	public List<Restaurant> getRestaurant(int m) {
		String sql = "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address " +
				 "FROM restaurant r, location l, menu m "+
				 "WHERE r.category_id = m.category_id "+
				 "AND r.name_id = l.name_id " +
				 "AND r.category_id=" + m;

	
	 List<Restaurant> lstRm = new ArrayList<Restaurant>();
	 
	 try {
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()) {
			Restaurant restaurant = new Restaurant();
			
			restaurant.setName(rs.getString("name"));
			restaurant.setCategory(rs.getString("category"));
			restaurant.setPrice(rs.getInt("price"));
			restaurant.setBusinesshour(rs.getString("businesshour"));
			restaurant.setBreaktime(rs.getString("breaktime"));
			restaurant.setImage(rs.getString("image"));
			restaurant.setLoca_x(rs.getInt("loca_x"));
			restaurant.setLoca_y(rs.getInt("loca_y"));
			restaurant.setAddress(rs.getString("address"));
			
			lstRm.add(restaurant);
		}
		
		rs.close();
		pStmt.close();
		conn.close();
		
	} catch (SQLException e) {
		return null;
	}
	
	return lstRm;
	}
	
	public String rank() {
	
		return null;
	}


	
}
