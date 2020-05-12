package Minigame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Component {

	public Label getLabel(String txt, String color) {
			
		Label lbl = new Label(txt);
		lbl.setFont(new Font(15));
		lbl.setStyle("-fx-background-color: "+ color+";" +"-fx-font-weight: bold;");
		
		return lbl;
		
	}
	
	public Arc getArc(double x, double y, 
			double rx, double ry,
			double startAngle, double length,
			Color color) {
		
		Arc arc = new Arc();
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setRadiusX(rx);
		arc.setRadiusY(ry);
		arc.setStartAngle(startAngle);
		arc.setLength(length);
		arc.setType(ArcType.ROUND);
		arc.setStroke(Color.BLACK);
		arc.setFill(color);
		arc.toBack();
		
		return arc;
	}
	
	public Label getArcLabel(String str, double x, double y, double startAngle) {
		Label lbl = new Label(str);
		
		lbl.setFont(new Font(15));
		lbl.setLayoutX(x);
		lbl.setLayoutY(y);
		//System.out.println(lbl.getText()+"x: " + x);
		//System.out.println(lbl.getText()+"y: " + y);
		lbl.setStyle("-fx-font-size: 15pt;"+"-fx-font-weight : bold;");
		lbl.setRotate(startAngle);
		lbl.toFront();
		
		return lbl;
	}
		
	public Label getLadderLabel(TextField txtfield) {
		
		Label lbl = new Label(txtfield.getText());
		
		lbl.setFont(new Font(15));
		lbl.setPrefSize(txtfield.getPrefWidth(), txtfield.getPrefHeight());
		lbl.setAlignment(Pos.CENTER);
		lbl.setLayoutX(txtfield.getLayoutX());
		lbl.setLayoutY(txtfield.getLayoutY());
	
		return lbl;
	}
	
	public Line getLine(double startX, double startY,
						double endX, double endY) {
		Line line = new Line(startX, startY, endX, endY);
		line.setFill(Color.BLACK);
		line.setStrokeWidth(2);
			
		
		return line;
	}
	
	
	public Line getThreadLine(double startX, double startY,
			double endX, double endY) {
		Line line = new Line(startX, startY, endX, endY);
		//line.setStyle("-fx-stroke: red;");
		line.setStroke(Color.RED);
		line.setStrokeWidth(4);


		return line;
}
	
	public TextField getRouletteTxtField() {	
		TextField txtField = new TextField();
		txtField.setPromptText("ÄÁÅÙÃ÷ ÀÔ·Â");
		return txtField;
	}
	
	public TextField getLadderTxtField(double x, double y) {	
		TextField txtField = new TextField();
		txtField.setPrefSize(120, 30);
		txtField.setPromptText("ÄÁÅÙÃ÷ ÀÔ·Â");
		txtField.setLayoutX(x);
		txtField.setLayoutY(y);
		return txtField;
	}
	
	public Button getLadderButton(TextField txtfield) {

		Button btn = new Button(txtfield.getText());
		btn.setFont(new Font(15));
		btn.setPrefSize(txtfield.getPrefWidth(), txtfield.getPrefHeight());
		btn.setLayoutX(txtfield.getLayoutX());
		btn.setLayoutY(txtfield.getLayoutY());
		
		return btn;
		
	}
}



