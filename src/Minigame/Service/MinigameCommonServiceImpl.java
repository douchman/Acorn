package Minigame.Service;

import Minigame.ServiceImpl.MinigameCommonService;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MinigameCommonServiceImpl implements MinigameCommonService{

	@Override
	public void closeWindow(Parent root) {
		Stage stg = (Stage)root.getScene().getWindow();
		stg.close();
		
	}
	
	@Override
	public void openWindow(Parent root) {
		Stage stg = (Stage)root.getScene().getWindow();
		stg.show();
		
		
	}
}
