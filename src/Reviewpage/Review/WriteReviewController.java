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

//WriteReview.fxml의 컨트롤러
public class WriteReviewController extends Controller implements Initializable{
	private String shopId, userId;
	private Parent root;
	Stage stage;
	CommonService comserv;
	WriterService writerserv;	//리뷰작성 서비스
	ReviewPageController rvpageCtrl;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		writerserv = new WriterServiceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void setReviewCtrler(ReviewPageController rvpageCtrl) {
		this.rvpageCtrl = rvpageCtrl;
	}
	
	public void setShopID(String shopId) {
		this.shopId = shopId;
	}
	public void setUserID(String userId) {
		this.userId = userId;
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
		writerserv.submitBtnServ(root, shopId, userId);
		rvpageCtrl.refresh();
		comserv.CloseWindow(e);
	}
}
