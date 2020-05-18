package MainMenu.FirstPage.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface FirstPageService {
	public Parent showWindow(Stage s, String formPath);
	public Parent showWindow2(Stage s, String formPath);
	public Parent showWindow(Stage s, String formPath, String css);
	
	public void WindowClose(ActionEvent event);

}
