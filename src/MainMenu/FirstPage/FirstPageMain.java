package MainMenu.FirstPage;

import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPageMain extends Application {

	private FXMLLoader loader;
	private Scene sc;
	private Parent root;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 박상현 수정
		loader = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
		root = loader.load();
		FirstPageController firstCon = loader.getController();
		sc = new Scene(root);
		sc.getStylesheets().add(
				getClass().getResource("FirstPage.css").toString());
				
		primaryStage.setScene(sc);
		firstCon.setRoot(root);
		firstCon.setFirstPage(primaryStage);	
		primaryStage.show();
				
				
				
				
		//FirstPageService service = new FirstPageServiceImpl();
		//service.showWindow(primaryStage, "../FirstPage.fxml", "../FirstPage.css");
				
	}
	public static void main(String[] args) {
		launch(args);
	}
}
