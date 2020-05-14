package Minigame.Service;

import MainMenu.MainPage.MainPageController;
import Minigame.ServiceImpl.RouletteMenuService;
import javafx.scene.Parent;

public class RouletteMenuServiceImpl implements RouletteMenuService{
	private Parent root;
	private MainPageController mainCon;
	
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;	
	}
	
	@Override
	public void setMainCon(MainPageController mainCon) {
		this.mainCon = mainCon;			
	}
}
