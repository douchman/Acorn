package MainMenu.MainPage.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MainMenu.MainPage.Restaurant;

public class RestaurantDataManageImpl implements RestaurantDataManage{
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/AcornDB/Restaurant.db";
	
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
		String sql = "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address, avg(rt.grade), r.name_id " +
					 "FROM restaurant r, location l, menu m, reviewTable rt "+
					 "WHERE r.category_id = m.category_id "+
					 "AND r.name_id = l.name_id " +
				     "AND r.name_id = rt.shop_id "+
				     "GROUP by r.name_id";

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
				restaurant.setRate(rs.getInt("avg(rt.grade)"));
				restaurant.setRid(rs.getInt("name_id"));
				
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
		String sql = "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address, avg(rt.grade), r.name_id " +
				 "FROM restaurant r, location l, menu m, reviewTable rt "+
				 "WHERE r.category_id = m.category_id "+
				 "AND r.name_id = l.name_id " +
				 "AND r.name_id = rt.shop_id "+
				 "AND r.category_id=" + m +
				 " GROUP by r.name_id";

		if(m==0)
			sql =   "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address, avg(rt.grade), r.name_id "+
					"FROM restaurant r, location l, menu m, reviewTable rt "+
					"WHERE r.category_id = m.category_id "+
					"AND r.name_id = l.name_id "+
					"AND r.name_id = rt.shop_id "+
					"GROUP by r.name_id "+
					"ORDER by avg(rt.grade) DESC "+
					"LIMIT 10";
		
		if(m==8)
			sql =   "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address, avg(rt.grade), count(rt.review), r.name_id "+
					"FROM restaurant r, location l, menu m, reviewTable rt "+
					"WHERE r.category_id = m.category_id "+
					"AND r.name_id = l.name_id "+
					"AND r.name_id = rt.shop_id "+
					"GROUP by r.name_id "+
					"ORDER by count(rt.review) DESC "+
					"LIMIT 10";
		
		
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
			restaurant.setRate(rs.getInt("avg(rt.grade)"));
			restaurant.setRid(rs.getInt("name_id"));
			restaurant.setRid(rs.getInt("name_id"));
			
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

	@Override
	public List<Restaurant> getRestaurant(String txt) {
		String sql = "SELECT r.name, m.category, r.price, r.businesshour, r.breaktime, r.image, l.loca_x, l.loca_y, l.address, k.keyword, avg(rt.grade), r.name_id "+
				"FROM restaurant r, location l, menu m, rKeyword k, reviewTable rt "+
				"WHERE r.category_id = m.category_id "+
				"AND r.name_id = l.name_id "+
				"AND r.name_id = k.name_id "+
				"AND r.name_id = rt.shop_id "+
				"AND k.keyword like ? "+
				"GROUP by r.name_id";

	 List<Restaurant> lstRm = new ArrayList<Restaurant>();
	 
	 try {
		PreparedStatement pStmt = conn.prepareStatement(sql);
	
		pStmt.setString(1,"%"+txt+"%");
		
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
			restaurant.setRate(rs.getInt("avg(rt.grade)"));
			restaurant.setRid(rs.getInt("name_id"));
			
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
	
	
}
