package ReviewPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Service.CommonServiceImpl;
import Service.MyDBService;
import Service.MyDBServiceImpl;
import Service.CommonService;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application{

	
	List<Restaurant> listRestaurant;
	MyDBService mydb ;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		CommonService comserv = new CommonServiceImpl();
		Management manage = new Management();
		comserv.OpenWindow(primaryStage, "/ReviewPackage/StartPage.fxml", "StartPage");
		
		mydb = new MyDBServiceImpl();
		
		listRestaurant = new ArrayList<Restaurant>();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
