package MainMenu.MainPage.Service;

import javafx.scene.Parent;

public interface SideMenuService {
	
	public void RandomList(Parent root, String usrID);
	public void RandomList(Parent root, int m, String usrID);
	public void RandomList(Parent root, String txt, String usrID);
	public String RatetoStar(int rate);
	public String PricetoWon(int price);
	public void clear(Parent root);
}
