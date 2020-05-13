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
 * ReviewList fxml �ε�  �ۼ��� ���� ����
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
	// �ڻ��� �߰� 
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
	
	// �ڻ��� �߰�  �Ҵ��ߴ� ��Ʈ�ѷ� ��ü�� ����� ���� �Ѱܹް� �����ϴ� �޼���
	public void setRevireCon(ReviewPageController reviewCon) {
		this.reviewCon = reviewCon;
	}
	//���� ����Ʈ �� ���� ��ư
	public void UpdateReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("������ �����ϰڽ��ϱ�?") == false)
			return;
		String reviewId = rvlistserv.findReviewId(USERID, rvlistserv.findWriteday(root));
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/EditReview.fxml", "Edit Review");	//OpenWindow�� ��� �ʿ�
		rvlistserv.UpdateReviewServ(comserv.getRoot(), USERID, reviewId);
		
		// �ڻ��� ����
		//reviewCon.refresh();
	}
	//���� ����Ʈ �� ���� ��ư
	public void DeleteReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("������ �����ϰڽ��ϱ�?") == false)
			return;
		comserv.Msgbox("�����Ǿ����ϴ�.");
		rvlistserv.DeleteReviewServ(root, USERID);
		// �ڻ��� ����
		//reviewCon.refresh();
	}
}
