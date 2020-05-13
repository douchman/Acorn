package Minigame.Service;

import java.io.IOException;

import Minigame.MiniGameController;
import Minigame.BrkAcorn.BrkAcorn;
import Minigame.Ladder.Ladder;
import Minigame.Roulette.RouletteMenu;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MinigameService implements MinigameServiceImpl{
	
	@Override
	public void showMenu() {
		Stage stage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/MinigameMenu.fxml"));
		try {
			Parent root = loader.load();
			MiniGameController miniCon = loader.getController();
			miniCon.setRoot(root);
		
			stage.setScene(new Scene(root));
			stage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	@Override
	public void runRoulette(Parent root) {
		RouletteMenu showRuulette = new RouletteMenu();
		showRuulette.displayMenu();		
	}
	
	@Override
	public void runLadder(Parent root) {
		Ladder ladder = new Ladder();
		ladder.startLadder();
		
	}
	
	@Override
	public void runBrkAcorn(Parent root) {
		BrkAcorn brkAcorn = new BrkAcorn();
		brkAcorn.runBrkAcorn();
		
	}
}
