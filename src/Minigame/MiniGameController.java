package Minigame;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.MainPage.MainPageController;
import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
// Minigame.MiniGameController
import javafx.stage.Stage;

public class MiniGameController implements Initializable{
	
	private Parent root;
	private MinigameServiceImpl gameService;
	private MainPageController mainCon;
	
	public void setRoot(Parent root){
		this.root = root;
	}
	public void setMainPageCon(MainPageController mainCon, MinigameService gameService) {
		this.mainCon = mainCon;
		this.gameService = gameService;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//gameService = new MinigameService();		
	}
	
	public void RouletteBtnPressed(ActionEvent event) {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		gameService.runRoulette(root, mainCon);
	}

	public void LadderBtnPressed(ActionEvent event) {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		gameService.runLadder(root);
		
	}
	public void BrkAcronBtnPressed(ActionEvent event) {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		gameService.runBrkAcorn(root);
	}
}
