package MainMenu.FirstPage.Service;

import java.io.IOException;

import MainMenu.FirstPage.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPageServiceImpl implements FirstPageService {

	@Override
	public Parent showWindow(Stage s, String formPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(s);
		Controller ctrler = loader.getController();
		ctrler.setRoot(root);
		s.setResizable(false);
		s.show();
		
		return root;
	}

	@Override
	public Parent showWindow(Stage s, String formPath, String css) {
		// ���������� �ҷ����� �޼���
		// �α��������� �ҷ����� �޼���
		// �������������� �ڷ� ���� ������ ���� â�� ������ִ� �޼���
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(css).toString());
			s.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Controller ctrler = loader.getController();
		ctrler.setRoot(root);
		s.setResizable(false);
		s.show();
		
		return root;
	}
	
	

	public void WindowClose(ActionEvent event) {
		Parent root = (Parent)event.getSource();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	public void WindowClose(Parent root) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	
}
