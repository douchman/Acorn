package Minigame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.MainPage.MainPageController;
import Minigame.Roulette.RouletteMenuController;
import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.MinigameCommonService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
// Minigame.MiniGameController
import javafx.stage.Stage;

public class MiniGameController implements Initializable{
	
	private Parent root;
	private MainPageController mainCon;
	private MinigameCommonService commonService;
	
	public void setRoot(Parent root){
		this.root = root;
	}
	
	public void setMainPageCon(MainPageController mainCon) {
		this.mainCon = mainCon;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameServcie = new MinigameService();		
	}
	
	public void RouletteBtnPressed(ActionEvent event) throws IOException {
		commonService.closeWindow(root);
		// �귿�� �����̴�.
		Parent menuRoot;
		Stage menuStage = new Stage();
		FXMLLoader rouletteMenuLoader = new FXMLLoader(getClass().getResource("FXML/rouletteMenu.fxml"));
		RouletteMenuController rouletteMenuCon;
		// �귿�� �귿 �ȿ����� ��尡 �����Ƿ� �귿 �޴��� ���� �ε��ϰ�
		// �귿 �޴����� ��� ���� ���������� �����ϰԵȴ�.
		menuRoot = rouletteMenuLoader.load();
		rouletteMenuCon= rouletteMenuLoader.getController();
		rouletteMenuCon.setRoot(menuRoot);
		
		rouletteMenuCon.setMainCon(mainCon);
		
		menuStage.setScene(new Scene(menuRoot));
		menuStage.show();
	}
}
