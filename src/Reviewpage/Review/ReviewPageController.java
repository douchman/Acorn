package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.InformationService;
import Reviewpage.Service.InformationServiceImpl;
import Reviewpage.Service.MyDBService;
import Reviewpage.Service.MyDBServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReviewPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	Button leftBtn;
	Button rightBtn;
	MyDBService dbserv;
	InformationService inforserv;
	CommonService comserv;
	Stage stage;
	Parent form;
	boolean Bookmark;
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dbserv = new MyDBServiceImpl();
		inforserv = new InformationServiceImpl();
		comserv = new CommonServiceImpl();
		Bookmark = true;
	}
	private void ColorTab(Event e) {
		Tab tab = (Tab)e.getTarget();
		if(tab.isSelected()) {
			tab.setStyle("-fx-background-color: #7FC9B7;");
		}else {
			tab.setStyle("-fx-background-color: #5FAE95;");
		}
	}
	public void TabMenu(Event e) {	
		ColorTab(e);
	}
	public void TabReview(Event e) {
		ColorTab(e);
	}
	public void TabNotice(Event e) {
		ColorTab(e);
	}
	public void TabInfo(Event e) {
		ColorTab(e);
	}
	
	//타이틀 이미지 왼버튼
	public void ImgLeftpage() {
		leftBtn = (Button)root.lookup("#LeftBtn");
		leftBtn.setOnAction(e->{
			System.out.println("왼쪽 이미지로 교체");
		});
	}
	//타이틀 이미지 오른버튼
	public void ImgRightpage() {
		rightBtn = (Button)root.lookup("#RightBtn");
		rightBtn.setOnAction(e->{
			System.out.println("오른쪽 이미지로 교체");
		});
	}
	//타이틀 찜
	public void BookmarkProc(ActionEvent e) {
		root = (Parent)e.getSource();
		inforserv.BookmarkSys(root, SHOPID, USERID, false);
		//Map<Integer, String> Mapbookmark = dbserv.selectDB(dbserv.InformationSQL(SHOPID), "count(book.user_id)");
		//System.out.println(Mapbookmark.get(0));
	}
	public void LinkProc() {
		stage = new Stage();
		comserv.OpenWindow(stage, "../Review/LinkPage.fxml", "Link");
		form = comserv.getRoot();
		Label lbl = (Label)form.lookup("#LinkShopNameLbl");
		TextField tf = (TextField)form.lookup("#LinkInformationTF");
		lbl.setText(dbserv.selectDB(dbserv.InformationSQL(SHOPID), "shop_name").get(0));
		tf.setText(dbserv.selectDB(dbserv.InformationSQL(SHOPID), "shop_address1").get(0)+ " " +
				dbserv.selectDB(dbserv.InformationSQL(SHOPID), "shop_address2").get(0));
	}
	//탭 페이지 - 메뉴
	//탭 페이지 - 리뷰
	public void WriteReviewProc() {
		stage = new Stage();
		comserv.OpenWindow(stage, "../Review/WriteReview.fxml", "Write Review");
		form = comserv.getRoot();
		Label lbl = (Label)form.lookup("#WriterNameLbl");
		String email = dbserv.selectDB(dbserv.EmailSQL(USERID), "substr(user_email, 1, instr(user_email,'@')-1)").get(0);
		lbl.setText(dbserv.selectDB(dbserv.WriteSQL(USERID), "user_name").get(0)+"("+ email +")");
	}
	//탭 페이지 - 공지
	//탭 페이지 - 찾아가기
}
