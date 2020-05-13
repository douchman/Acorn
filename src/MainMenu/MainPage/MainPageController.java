package MainMenu.MainPage;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.FirstPage.Controller;
import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import MainMenu.MainPage.Service.MapService;
import MainMenu.MainPage.Service.MapServiceImpl;
import MainMenu.MainPage.Service.SideMenuService;
import MainMenu.MainPage.Service.SideMenuServiceImpl;
import MainMenu.MainPage.Service.TopFieldService;
import MainMenu.MainPage.Service.TopFieldServiceImpl;
import Minigame.Service.MinigameService;
import Minigame.ServiceImpl.MinigameServiceImpl;
import javafx.event.ActionEvent;
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
	private MinigameServiceImpl gameservice;

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
		gameservice = new MinigameService();
	}

	//TopPane
	public void gotoFirstPage(MouseEvent e) {
		Stage stage = new Stage();
		service.showWindow(stage, "../FirstPage.fxml", "../FirstPage.css");
		topservice.WindowClose(e);
	}
	
	public void Tsearch(ActionEvent ae){
		sideservice.clear(root);
		mapservice.clear(root);
		String txt = topservice.Tsearch(ae);
		sideservice.RandomList(root,txt);
		mapservice.creatpin(root,txt);
	}
	
	public void Tsearch(String str){
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,str);
		mapservice.creatpin(root,str);
	}
	
	
	public void review() {
		
	}
	
	public void login(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Login/login.fxml", "../../Login/login.css");
	}
	
	public void memInfo() {
		
	}
	

	//SideMenu
	
	public void topVisit() {
		
	}
	
	public void topReview(MouseEvent e) {
		int m = 8;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
	}
	
	public void topRank(MouseEvent e) {
		int m = 0;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m);
		mapservice.creatpin(root,m);
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
	
	public void startMinigame(MouseEvent e) {
		gameservice.showMenu(this);
		
	}
	
	//Map
	public void resetpin(MouseEvent e) {
		mapservice.resetpin(root);
	}
	
}
