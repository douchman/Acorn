package MainMenu.Login.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{
	
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/AcornDB/Restaurant.db";
	
	private List<TextField> listTxtField;
	
	private String selectQuery = "SELECT pw "+
			  					"FROM member "+
			  					"WHERE email = ?";
	
	private Connection conn;
	private PreparedStatement psmt;
	private CommonService commonServ;
	private FirstPageService fServ;
	
	public LoginServiceImpl() {
		listTxtField = new ArrayList<TextField>();
		commonServ = new CommonServiceImpl();
		fServ = new FirstPageServiceImpl();
	}
	@Override
	public void setTextFieldProperty(Parent root) {
		
		Button btnLogin = (Button)root.lookup("#loginBtn");
		TextField idField = (TextField)root.lookup("#emailTxtF");
		TextField pwField = (TextField)root.lookup("#pwField");
		
		btnLogin.setDisable(true);
		
		listTxtField.add(idField);
		listTxtField.add(pwField);
		
		for(TextField txtF : listTxtField) {
			txtF.textProperty().addListener((obs, newtxt,oldtxt)->{
				for(TextField txtF2 : listTxtField) {
					if(txtF2.getText().isEmpty()) {
						btnLogin.setDisable(true);
					}
					else {
						btnLogin.setDisable(false);
					}
				}
			});
		}
	}
	
	@Override
	public String LoginProc(Parent root) {
		// 로그인 프락을 void -> String 으로 변경
		TextField idField = (TextField)root.lookup("#emailTxtF");
		TextField pwField = (TextField)root.lookup("#pwField");
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
			psmt = conn.prepareStatement(selectQuery);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			psmt.setString(1, idField.getText());
			
			ResultSet rs = psmt.executeQuery();
			// DB에 입력한 아이디가 있다면 가져올 것임
			rs.next();
			String userPW = rs.getString("pw");
			// 아이디가 있다면 쿼리문이 실행되면서
			// 해당하는 아이디에 대한 pw 를 얻어온다.
			
			if(userPW.equals(pwField.getText())){
				// DB로부터 가져온 pw를 사용자가 입력한 pw와 같은지 확인
				commonServ.ErrorMsg("로그인 성공", "어서오세요"+idField.getText()+"님");
				// 여기서 메인페이지로 넘어가야한다.
				// 로그인 성공시 리턴으로 해당 아이디 넘김
				return idField.getText();
			}
			
			else {
				commonServ.ErrorMsg("로그인 실패", "비밀번호가 불일치");
			}
			
			conn.close();
			psmt.close();
			
		} catch (SQLException e) {
			//System.out.println("일치하는 아이디가 없습니다.");
			commonServ.ErrorMsg("로그인실패", "일치하는 아이디가 없습니다.");
		}
		
		
		return null;
		
	}
	
	@Override
	public void AdminLogin(Parent root) {
		
	   String adminQuery = "SELECT pw,admin_set  "+
				"FROM member "+
				"WHERE email = ?";
	
		TextField idField = (TextField)root.lookup("#emailTxtF");
		TextField pwField = (TextField)root.lookup("#pwField");
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
			psmt = conn.prepareStatement(adminQuery);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			psmt.setString(1, idField.getText());
			
			ResultSet rs = psmt.executeQuery();
			// DB에 입력한 아이디가 있다면 가져올 것임
			rs.next();
			String userPW = rs.getString("pw");
			// 아이디가 있다면 쿼리문이 실행되면서
			// 해당하는 아이디에 대한 pw 를 얻어온다.
			
			if(userPW.equals(pwField.getText())){
				// DB로부터 가져온 pw를 사용자가 입력한 pw와 같은지 확인
				
				if(rs.getInt("admin_set")==1) {
				commonServ.ErrorMsg("관리자 로그인", "어서오세요"+idField.getText()+"님");
				Stage stage = new Stage();
				fServ.showWindow(stage, "../../../Admin/AdminLogin/AdminPage.fxml");
				
				Stage stage2 = (Stage) root.getScene().getWindow();
				stage2.close();
				
				}
				else commonServ.ErrorMsg("관리자 로그인 실패", "권한이 없습니다");
				return;
			}
			
			else {
				commonServ.ErrorMsg("로그인 실패", "비밀번호가 불일치");
			}
		} catch (SQLException e) {
			//System.out.println("일치하는 아이디가 없습니다.");
			commonServ.ErrorMsg("로그인실패", "일치하는 아이디가 없습니다.");
		}
		
		
		
		
	}
}
