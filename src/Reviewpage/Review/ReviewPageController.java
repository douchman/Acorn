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
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

//리뷰페이지 전체에서 동작하는 요청을 처리하는 컨트롤러
public class ReviewPageController extends Controller implements Initializable{
	private String shopID, userID;
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
	}
	public void setId(int shopid, String userid) {
	      this.shopID = Integer.toString(shopid);
	      this.userID = userid;
	   }
	public void Reviewstart() {
		tabserv = new TabServiceImpl();
	      inforserv.TopInformation(root, shopID, userID );
	      tabserv.TabMenu(root, shopID);
	      tabserv.TabReview(root, shopID, userID, this);
	      //comserv.CloseWindow(e);
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
		inforserv.BookmarkImg(shopID, userID);
		inforserv.BookmarkServ(shopID, userID);
	}
	//링크버튼
	public void LinkProc() {
		inforserv.LinkServ(stage, root, shopID);
	}
	//탭 서비스
	 //탭 페이지 - 메뉴
	
	 //탭 페이지 - 리뷰
	  //리뷰 쓰기
	public void WriteReviewProc() {
		if(userID.contentEquals("guest")) {
			comserv.Msgbox("로그인하고 이용하세요.");
			return;
		}
		tabserv.WriteReviewServ(stage, root, userID, shopID, this);
	}
	 //탭 페이지 - 공지
	 //탭 페이지 - 찾아가기
	
	// 박상현 수정
	public void refresh() {
		inforserv.TopInformation(root, shopID, userID );
		tabserv.TabMenu(root, shopID);
		tabserv.TabReview(root, shopID, userID, this);
	}
}