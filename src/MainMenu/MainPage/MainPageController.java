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

	
	//박상현 수정 
	private String usrID; 
	// 로그인 서비스에서 로그인 성공시 저장될 유저 아이디
	
	public void setUsrID(String usrID) {
		if(usrID==null) usrID="guest";
		this.usrID = usrID;
		topservice.setLoginBtn(usrID, root);
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		sideservice.RandomList(root, usrID);
		mapservice.creatpin(root, usrID);
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
		sideservice.RandomList(root,txt,usrID);
		mapservice.creatpin(root,txt,usrID);
	}
	
	public void Tsearch(String str){
		// 돌림판에서 결과처리를 위해 추가한 검색 메서드
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,str,usrID);
		mapservice.creatpin(root,str,usrID);
	}
	
	public void review(MouseEvent e) {
		topservice.wReview(root);
	}
	
	public void login(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Login/login.fxml", "../../Login/login.css");
		Stage currentStage = (Stage)root.getScene().getWindow();
		currentStage.close();
		// 현재 열린 창 닫기 *임시
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
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void topRank(MouseEvent e) {
		int m = 0;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void allm(MouseEvent e) {
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,usrID);
		mapservice.creatpin(root,usrID);
	}
	
	public void menuK(MouseEvent e) {
		int m = 1;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuC(MouseEvent e) {
		int m = 2;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuJ(MouseEvent e) {
		int m = 3;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}

	public void menuB(MouseEvent e) {
		int m = 4;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuF(MouseEvent e) {
		int m = 5;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void cafe(MouseEvent e) {
		int m = 7;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuE(MouseEvent e) {
		int m = 6;
		sideservice.clear(root);
		mapservice.clear(root);
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void startMinigame(MouseEvent e) {
		gameservice.showMenu();
		
	}
	
	//Map
	public void resetpin(MouseEvent e) {
		mapservice.resetpin(root);
	}
	
}
