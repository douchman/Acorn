package Minigame.Service;

import MainMenu.MainPage.MainPageController;
import Minigame.Roulette.RouletteMenuController;
import Minigame.ServiceImpl.RouletteServiceImpl;
import javafx.scene.Parent;

public class RouletteService implements RouletteServiceImpl{
	public void setRoot(Parent root);
	public void RunRankRoulette(RouletteMenuController rouletteMenuCon,MainPageController mainCon);
	public void RunCustomRoulette(RouletteMenuController rouletteMenuCon,MainPageController mainCon);
}
