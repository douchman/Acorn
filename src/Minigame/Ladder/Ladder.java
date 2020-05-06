package Minigame.Ladder;

import java.io.IOException;

import Minigame.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ladder {
	private FXMLLoader main;
	private Component compo;
	private Parent rootLadder;
	private Stage ladderStage;
	private AnchorPane ladderField;
	private Button btnAddSlot,btnClear;
	private HBox startField, endField;
	
	private int fieldCnt=0;
	// #startField   #endField
	public Ladder() {
		main = new FXMLLoader(getClass().getResource("../FXML/ladder.fxml"));
		compo = new Component();
		try {
		
			rootLadder = main.load();
			ladderStage = new Stage();
			ladderStage.setScene(new Scene(rootLadder));
	
			setComponent();		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setComponent() {
		startField = (HBox)rootLadder.lookup("#startField");
		endField = (HBox)rootLadder.lookup("#endField");
	
		btnAddSlot = (Button)rootLadder.lookup("#btnAddslot");
		btnClear = (Button)rootLadder.lookup("#btnClear");	
		
		ladderField = (AnchorPane)rootLadder.lookup("#ladderField");
		
		btnAddSlot.setOnAction(e->{
			addSlot();
		});
		
		btnClear.setOnAction(e->{
			clearField();
		});

	}
	
	private void clearField() {
		fieldCnt=0;
		btnAddSlot.setDisable(false);
		startField.getChildren().clear();
		endField.getChildren().clear();
	}
	
	private void addSlot() {
	
		fieldCnt++;
		if(fieldCnt >= 5) {
			btnAddSlot.setDisable(true);
		}
		startField.getChildren().add(compo.getTxtField());
		endField.getChildren().add(compo.getTxtField());		
		// ��ٸ� ����⸦ ������ 
		// ��� ���ۺκ��� ��ư���� �ٲٰ�
		// �ϴ� �κ��� �󺧷� �ٲپ ǥ�� �Ѵ�
		
	}

	private void makeLadder() {
		
	}
	
	public void startLadder() {
		ladderStage.show();
	}
}
