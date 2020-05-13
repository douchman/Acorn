package MainMenu.Creat.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreatServiceImpl implements CreatService {

	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/AcornDB/Member.db";
	Parent root;
	private List<TextField> list_txtField;
	private TextField nameField,idField, pwField, pwChkField;
	private Alert alert;
	private String insertQuery;
	
	Connection conn;
	PreparedStatement psmt;
	public CreatServiceImpl() {
		insertQuery="INSERT INTO member "+
					"VALUES (?, ?, ?, ?); ";
		
		
		alert = new Alert(AlertType.INFORMATION);
		list_txtField = new ArrayList<TextField>();
		
	}
	
	@Override
	public void setTextProperty(Parent root) {
		Button regist = (Button)root.lookup("#creatBtn");
		regist.setDisable(true);
		nameField = (TextField)root.lookup("#nameTxtF");
		idField = (TextField)root.lookup("#emailTxtF");
		pwField = (TextField)root.lookup("#passwordTxtF");
		pwChkField = (TextField)root.lookup("#repasswordTxtF");
		
		list_txtField.add(nameField);
		list_txtField.add(idField);
		list_txtField.add(pwField);
		list_txtField.add(pwChkField);
		for(TextField txtField : list_txtField) {
			
			txtField.textProperty().addListener((obs, newtxt, oldtxt)->{
				for(TextField txtField2 : list_txtField) {
					if(!txtField2.getText().isEmpty()) {
						// ��� �Է� �ʵ尡 ��������� ��ư �ᱸ��
						regist.setDisable(false);					
					}
					else 
						// ��� �Է� �ʵ尡 ������� ������ ��ư ����
						regist.setDisable(true);
				}
			});
		}
	}
	
	@Override
	public void RegistProc(Parent root) {
		if(!pwField.getText().equals(pwChkField.getText())) {
			// �н����� �ʵ�� üũ�ʵ� �� �˻�
			showMessage("ȸ������ ����","�н����尡 ��ġ���� �ʽ��ϴ�.");	
			return;
		}
		else{
			// Jaranara@naver.com
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(DB);
				psmt = conn.prepareStatement(insertQuery);
				psmt.setString(1, nameField.getText());
				psmt.setString(2, idField.getText());
				psmt.setString(3, pwField.getText());
				psmt.setString(4, null);
				psmt.executeUpdate();
				
				showMessage("ȸ�����Լ���", "ȯ���մϴ�"+nameField.getText()+"��");
				conn.close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void showMessage(String title,String str) {
		alert.setHeaderText("�˸�"); 
		alert.setContentText(str);
		alert.show();
	}
	
	
	
	
}
