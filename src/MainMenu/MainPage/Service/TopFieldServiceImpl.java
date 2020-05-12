package MainMenu.MainPage.Service;


import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TopFieldServiceImpl implements TopFieldService {

	
	@Override
	public void WindowClose(MouseEvent event) {
		Parent root = (Parent)event.getSource();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public String Tsearch(ActionEvent ae) {
		Parent root = (Parent)ae.getSource();
		TextField txt = (TextField)root.lookup("TextField");
		String Stxt = txt.getText();	

		return Stxt;
	}



}
