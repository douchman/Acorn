package Minigame;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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
		
	public TextField getTxtField() {	
		TextField txtField = new TextField();
		txtField.setPromptText("ÄÁÅÙÃ÷ ÀÔ·Â");
		return txtField;
	}
}



