package Minigame.Service;

import java.io.IOException;

import MainMenu.MainPage.MainPageController;
import Minigame.MiniGameController;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MinigameService implements MinigameServiceImpl{
	// 단순하게 메인 컨트롤러에서 미니게임을 실행시켜주는 서비스
	@Override
	public void showMenu(MainPageController mainCon) {
		// 메인 페이지 컨트롤러에서 호출되어서 미니게임 선택 메뉴를 구성한다.
		Stage stage = new Stage();	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/MinigameMenu.fxml"));
		
		try {
			Parent root = loader.load();
			MiniGameController miniCon = loader.getController();
			miniCon.setRoot(root);
			miniCon.setMainPageCon(mainCon);
			
			stage.setScene(new Scene(root));
			stage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}


	

}
