package Reviewpage.Review;

import java.io.IOException;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application{

	@Override
	public void start(Stage primaryStage) throws IOException {
		CommonService comserv = new CommonServiceImpl();
		comserv.OpenWindow(primaryStage, "../Review/StartPage.fxml", "StartPage");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
