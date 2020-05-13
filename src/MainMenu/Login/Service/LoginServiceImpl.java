package MainMenu.Login.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginServiceImpl implements LoginService{
	
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/AcornDB/Member.db";
	
	private List<TextField> listTxtField;
	
	private String selectQuery = "SELECT pw "+
			  					"FROM member "+
			  					"WHERE email = ?";
	
	private Connection conn;
	private PreparedStatement psmt;
	private CommonService commonServ;
	
	public LoginServiceImpl() {
		listTxtField = new ArrayList<TextField>();
		commonServ = new CommonServiceImpl();
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
	public void LoginProc(Parent root) {
		
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
			// DB�� �Է��� ���̵� �ִٸ� ������ ����
			rs.next();
			String userPW = rs.getString("pw");
			// ���̵� �ִٸ� �������� ����Ǹ鼭
			// �ش��ϴ� ���̵� ���� pw �� ���´�.
			
			if(userPW.equals(pwField.getText())){
				// DB�κ��� ������ pw�� ����ڰ� �Է��� pw�� ������ Ȯ��
				commonServ.ErrorMsg("�α��� ����", "�������"+idField.getText()+"��");
				
			}
			
			else {
				commonServ.ErrorMsg("�α��� ����", "��й�ȣ�� ����ġ");
			}
		} catch (SQLException e) {
			//System.out.println("��ġ�ϴ� ���̵� �����ϴ�.");
			commonServ.ErrorMsg("�α��ν���", "��ġ�ϴ� ���̵� �����ϴ�.");
		}
		
		
		
		
	}
}
