package ReviewPackage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.MyDBService;
import Service.MyDBServiceImpl;
import Service.WriterService;
import Service.WriterServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class EditReviewController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	Stage stage;
	CommonService comserv;
	WriterService writerserv;
	StartPageController starctr;
	ToggleButton TBtn;
	List<ToggleButton> lstTBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		writerserv = new WriterServiceImpl();
		starctr = new StartPageController();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	//이미지 업로드 버튼
	public void ImgUploadBtnProc() {
		writerserv.ImgUploadBtnServ(stage, root);
	}
	//취소 버튼
	public void CancelBtnProc(ActionEvent e) {
		comserv.CloseWindow(e);
	}
	
	public void EditBtnProc(ActionEvent e) {
		writerserv.EditBtnServ(root);
		comserv.CloseWindow(e);
	}

	

}
