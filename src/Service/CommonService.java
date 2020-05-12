package Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface CommonService {
	public Parent getRoot();
	public void CloseWindow(ActionEvent e);
	public void OpenWindow(Stage stage, String fxmlname, String title);
	public Parent ListForm(String fxmlname, boolean isCtr);
	public void ShowLabel(Parent form, String fxId, String labeltxt);
	public String getLabel(Parent form, String fxId);
	public boolean CheckMsgbox(String contents);
	public void Msgbox(String text);
	public FXMLLoader OpenReviewPage(Stage stage, String fxmlname, String title);
	//public FXMLLoader OpenReviewPage(Stage stage, String fxmlname, String title);
}
