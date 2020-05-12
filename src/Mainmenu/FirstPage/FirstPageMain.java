package Mainmenu.FirstPage;

import Mainmenu.FirstPage.Service.FirstPageService;
import Mainmenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class FirstPageMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FirstPageService service = new FirstPageServiceImpl();
		service.showWindow(primaryStage, "../FirstPage.fxml", "../FirstPage.css");
	}
	public static void main(String[] args) {
		launch(args);
	}
}
