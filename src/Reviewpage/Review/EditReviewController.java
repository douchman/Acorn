package Reviewpage.Review;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.WriterService;
import Reviewpage.Service.WriterServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

//EditReview.fxml에 대한 컨트롤러
public class EditReviewController extends Controller implements Initializable{
	private String reviewId;
	private Parent root;
	Stage stage;
	CommonService comserv;
	WriterService writerserv;
	ReviewListController rvlistCtrl;
	ReviewPageController rvpageCtrl;	//reflesh를 위한 소환
	ToggleButton TBtn;
	List<ToggleButton> lstTBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		writerserv = new WriterServiceImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void setReviewCtrler(ReviewListController rvlistCtrl) {
		this.rvlistCtrl = rvlistCtrl;
	}
	public void setReviewpageCtrl(ReviewPageController rvpageCtrl) {
		this.rvpageCtrl = rvpageCtrl;
	}
	public void setReviewID(String reviewId) {
		this.reviewId = reviewId;
	}

	//이미지 업로드 버튼
	public void ImgUploadBtnProc() {
		writerserv.ImgUploadBtnServ(stage, root);
	}
	//취소 버튼
	public void CancelBtnProc(ActionEvent e) {
		comserv.CloseWindow(e);
	}
	//수정 버튼
	public void EditBtnProc(ActionEvent e) {
		writerserv.EditBtnServ(root, reviewId);
		rvpageCtrl.refresh();
		comserv.CloseWindow(e);
	}
}
