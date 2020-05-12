package Reviewpage.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface CommonService {
	public Parent getRoot();
	public void CloseWindow(ActionEvent e);
	public void OpenWindow(Stage stage, String fxmlname, String title);
	public Parent ListForm(String fxmlname);
	
}
