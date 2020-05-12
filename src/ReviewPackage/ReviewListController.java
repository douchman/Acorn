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
	//���� ����Ʈ �� ���� ��ư
	public void UpdateReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("������ �����ϰڽ��ϱ�?") == false)
			return;
		String reviewId = rvlistserv.findReviewId(USERID, rvlistserv.findWriteday(root));
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/EditReview.fxml", "Edit Review");	//OpenWindow�� ��� �ʿ�
		rvlistserv.UpdateReviewServ(comserv.getRoot(), USERID, reviewId);
	}
	//���� ����Ʈ �� ���� ��ư
	public void DeleteReviewProc(ActionEvent e) {
		if(comserv.CheckMsgbox("������ �����ϰڽ��ϱ�?") == false)
			return;
		comserv.Msgbox("�����Ǿ����ϴ�.");
		rvlistserv.DeleteReviewServ(root, USERID);
	}
}
