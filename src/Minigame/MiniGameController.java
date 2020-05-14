package Minigame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.MainPage.MainPageController;
import Minigame.Roulette.RouletteMenuController;
import Minigame.Service.BrkacornServiceImpl;
import Minigame.Service.LadderServiceImpl;
import Minigame.Service.MinigameCommonServiceImpl;
import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.BrkacornService;
import Minigame.ServiceImpl.LadderService;
import Minigame.ServiceImpl.MinigameCommonService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
// Minigame.MiniGameController
import javafx.stage.Stage;

public class MiniGameController implements Initializable{
	
	private Parent root;
	private MainPageController mainCon;
	private MinigameCommonService commonService;
	private LadderService ladderServ;
	private BrkacornService brkAcornServ;
	
	public void setRoot(Parent root){
		this.root = root;
	}
	
	public void setMainPageCon(MainPageController mainCon) {
		this.mainCon = mainCon;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		commonService = new MinigameCommonServiceImpl();	
		ladderServ = new LadderServiceImpl();
		brkAcornServ = new BrkacornServiceImpl();
	}
	
	public void RouletteBtnPressed(ActionEvent event) throws IOException {
		commonService.closeWindow(root);
		// �귿�� �����̴�.
		Parent menuRoot;
		Stage menuStage = new Stage();
		FXMLLoader rouletteMenuLoader = new FXMLLoader(getClass().getResource("FXML/rouletteMenu.fxml"));
		RouletteMenuController rouletteMenuCon;
		// �귿�� �귿 �ȿ����� ��尡 �����Ƿ� �귿 �޴��� ���� �ε��ϰ�
		// �귿 �޴����� ��� ���� ���������� �����ϰԵȴ�.
		menuRoot = rouletteMenuLoader.load();
		rouletteMenuCon= rouletteMenuLoader.getController();
		rouletteMenuCon.setRoot(menuRoot);
		
		rouletteMenuCon.setMainCon(mainCon);
		
		menuStage.setScene(new Scene(menuRoot));
		menuStage.show();
	}
	public void LadderBtnPressed() {
		commonService.closeWindow(root);
		ladderServ.runLadder();
	}
	
	public void BrkAcronBtnPressed() {
		commonService.closeWindow(root);
		brkAcornServ.runBrkacorn();
	}
}
