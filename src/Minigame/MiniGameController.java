package Minigame;

import java.net.URL;
import java.util.ResourceBundle;

import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
// Minigame.MiniGameController
import javafx.stage.Stage;

public class MiniGameController implements Initializable{
	
	private Parent root;
	private MinigameServiceImpl gameServcie;
	
	public void setRoot(Parent root){
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameServcie = new MinigameService();		
	}
	
	public void RouletteBtnPressed(ActionEvent event) {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		gameServcie.runRoulette(root);
	}

	public void LadderBtnPressed(ActionEvent event) {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		gameServcie.runLadder(root);
		
	}
}
