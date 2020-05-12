package Minigame.BrkAcorn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class BrkAcornImg {
	List<Image> listImage;
	
	public BrkAcornImg() {
		listImage = new ArrayList<Image>();
		for(int i=1; i<=9; i+=2) {
			listImage.add(
					new Image(getClass().getResourceAsStream("../image/brk"+i+"0"+".png")));
		}
		listImage.add(
				new Image
				(getClass().getResourceAsStream("../image/brk100.png")));
	}	
}
