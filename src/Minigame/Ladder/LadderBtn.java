package Minigame.Ladder;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class LadderBtn extends Button{
	
	private double centerX, centerY;
	
	public LadderBtn(TextField txtfield) {
		this.setText(txtfield.getText());
		this.setFont(new Font(15));
		this.setPrefSize(txtfield.getPrefWidth(), txtfield.getPrefHeight());
		this.setLayoutX(txtfield.getLayoutX());
		this.setLayoutY(txtfield.getLayoutY());	
		
		this.centerX = this.getLayoutX() + this.getPrefWidth()/2;
		this.centerY = this.getPrefHeight();
	}
	public int getCenterX() {	
		return (int)centerX;	
	}
	
	public int getCenterY() {
		return (int)centerY;
	}
	
}
