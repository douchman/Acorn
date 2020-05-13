package Minigame.ServiceImpl;

import MainMenu.MainPage.MainPageController;
import javafx.scene.Parent;

public interface MinigameServiceImpl {

	public void showMenu(MainPageController mainCon);
	public void runRoulette(Parent root, MainPageController miniCon);
	public void runLadder(Parent root);
	public void runBrkAcorn(Parent root);
}
