package MainMenu.MainPage.Service;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Container {
	
	public Circle getC(int radius, Color fill, Color stroke, int locax, int locay, String sid ) {
		Circle c = new Circle();
		c.setRadius(radius);
		c.setFill(fill);
		c.setStroke(stroke);
		c.setLayoutX(locax);
		c.setLayoutY(locay);
		c.setId(sid);
		
		
		return c;
	}
	
	public Rectangle getR(int width, int height, Color fill, Color stroke) {
		Rectangle r = new Rectangle();
		r.setWidth(width);
		r.setHeight(height);
		r.setFill(fill);
		r.setStroke(stroke);
		
		return r;
	}
	
	public StackPane getSP(String id, double d , double e) {
		StackPane sp = new StackPane();
		
		sp.setId(id);
		sp.setLayoutX(d);
		sp.setLayoutY(e);
		
		return sp;
	}
	
	public StackPane getSP(double d , double e) {
		StackPane sp = new StackPane();

		sp.setLayoutX(d);
		sp.setLayoutY(e);
		
		return sp;
	}
	
	public ImageView getImg(String iv, boolean ratio, int h, int w) {
		ImageView img = new ImageView(iv);
		
		img.setPreserveRatio(ratio);
		img.setFitHeight(h);
		img.setFitWidth(w);
		
		return img;
	}
	
	public HBox getHB(int p, String id) {
		HBox hb = new HBox();
		
		hb.setPadding(new Insets(p));
		hb.setId(id);
		
		return hb;
	}
	
	public VBox getVB(int p, int s, Pos pos) {
		VBox vb = new VBox();
		
		vb.setPadding(new Insets(p));
		vb.setSpacing(s);
		vb.setAlignment(pos);
		
		return vb;
	}
}
