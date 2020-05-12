package Minigame.BrkAcorn;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BrkAcorn {

	private Stage stage;
	private Parent root;
	private BrkAcornThread brkThread;
	private Button btnRetry;
	private ImageView imgAcorn;
	
	public BrkAcorn() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/BrkAcorn.fxml"));
		stage= new Stage();
		try {
			root = loader.load();
			stage.setScene(new Scene(root));
			//stage.show();
			
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		btnRetry = (Button)root.lookup("#btnRetry");
		imgAcorn = (ImageView)root.lookup("#ImgAcorn");
		
		btnRetry.setOnAction(e->{
			
		});
		
		imgAcorn.setOnMouseClicked(e->{
			
		});
	}
	
	public void brkAcorn() {
		
		
	}
	
	
	public void runBrkAcorn() {
		stage.show();
	}
	
}
