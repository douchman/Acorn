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

//ReviewList.fxml�� ���� ��Ʈ�ѷ�
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
	
	//�Ҵ��ߴ� ��Ʈ�ѷ� ��ü�� ����� ���� �Ѱܹް� �����ϴ� �޼���
	public void setReviewCtrler(ReviewPageController rvpageCtrl) {
		this.rvpageCtrl = rvpageCtrl;
	}
	
	public void setShopID(String shopId) {
		this.shopId = shopId;
	}
	public void setUserID(String userId) {
		this.userId = userId;
		
	}
	//���� ����Ʈ �� ���� ��ư
	public void UpdateReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("������ �����ϰڽ��ϱ�?") == false)
			return;
		String reviewId = rvlistserv.findReviewId(userId, rvlistserv.findWriteday(root));
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/EditReview.fxml", "Edit Review");	//OpenWindow�� ��� �ʿ�
		rvlistserv.UpdateReviewServ(comserv.getRoot(), userId, reviewId);
	}
	//���� ����Ʈ �� ���� ��ư
	public void DeleteReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("������ �����ϰڽ��ϱ�?") == false)
			return;
		comserv.Msgbox("�����Ǿ����ϴ�.");
		rvlistserv.DeleteReviewServ(root, userId);
		rvpageCtrl.refresh();
	}
}
