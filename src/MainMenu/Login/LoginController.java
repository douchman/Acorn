package MainMenu.Login;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.FirstPage.Controller;
import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable {
	private Parent root;
	private FirstPageService service;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new FirstPageServiceImpl();
		
	}
	public void CreatAccount(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Creat/Creat.fxml", "../../Creat/Creat.css");
		service.WindowClose(e);
	}

	

	
}
