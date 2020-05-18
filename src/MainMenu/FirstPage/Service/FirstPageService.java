package MainMenu.FirstPage.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface FirstPageService {
	public Parent showWindow(Stage s, String formPath);
	public Parent showAdminPage(Stage s, String formPath);
	public Parent showWindow(Stage s, String formPath, String css);
	public Stage getMainPage(String formPath, String css,Stage firstPage);
	public Stage getLoginPage(String formPath, String css,Stage firstPage);
	public void WindowClose(ActionEvent event);

}
