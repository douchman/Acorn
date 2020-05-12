package Mainmenu.MainPage.Service;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TopFieldServiceImpl implements TopFieldService {

	@Override
	public void WindowClose(MouseEvent event) {
		Parent root = (Parent)event.getSource();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
