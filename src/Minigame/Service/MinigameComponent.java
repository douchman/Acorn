package Minigame.ServiceImpl;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;

public interface MinigameComponent {
	public Button getLadderButton(TextField txtfield);
	public Label getLabel(String txt, String color);
	public Arc getArc(double x, double y, 
			double rx, double ry,
			double startAngle, double length,
			Color color);
	public Label getArcLabel(String str, double x, double y, double startAngle);
	public Label getLadderLabel(TextField txtfield);
	public Line getLine(double startX, double startY,
			double endX, double endY);
	public Line getThreadLine(double startX, double startY,
			double endX, double endY);
	public TextField getRouletteTxtField();
	public TextField getLadderTxtField(double x, double y);
	
}
