package Minigame;

import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class tmpMain extends Application{
	
	private MinigameServiceImpl gameServcie;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		gameServcie = new MinigameService();	
		
		gameServcie.showMenu();

		
	}

	public static void main(String[] args) {

		launch(args);

	}

}
