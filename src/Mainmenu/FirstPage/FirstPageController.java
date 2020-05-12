package Mainmenu.FirstPage;

import java.net.URL;
import java.util.ResourceBundle;

import Mainmenu.FirstPage.Service.FirstPageService;
import Mainmenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FirstPageController extends Controller implements Initializable {
	@SuppressWarnings("unused")
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
	
	public void gotoMain(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../MainPage/MainPage.fxml", "../../MainPage/MainPage.css");
		service.WindowClose(e);

	}
	
	public void login() {
		
	}
	
}
