package Minigame.Roulette;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RouletteMenu {

	private Parent root;
	private Stage stage;
	private FXMLLoader loader;
	private CustomRulette customRoulette;
	private RankRoulette rkRoulette;
	private MenuRoulette menuRoulette;
	
	public RouletteMenu() {
		stage = new Stage();
		
		customRoulette = new CustomRulette(this);
		rkRoulette = new RankRoulette(this);
		menuRoulette = new MenuRoulette(this);
		
		loader = new FXMLLoader(getClass().
				getResource("../FXML/rouletteMenu.fxml"));
	
		try {
			root = loader.load();
			setBtnAction();
			stage.setScene(new Scene(root));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void displayMenu() {
		stage.show();
	}
	
	private void setBtnAction() {
		Button btnCustom = (Button)root.lookup("#btnCustom");
		Button btnRank = (Button)root.lookup("#btnRank");
		Button btnChoice = (Button)root.lookup("#btnChoice");
		
		btnCustom.setOnAction(e->{
			customRoulette.runRoulette();		
			stage.close();
		});
		
		btnRank.setOnAction(e->{
			rkRoulette.runRoulette();
			stage.close();
		});

		btnChoice.setOnAction(e->{
			menuRoulette.runRoulette();
			stage.close();
		});
	}
	
	
	
}
