package Minigame.ServiceImpl;

import MainMenu.MainPage.MainPageController;
import Minigame.Roulette.RouletteMenuController;
import javafx.scene.Parent;

public interface RouletteService {
	public void setRoot(Parent root);
	public void RunRankRoulette(RouletteMenuController rouletteMenuCon,MainPageController mainCon);
	public void RunCustomRoulette(RouletteMenuController rouletteMenuCon,MainPageController mainCon);
}
