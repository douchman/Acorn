package MainMenu.Login;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.FirstPage.Controller;
import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import MainMenu.Login.Service.LoginService;
import MainMenu.Login.Service.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable {
	private Parent root;
	private FirstPageService service;
	private LoginService loginServ;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		setTextProperty();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new FirstPageServiceImpl();
		loginServ = new LoginServiceImpl();
		
	}
	public void CreatAccount(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Creat/Creat.fxml", "../../Creat/Creat.css");
		service.WindowClose(e);
	}

	public void LoginBtnPressed(ActionEvent e) {
		loginServ.LoginProc(root);
		service.WindowClose(e);
	}
	public void setTextProperty() {
		loginServ.setTextFieldProperty(root);
	}

	public void AdminLogin(ActionEvent e) {
		loginServ.AdminLogin(root);
		service.WindowClose(e);
	}
}
