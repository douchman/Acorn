package Minigame.Service;

import MainMenu.MainPage.MainPageController;
import Minigame.Roulette.CustomRulette;
import Minigame.Roulette.RankRoulette;
import Minigame.Roulette.RouletteMenuController;
import Minigame.ServiceImpl.RouletteService;
import javafx.scene.Parent;

public class RouletteServiceImpl implements RouletteService{
	private Parent root;

	@Override
	public void setRoot(Parent root) {
		this.root = root;	
	}

	@Override
	public void RunCustomRoulette(RouletteMenuController rouletteMenuCon,MainPageController mainCon) {
		CustomRulette customRulette = new CustomRulette(
				rouletteMenuCon, mainCon);	
		customRulette.show();
	}
	
	
	@Override
	public void RunRankRoulette(RouletteMenuController rouletteMenuCon,MainPageController mainCon) {
		RankRoulette rankRoulette = new RankRoulette(
				rouletteMenuCon, mainCon);
		rankRoulette.show();
		
		
	}
}
