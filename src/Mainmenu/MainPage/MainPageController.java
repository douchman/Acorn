package Mainmenu.MainPage;

import java.net.URL;
import java.util.ResourceBundle;

import Mainmenu.FirstPage.Controller;
import Mainmenu.FirstPage.Service.FirstPageService;
import Mainmenu.FirstPage.Service.FirstPageServiceImpl;
import Mainmenu.MainPage.Service.MapService;
import Mainmenu.MainPage.Service.MapServiceImpl;
import Mainmenu.MainPage.Service.SideMenuService;
import Mainmenu.MainPage.Service.SideMenuServiceImpl;
import Mainmenu.MainPage.Service.TopFieldService;
import Mainmenu.MainPage.Service.TopFieldServiceImpl;
import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainPageController extends Controller implements Initializable{
	private Parent root;
	private FirstPageService service;
	private TopFieldService topservice;
	private SideMenuService sideservice;
	private MapService mapservice;
	
	
	private MinigameServiceImpl gameServcie;
	// 미니게임 시작을 위한 서비스 객체
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		sideservice.RandomList(root);
		mapservice.creatpin(root);
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new FirstPageServiceImpl();
		topservice = new TopFieldServiceImpl();
		sideservice = new SideMenuServiceImpl();
		mapservice = new MapServiceImpl();
		gameServcie = new MinigameService();	
	}

	//TopPane
	public void gotoFirstPage(MouseEvent e) {
		Stage stage = new Stage();
		service.showWindow(stage, "../FirstPage.fxml", "../FirstPage.css");
		topservice.WindowClose(e);
	}
	
	public void search() {
		
	}
	
	public void review() {
		
	}
	
	public void login() {
		
	}
	
	public void memInfo() {
		
	}
	

	//SideMenu
	public void randomGame() {
		
	}
	
	public void topVisit() {
		
	}
	
	public void topReview() {
		
	}
	
	public void topRank() {
		
	}
	
	public void allm(MouseEvent e) {
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root);
		mapservice.creatpin(root);
	}
	
	public void menuK(MouseEvent e) {
		int m = 1;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	public void menuC(MouseEvent e) {
		int m = 2;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	public void menuJ(MouseEvent e) {
		int m = 3;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}

	public void menuB(MouseEvent e) {
		int m = 4;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	public void menuF(MouseEvent e) {
		int m = 5;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	public void cafe(MouseEvent e) {
		int m = 7;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	public void menuE(MouseEvent e) {
		int m = 6;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	// 미니게임 호출 부분
	
	public void startMinigame(MouseEvent e) {
		gameServcie.showMenu();
		
	}
	
	//Map
	public void resetpin(MouseEvent e) {
		mapservice.resetpin(root);
	}
	
}
