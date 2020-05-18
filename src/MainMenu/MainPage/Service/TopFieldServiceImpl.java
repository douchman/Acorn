package MainMenu.MainPage.Service;


import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

	
	@Override
	public void setLoginBtn(String str, Parent root) {
		// �ӽø޼��� ����
		Button btnLogin = (Button)root.lookup("#TloginBtn");
		btnLogin.setText(str);
		btnLogin.setPrefWidth(200);
		btnLogin.setDisable(true);
		
	}
	
	@Override
	public void LogoutProc(String str, Parent root) {
		// �ӽø޼��� ����
		Button btnLogin = (Button)root.lookup("#TloginBtn");
		
		
		btnLogin.setText(str);
		btnLogin.setPrefWidth(200);
		btnLogin.setDisable(false);
		
	}
	@Override
	public void wReview(Parent root) {
		int rid=0;
		@SuppressWarnings("unchecked")
		ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
		
		if(lv.getSelectionModel().getSelectedItem()==null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("���並 �ۼ��� �� �����ϴ�");
			alert.setContentText("����Ʈ���� ���並 �ۼ��� �Ĵ��� �������ּ���");
			alert.showAndWait();
		}
		else 
			System.out.println(lv.getSelectionModel().selectedItemProperty().get().getUserData());
	}


}
