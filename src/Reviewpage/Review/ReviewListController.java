package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.ReviewListService;
import Reviewpage.Service.ReviewListServiceImpl;
import Reviewpage.Service.TabService;
import Reviewpage.Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

//ReviewList.fxml에 대한 컨트롤러
public class ReviewListController extends Controller implements Initializable{
	private String shopId;
	private String userId;
	private Parent root;
	Stage stage;
	ReviewListService rvlistserv;
	TabService tabserv;
	CommonService comserv;
	ReviewPageController rvpageCtrl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rvlistserv = new ReviewListServiceImpl();
		tabserv = new TabServiceImpl();
		comserv = new CommonServiceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	//할당했던 컨트롤러 객체로 만들기 위해 넘겨받고 설정하는 메서드
	public void setReviewCtrler(ReviewPageController rvpageCtrl) {
		this.rvpageCtrl = rvpageCtrl;
	}
	
	public void setShopID(String shopId) {
		this.shopId = shopId;
	}
	public void setUserID(String userId) {
		this.userId = userId;
		
	}
	//리뷰 리스트 중 수정 버튼
	public void UpdateReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("정말로 수정하겠습니까?") == false)
			return;
		String reviewId = rvlistserv.findReviewId(userId, rvlistserv.findWriteday(root));
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/EditReview.fxml", "Edit Review");	//OpenWindow의 경로 필요
		rvlistserv.UpdateReviewServ(comserv.getRoot(), userId, reviewId);
	}
	//리뷰 리스트 중 삭제 버튼
	public void DeleteReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("정말로 삭제하겠습니까?") == false)
			return;
		comserv.Msgbox("삭제되었습니다.");
		rvlistserv.DeleteReviewServ(root, userId);
		rvpageCtrl.refresh();
	}
}
