package Reviewpage.Service;

import java.io.IOException;

import Reviewpage.Review.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{
	Parent form;
	@Override
	public Parent getRoot() {
		return form;
	}
	@Override
	public void CloseWindow(ActionEvent e) {
		Parent root = (Parent)e.getSource();
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}
	@Override
	public void OpenWindow(Stage stage, String fxmlname, String title) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlname));
		form = null;
		try {
			form = loader.load();
			stage.setTitle(title);
			stage.setScene(new Scene(form));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		Controller ctrler = loader.getController();
		ctrler.setRoot(form);
		
		stage.show();
	}
	@Override
	public Parent ListForm(String fxmlname) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlname));
		try {
			form = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return form;
	}
}
