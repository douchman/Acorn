package Minigame.Ladder;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class LadderLabel extends Label{
	
	double centerX, centerY;
	
	public LadderLabel(TextField txtfield) {
		this.setText(txtfield.getText());
		this.setFont(new Font(15));
		this.setPrefSize(txtfield.getPrefWidth(), txtfield.getPrefHeight());
		this.setAlignment(Pos.CENTER);
		this.setLayoutX(txtfield.getLayoutX());
		this.setLayoutY(txtfield.getLayoutY());
		
		this.centerX = this.getLayoutX() + this.getPrefWidth()/2;
		this.centerY = this.getLayoutY();
	}
	
	public int getCenterX() {
		return (int)this.centerX;
	}
	public int getCneterY() {
		return (int)this.centerY;
	}
}
