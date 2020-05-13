package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.ReviewListService;
import Reviewpage.Service.ReviewListServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;


/*
 * 
 * 
 * ReviewList fxml 로드  작성된 리뷰 구성
 * 
 * 
 * 
*/

public class ReviewListController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "aa1@mail.com";
	private Parent root;
	Stage stage;
	ReviewListService rvlistserv;
	CommonService comserv;
	// 박상현 추가 
	ReviewPageController reviewCon;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rvlistserv = new ReviewListServiceImpl();
		comserv = new CommonServiceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	// 박상현 추가  할당했던 컨트롤러 객체로 만들기 위해 넘겨받고 설정하는 메서드
	public void setRevireCon(ReviewPageController reviewCon) {
		this.reviewCon = reviewCon;
	}
	//리뷰 리스트 중 수정 버튼
	public void UpdateReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("정말로 수정하겠습니까?") == false)
			return;
		String reviewId = rvlistserv.findReviewId(USERID, rvlistserv.findWriteday(root));
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/EditReview.fxml", "Edit Review");	//OpenWindow의 경로 필요
		rvlistserv.UpdateReviewServ(comserv.getRoot(), USERID, reviewId);
		
		// 박상현 수정
		//reviewCon.refresh();
	}
	//리뷰 리스트 중 삭제 버튼
	public void DeleteReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("정말로 삭제하겠습니까?") == false)
			return;
		comserv.Msgbox("삭제되었습니다.");
		rvlistserv.DeleteReviewServ(root, USERID);
		// 박상현 수정
		//reviewCon.refresh();
	}
}
