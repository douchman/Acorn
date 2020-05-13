package MainMenu.Creat;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.Creat.Service.CreatService;
import MainMenu.Creat.Service.CreatServiceImpl;
import MainMenu.FirstPage.Controller;
import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreatController extends Controller implements Initializable  {
	private Parent root;
	private FirstPageService service;
	private CreatService creatServ;
	private Button registBtn;
	// ȸ������ �������� ȸ������ ��ư
	// private TextField nameField,idField, pwField, pwChkField;
	// �̸�, ���̵�, �н�����, �н����� Ȯ�� �Է� �ʵ�
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		setTextFieldProperty();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new FirstPageServiceImpl();
		creatServ = new CreatServiceImpl();	
	}
	
	public void setTextFieldProperty() {
		creatServ.setTextProperty(root);
	}

	public void registBtnPressed(ActionEvent e) {
		creatServ.RegistProc(root);
	}
	
	public void backtologin(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Login/login.fxml", "../../Login/login.css");
		service.WindowClose(e);
	}
}
