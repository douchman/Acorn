package MainMenu.Creat;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.FirstPage.Controller;
import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class CreatController extends Controller implements Initializable  {
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

	public void backtologin(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Login/login.fxml", "../../Login/login.css");
		service.WindowClose(e);
	}
}
