package ReviewPackage;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonServiceImpl;
import Service.CommonService;
import Service.InformationService;
import Service.InformationServiceImpl;
import Service.TabService;
import Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	
	String shopID;
	//String SHOPID;
	//String USERID;
	private Parent root;
	CommonService comserv;
	InformationService inforserv;	//메인 음식점 타이틀 서비스
	TabService tabserv;				//탭 서비스
	Management manage;
	
	TextField tmpField;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		inforserv = new InformationServiceImpl();
		tabserv = new TabServiceImpl();
		manage = new Management();
		
		//SHOPID = manage.getShopId();
		//USERID = manage.getUserId();
	}
	//메인 메이지로 이동
	public void GoMain(ActionEvent e) {
		tmpField = (TextField)root.lookup("#InsertEmailTF");
		shopID = tmpField.getText();
		Stage stage = new Stage();
		//Title(TopInformation)
		comserv.OpenWindow(stage, "/ReviewPackage/ReviewPage.fxml", "ReviewPage");	//식당페이지 출력
		root = comserv.getRoot();	//OpenWindow의 루트를 가져옴
		
		//inforserv.TopInformation(root, SHOPID, USERID);	//식당페이지 정보출력("식당식별자")
		inforserv.TopInformation(root, shopID, USERID);	//식당페이지 정보출력("식당식별자")
		
		
		/*
		 * 
		//MENU
		tabserv.TabMenu(root, SHOPID);
		//REVIEW
		tabserv.TabReview(root, SHOPID, USERID);
		
		*/
		
		//MENU
		tabserv.TabMenu(root, shopID);
		//REVIEW
		tabserv.TabReview(root, shopID, USERID);
		
		comserv.CloseWindow(e);
	}
}
