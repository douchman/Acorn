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
		// 룰렛의 실행이다.
		Parent menuRoot;
		Stage menuStage = new Stage();
		FXMLLoader rouletteMenuLoader = new FXMLLoader(getClass().getResource("FXML/rouletteMenu.fxml"));
		RouletteMenuController rouletteMenuCon;
		// 룰렛은 룰렛 안에서도 모드가 있으므로 룰렛 메뉴를 먼저 로드하고
		// 룰렛 메뉴에서 모드 별로 독립적으로 실행하게된다.
		menuRoot = rouletteMenuLoader.load();
		rouletteMenuCon= rouletteMenuLoader.getController();
		rouletteMenuCon.setRoot(menuRoot);
		
		rouletteMenuCon.setMainCon(mainCon);
		
		menuStage.setScene(new Scene(menuRoot));
		menuStage.show();
	}
}
