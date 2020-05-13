package Minigame.Roulette;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RankRoulette extends Roulette{
	
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB  = "jdbc:sqlite:C:/AcornDB/parkDB.db";
	String sql = "SELECT * "+
				 "FROM restaurant "
			     ;
	
	private FXMLLoader loader = new FXMLLoader(
			getClass().getResource("../FXML/rankMenu.fxml"));
	private Stage menu;
	private Parent root;
	
	private List<String> listContents;
	//private List<Arc> listRouletteArc;

	
	public RankRoulette(RouletteMenu rouletteMenu) {
		super(rouletteMenu);
		
		menu = new Stage();
		listContents = new ArrayList<String>();
		//listRouletteArc = new ArrayList<Arc>();
		setFuncBtn();
		try {
			root = loader.load();
			
			menu.setScene(new Scene(root));
			// �޴� ���������� . �� �޴� ���������� �� �귿 ��忡�� 
			// �귿 ��ü �������� �����ϰ��� �� �� �Ҹ��� �� ���̴�.
			//menu.show();
			//setContentsList();
			
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//super.makeRoulette();
		//super.show();
	}
	
	@Override
	public void runRoulette() {
		function();
	}
	
	
	public void readDB(String str) {
		try {
			initList();
			//String str1 = "res_name";
			//String str2 = "res_food";
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(DB);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//listRestaurant.add(rs.getString("res_name"));
				if(rs.getString(str)!=null) {
					//System.out.println(rs.getString("res_food"));
					listContents.add(rs.getString(str));
				}
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(listRestaurant.size());
		
	}
	
	@Override
	public void setContentsList() {
		super.setList(listContents);
	}
	
	@Override
	public void setFuncBtn() {
		super.setFuncBtnText("��ũ����");
	}
	@Override 
	public void function() {
		Button btnGrade = (Button)root.lookup("#btnGrade");
		Button btnGo = (Button)root.lookup("#btnGo");
		Button btnReview = (Button)root.lookup("#btnReview");
		
		btnGrade.setOnAction(e->{
			// ���������� �귿(�θ�Ŭ����) �������� �����ϴ� �޼���  ȣ��
			String str = "res_name";
			readDB(str);
			setContentsList();
			super.makeRoulette();
			super.show();
			menu.close();
			System.out.println("��ũ");
			
		});
		
		btnGo.setOnAction(e->{
			// GO! ������ �귿(�θ�Ŭ����) �������� �����ϴ� �޼���  ȣ��
			String str = "res_food";
			readDB(str);
			setContentsList();
			super.makeRoulette();
			super.show();
			menu.close();
			System.out.println("GO!");
		});
		
		btnReview.setOnAction(e->{
			// ��������� �귿(�θ�Ŭ����) �������� �����ϴ� �޼���  ȣ��
			//readDB();
			System.out.println("����");
		});
		
		menu.show(); 
		// �� �귿���� �ٸ��� ������ ��� ��ư�� ��������  
		// �ش� �귿�� �������� ���� �ϴ� �޴��� ȣ��ȴ�.
		
	}
	
	@Override
	public void initList() {
		listContents.clear();
		
	}
	
	
	
}
