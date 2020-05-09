package MainPage;

import java.net.URL;
import java.util.ResourceBundle;

import FirstPage.Controller;
import FirstPage.Service.FirstPageService;
import FirstPage.Service.FirstPageServiceImpl;
import MainPage.Service.MapService;
import MainPage.Service.MapServiceImpl;
import MainPage.Service.SideMenuService;
import MainPage.Service.SideMenuServiceImpl;
import MainPage.Service.TopFieldService;
import MainPage.Service.TopFieldServiceImpl;
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
	
	
	//Map
	public void resetpin(MouseEvent e) {
		mapservice.resetpin(root);
	}
	
}
