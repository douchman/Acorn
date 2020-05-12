package Minigame.BrkAcorn;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BrkAcorn {

	private Stage stage;
	private Parent root;
	private BrkAcornThread brkThread;
	private Button btnRetry, btnStart, btnAcorn;
	private ImageView imgAcorn;
	private Label time;
	private int clickCnt;
	private Image startImg;
	private BrkAcornImg brkImg;
	private int idx;
	
	public BrkAcorn() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/BrkAcorn.fxml"));
		stage= new Stage();
		startImg = new Image(getClass().getResourceAsStream("../image/brk0.png"));
		imgAcorn = new ImageView(startImg);
		
		imgAcorn.setFitWidth(200);
		imgAcorn.setFitHeight(200);
		brkImg = new BrkAcornImg();

		try {
			root = loader.load();
			stage.setScene(new Scene(root));
			//stage.show();
			setAction();
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		imgAcorn.setImage(startImg);
		btnRetry.setVisible(false);
		btnAcorn.setGraphic(imgAcorn);
		btnAcorn.setDisable(false);
		btnAcorn.setVisible(false);
		time.setText("Ready");
		clickCnt = 0;
		idx=0;
	}
	public void setAction() {
		btnStart = (Button)root.lookup("#btnStart");
		btnRetry = (Button)root.lookup("#btnRetry");
		btnAcorn = (Button)root.lookup("#btnAcorn");
		time = (Label)root.lookup("#time");
		time.setText("Ready");
		
		btnRetry.setOnAction(e->{
			btnRetry.setVisible(false);
			btnStart.setVisible(true);
			initialize();
		});
		
		btnStart.setOnAction(e->{
			btnAcorn.setVisible(true);
			btnStart.setVisible(false);
			btnRetry.setVisible(false);
			
			brkThread = new BrkAcornThread(this);
			brkThread.start();			
		});
		btnAcorn.setOnAction(e->{
			clickCnt++;
			setImage();
		});
	}
	
	public void gameSet() {
		btnAcorn.setDisable(true);
		btnRetry.setVisible(true);
	}
	
	public void setImage() {
		switch (clickCnt) {
		case 10:
			imgAcorn.setImage(brkImg.listImage.get(idx));
			btnAcorn.setGraphic(imgAcorn);
			idx++;
			break;
		case 20:
			imgAcorn.setImage(brkImg.listImage.get(idx));
			btnAcorn.setGraphic(imgAcorn);
			idx++;
			break;
		case 30:
			imgAcorn.setImage(brkImg.listImage.get(idx));
			btnAcorn.setGraphic(imgAcorn);
			idx++;
			break;
		case 40:
			imgAcorn.setImage(brkImg.listImage.get(idx));
			btnAcorn.setGraphic(imgAcorn);
			idx++;
			break;
		case 50:
			imgAcorn.setImage(brkImg.listImage.get(idx));
			btnAcorn.setGraphic(imgAcorn);
			idx++;
			break;
		case 55:
			imgAcorn.setImage(brkImg.listImage.get(idx));
			btnAcorn.setGraphic(imgAcorn);
			brkThread.setStop(false);
			//btnRetry.setVisible(true);
			break;
		}
	}

	public void setLabel(String str) {
		time.setText(str);
	}
	
	public void runBrkAcorn() {
		stage.show();
	}
	
}
