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

//EditReview.fxml�� ���� ��Ʈ�ѷ�
public class EditReviewController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "aa1@mail.com";
	private Parent root;
	Stage stage;
	CommonService comserv;
	WriterService writerserv;
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

	//�̹��� ���ε� ��ư
	public void ImgUploadBtnProc() {
		writerserv.ImgUploadBtnServ(stage, root);
	}
	//��� ��ư
	public void CancelBtnProc(ActionEvent e) {
		comserv.CloseWindow(e);
	}
	
	public void EditBtnProc(ActionEvent e) {
		writerserv.EditBtnServ(root);
		comserv.CloseWindow(e);
	}
}
