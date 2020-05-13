package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.WriterService;
import Reviewpage.Service.WriterServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;


/*
 * 
 * 리뷰 작성을 위한 컨트롤러
 * WriteReview.fxml 에서 로드 된다.
 * 
 * 
 * */
public class WriteReviewController extends Controller implements Initializable{
	//final String SHOPID = "1";
	//final String USERID = "aa1@mail.com";
	String shopid;
	String userid;
	private Parent root;
	Stage stage;
	CommonService comserv;
	WriterService writerserv;	//리뷰작성 서비스
	
	ReviewPageController rvCon;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		writerserv = new WriterServiceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void setReviewCon(ReviewPageController rvCon) {
		this.rvCon = rvCon;
	}
	
	public void setShopID(String shopid) {
		this.shopid = shopid;
	}
	public void setUserID(String userid) {
		this.userid = userid;
	}
	//별 버튼
	public Integer star1() {
		return writerserv.ToggleStar(root, "1");
	}
	public Integer star2() {
		return writerserv.ToggleStar(root, "2");
	}
	public Integer star3() {
		return writerserv.ToggleStar(root, "3");
	}
	public Integer star4() {
		return writerserv.ToggleStar(root, "4");
	}
	public Integer star5() {
		return writerserv.ToggleStar(root, "5");
	}
	//이미지업로드 버튼
	public void ImgUploadBtnProc() {
		writerserv.ImgUploadBtnServ(stage, root);
	}
	//취소 버튼
	public void CancelBtnProc(ActionEvent e) {
		comserv.CloseWindow(e);
	}
	//작성 버튼
	public void submitBtnProc(ActionEvent e) {
		writerserv.submitBtnServ(root, shopid, userid);
		rvCon.refresh();
		comserv.CloseWindow(e);
	}
}
