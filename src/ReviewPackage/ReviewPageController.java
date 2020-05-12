package ReviewPackage;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.InformationService;
import Service.InformationServiceImpl;
import Service.TabService;
import Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReviewPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	Stage stage;
	InformationService inforserv;	//메인 음식점 타이틀 서비스
	CommonService comserv;
	TabService tabserv;				//탭 서비스	
	boolean mark;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inforserv = new InformationServiceImpl();
		comserv = new CommonServiceImpl();
		tabserv = new TabServiceImpl();
		mark = false;
	}
	//탭 클릭시 배경색과 같음
	private void ColorTab(Event e) {
		Tab tab = (Tab)e.getTarget();
		if(tab.isSelected()) {
			tab.setStyle("-fx-background-color: #7FC9B7;");
		}else {
			tab.setStyle("-fx-background-color: #5FAE95;");
		}
	}
	//메뉴 탭 클릭
	public void TabMenu(Event e) {	
		ColorTab(e);
	}
	//리뷰 탭 클릭
	public void TabReview(Event e) {
		ColorTab(e);
	}
	//공지사항 탭 클릭
	public void TabNotice(Event e) {
		ColorTab(e);
		//Reflash();
	}
	//찾아가기 탭 클릭
	public void TabInfo(Event e) {
		ColorTab(e);
	}
	
	//타이틀 이미지 왼버튼
	public void ImgLeftpageProc() {
		System.out.println("왼쪽 이미지로 교체");
	}
	//타이틀 이미지 오른버튼
	public void ImgRightpageProc() {
		System.out.println("오른쪽 이미지로 교체");
	}
	//북마크버튼
	public void BookmarkProc(ActionEvent e) {
		inforserv.BookmarkServ(root, SHOPID, USERID, false);
	}
	//링크버튼
	public void LinkProc() {
		inforserv.LinkServ(stage, root, SHOPID);
	}
	//탭 서비스
	 //탭 페이지 - 메뉴
	
	 //탭 페이지 - 리뷰
	  //리뷰 쓰기
	public void WriteReviewProc() {
		tabserv.WriteReviewServ(stage, root, USERID);
	}
	 //탭 페이지 - 공지
	 //탭 페이지 - 찾아가기
	
	public void Reflash() {
		System.out.println("reviewpagecontroller =" + root);
		VBox mainvbox = (VBox)root.lookup("#MainVBox");
		System.out.println(mainvbox);
		mainvbox.getChildren().clear();
	}
}
