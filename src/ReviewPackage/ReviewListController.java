package ReviewPackage;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.ReviewListService;
import Service.ReviewListServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class ReviewListController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	Stage stage;
	ReviewListService rvlistserv;
	CommonService comserv;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rvlistserv = new ReviewListServiceImpl();
		comserv = new CommonServiceImpl();
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	//리뷰 리스트 중 수정 버튼
	public void UpdateReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("정말로 수정하겠습니까?") == false)
			return;
		String reviewId = rvlistserv.findReviewId(USERID, rvlistserv.findWriteday(root));
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/EditReview.fxml", "Edit Review");	//OpenWindow의 경로 필요
		rvlistserv.UpdateReviewServ(comserv.getRoot(), USERID, reviewId);
	}
	//리뷰 리스트 중 삭제 버튼
	public void DeleteReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("정말로 삭제하겠습니까?") == false)
			return;
		comserv.Msgbox("삭제되었습니다.");
		rvlistserv.DeleteReviewServ(root, USERID);
	}
}
