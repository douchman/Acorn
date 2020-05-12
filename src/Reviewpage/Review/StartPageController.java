package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.InformationService;
import Reviewpage.Service.InformationServiceImpl;
import Reviewpage.Service.TabService;
import Reviewpage.Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class StartPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	CommonService comserv;
	InformationService inforserv;
	TabService tabserv;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inforserv = new InformationServiceImpl();
		comserv = new CommonServiceImpl();
		tabserv = new TabServiceImpl();
	}
	public void GoMain(ActionEvent e) {
		Stage stage = new Stage();
		//Title(TopInformation)
		comserv.OpenWindow(stage, "../Review/ReviewPage.fxml", "ReviewPage");	//식당페이지 출력
		inforserv.TopInformation(comserv.getRoot(), SHOPID, USERID);	//식당페이지 정보출력("식당식별자")
		comserv.CloseWindow(e);
		
		//MENU
		tabserv.TabMenu(comserv.getRoot(), SHOPID);
		//REVIEW
		tabserv.TabReview(comserv.getRoot(), SHOPID, USERID);
	}
}
