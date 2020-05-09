package MainPage.Service;

import javafx.scene.Parent;

public interface SideMenuService {
	
	public void RandomList(Parent root);
	public void RandomList(Parent root, int m);
	public String RatetoStar(int rate);
	public String PricetoWon(int price);
	public void clear(Parent root);
}
