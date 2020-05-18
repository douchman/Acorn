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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainPageController extends Controller implements Initializable{
	private Parent root;
	private FirstPageService service;
	private TopFieldService topservice;
	private SideMenuService sideservice;
	private MapService mapservice;
	private MinigameServiceImpl gameservice;

	
	// 박상현 추가
		private Stage firstPage;
		private Stage loginPage;
		
		
	//박상현 수정 
	private String usrID; 
	// 로그인 서비스에서 로그인 성공시 저장될 유저 아이디
	
	public void setUsrID(String usrID) {
		if(usrID==null) usrID="guest";
		this.usrID = usrID;
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		topservice.setLoginBtn(usrID, root);
	}
	
	// 메인페이지에서 뒤로가기할때 새로운 페이지를 만드는게아니라 최초생성된
		// 초기 페이지값을 얻어와 설정하는것
		public void getFirstPage(Stage firstPage) {
			// 박상현 추가
			this.firstPage = firstPage;
		}
		
		//
		public void setLoginPage(Stage loginPage) {
			this.loginPage = loginPage;
		}
		public void setLogoutBtn() {
			// 박상현 추가
			Button btnLogout  =(Button)root.lookup("#btnLogout");
			btnLogout.setVisible(true);
			
		}
		
		public void Logout(ActionEvent event) {
			// 박상현 추가
			Button btnLogout  =(Button)root.lookup("#btnLogout");
			btnLogout.setVisible(false);
			setUsrID(null);
			topservice.LogoutProc("로그인", root);
		}
		
		
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
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
		// 이 메서드가 실행되면
				// 현재 열려있는 메인창이 닫히고 
				// 초기 화면이 나타남
				
				//Stage stage = new Stage();
				//service.showWindow(stage, "../FirstPage.fxml", "../FirstPage.css");
				topservice.WindowClose(e);
				firstPage.show();
	}
	
	
	
	public void Tsearch(ActionEvent ae){
		sideservice.clear(root);
		mapservice.clear(root);
		String txt = topservice.Tsearch(ae);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,txt,usrID);
		mapservice.creatpin(root,txt,usrID);
	}
	
	public void Tsearch(String str){
		// 돌림판에서 결과처리를 위해 추가한 검색 메서드
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
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
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void topRank(MouseEvent e) {
		int m = 0;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void allm(MouseEvent e) {
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,usrID);
		mapservice.creatpin(root,usrID);
	}
	
	public void menuK(MouseEvent e) {
		int m = 1;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuC(MouseEvent e) {
		int m = 2;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuJ(MouseEvent e) {
		int m = 3;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}

	public void menuB(MouseEvent e) {
		int m = 4;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuF(MouseEvent e) {
		int m = 5;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void cafe(MouseEvent e) {
		int m = 7;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void menuE(MouseEvent e) {
		int m = 6;
		sideservice.clear(root);
		mapservice.clear(root);
		//세근 수정
	    sideservice.setUsrID(usrID);
	    
		sideservice.RandomList(root,m,usrID);
		mapservice.creatpin(root,m,usrID);
	}
	
	public void startMinigame(MouseEvent e) {
		gameservice.showMenu(this);
		
	}
	
	//Map
	public void resetpin(MouseEvent e) {
		mapservice.resetpin(root);
	}
	
}
