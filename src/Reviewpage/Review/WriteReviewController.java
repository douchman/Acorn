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
 * ���� �ۼ��� ���� ��Ʈ�ѷ�
 * WriteReview.fxml ���� �ε� �ȴ�.
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
	WriterService writerserv;	//�����ۼ� ����
	
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
	//�� ��ư
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
	//�̹������ε� ��ư
	public void ImgUploadBtnProc() {
		writerserv.ImgUploadBtnServ(stage, root);
	}
	//��� ��ư
	public void CancelBtnProc(ActionEvent e) {
		comserv.CloseWindow(e);
	}
	//�ۼ� ��ư
	public void submitBtnProc(ActionEvent e) {
		writerserv.submitBtnServ(root, shopid, userid);
		rvCon.refresh();
		comserv.CloseWindow(e);
	}
}
