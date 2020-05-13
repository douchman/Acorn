package Reviewpage.Service;

import java.io.IOException;
import java.util.Optional;

import Reviewpage.Review.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{
	private Parent form;
	Controller ctrler;
	Alert alert;
	public CommonServiceImpl() {
		
	}
	//OpenWindow메소드에서 부족한 루트 반환값
	@Override
	public Parent getRoot() {
		return form;
	}
	//기존창 닫기
	@Override
	public void CloseWindow(ActionEvent e) {
		Parent root = (Parent)e.getSource();
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}
	//새창 열기
	
	
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
		
		ctrler = loader.getController();
		ctrler.setRoot(form);
		
		stage.show();
	}
	
	@Override
	public FXMLLoader OpenReviewPage(Stage stage, String fxmlname, String title) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlname));
		form = null;
		try {
			form = loader.load();
			
			stage.setTitle(title);
			stage.setScene(new Scene(form));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		ctrler = loader.getController();
		ctrler.setRoot(form);
		
		
		stage.show();
		
		return loader;
	}
	
	//OpenWindow와 달리 루트값을 반환하는 메소드(단, stage설정 없음)
	@Override
	public Parent ListForm(String fxmlname, boolean isCtr) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlname));
		form = null;
		try {
			form = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(isCtr) {
			ctrler = loader.getController();
			ctrler.setRoot(form);
			
		}
		
		return form;
	}
	//라벨입력
	@Override
	public void ShowLabel(Parent form, String fxId, String labeltxt) {
		Label lbl = (Label)form.lookup(fxId);
		lbl.setText(labeltxt);
	}
	//라벨출력
	@Override
	public String getLabel(Parent form, String fxId) {
		Label lbl = (Label)form.lookup(fxId);
		return lbl.getText();
	}
	//확인, 취소 버튼이 있는 메세지창
	@Override
	public boolean CheckMsgbox(String contents) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("경고");
		alert.setContentText(contents);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK)
			return true;
		else 
			return false;
	}
	//알리기 위한 메세지창
	@Override
	public void Msgbox(String text) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setContentText(text);
		alert.show();
	}
	
}
