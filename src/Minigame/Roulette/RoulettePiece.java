package Minigame.Roulette;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class RoulettePiece extends Arc{

	public double areaValueX;
	public double areaValueY;
	public double angle;
	public String name;
	public double distance;
	public RoulettePiece(double x, double y, 
			double rx, double ry,
			double startAngle, double length,
			Color color,String name) {
		this.angle = (((length/2)+startAngle) * ((Math.PI)/180));
		this.name = name;
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadiusX(rx);
		this.setRadiusY(ry);
		this.setStartAngle(startAngle);
		this.setLength(length);// angleSize
		this.setType(ArcType.ROUND);
		this.setStroke(Color.BLACK);
		this.setFill(color);
		//this.areaValueX
		this.toBack();
	
		
	}
	
	public void getLayout() {
		//System.out.println(this.getRotate());
		System.out.println(this.name+ "" + this.areaValueX +"\t"+ this.areaValueY);
	}
	public String getName(){
		return this.name;
	}
	public void setArea(double X, double Y) {
		this.areaValueX = X;
		this.areaValueY = Y;
		//System.out.println(this.name+ "" + this.areaValueX +"\t"+ this.areaValueY );
	}
	
	public double getDistance() {
		return this.distance;
	}
	public double getDistance(double pinX, double pinY) {
		this.distance = Math.sqrt(Math.pow(Math.abs(pinX-this.areaValueX), 2) +
				Math.pow(Math.abs(pinY-this.areaValueY),2));
		return this.distance;
	}
}
