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
	// �ܼ��ϰ� ���� ��Ʈ�ѷ����� �̴ϰ����� ��������ִ� ����
	@Override
	public void showMenu(MainPageController mainCon) {
		// ���� ������ ��Ʈ�ѷ����� ȣ��Ǿ �̴ϰ��� ���� �޴��� �����Ѵ�.
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
