package Minigame.Roulette;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.MainPage.MainPageController;
import Minigame.Service.BrkacornServiceImpl;
import Minigame.Service.MinigameCommonServiceImpl;
import Minigame.Service.RouletteServiceImpl;
import Minigame.ServiceImpl.BrkacornService;
import Minigame.ServiceImpl.MinigameCommonService;
import Minigame.ServiceImpl.RouletteService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class RouletteMenuController implements Initializable{
	// 룰렛 게임을 담당하는 컨트롤러
	private Parent root;
	//  이 루트는 룰렛 메뉴 루트임
	
	
	private MainPageController mainCon;
	private RouletteService rouletteServ;
	private MinigameCommonService commonService;
	private BrkacornService brkAcornServ;
	public void setRoot(Parent root){
		this.root = root;
		
	}
	public void setMainCon(MainPageController mainCon) {
		this.mainCon = mainCon;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			rouletteServ = new RouletteServiceImpl();
			commonService = new MinigameCommonServiceImpl();
			brkAcornServ = new BrkacornServiceImpl();
	}
	
	public void RunRankRoulette() {
		commonService.closeWindow(root);	
		rouletteServ.RunRankRoulette(this,mainCon);	
	}
	
	public void RunCustomRoulette() { 
		commonService.closeWindow(root);	
		rouletteServ.RunCustomRoulette(this,mainCon);
	}
	
	public void openMenu(){
		commonService.openWindow(root);	
		brkAcornServ.runBrkacorn();
	}

}
