package ReviewPackage;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.InformationService;
import Service.InformationServiceImpl;
import Service.TabService;
import Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReviewPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	Stage stage;
	InformationService inforserv;	//���� ������ Ÿ��Ʋ ����
	CommonService comserv;
	TabService tabserv;				//�� ����	
	boolean mark;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inforserv = new InformationServiceImpl();
		comserv = new CommonServiceImpl();
		tabserv = new TabServiceImpl();
		mark = false;
	}
	//�� Ŭ���� ������ ����
	private void ColorTab(Event e) {
		Tab tab = (Tab)e.getTarget();
		if(tab.isSelected()) {
			tab.setStyle("-fx-background-color: #7FC9B7;");
		}else {
			tab.setStyle("-fx-background-color: #5FAE95;");
		}
	}
	//�޴� �� Ŭ��
	public void TabMenu(Event e) {	
		ColorTab(e);
	}
	//���� �� Ŭ��
	public void TabReview(Event e) {
		ColorTab(e);
	}
	//�������� �� Ŭ��
	public void TabNotice(Event e) {
		ColorTab(e);
		//Reflash();
	}
	//ã�ư��� �� Ŭ��
	public void TabInfo(Event e) {
		ColorTab(e);
	}
	
	//Ÿ��Ʋ �̹��� �޹�ư
	public void ImgLeftpageProc() {
		System.out.println("���� �̹����� ��ü");
	}
	//Ÿ��Ʋ �̹��� ������ư
	public void ImgRightpageProc() {
		System.out.println("������ �̹����� ��ü");
	}
	//�ϸ�ũ��ư
	public void BookmarkProc(ActionEvent e) {
		inforserv.BookmarkServ(root, SHOPID, USERID, false);
	}
	//��ũ��ư
	public void LinkProc() {
		inforserv.LinkServ(stage, root, SHOPID);
	}
	//�� ����
	 //�� ������ - �޴�
	
	 //�� ������ - ����
	  //���� ����
	public void WriteReviewProc() {
		tabserv.WriteReviewServ(stage, root, USERID);
	}
	 //�� ������ - ����
	 //�� ������ - ã�ư���
	
	public void Reflash() {
		System.out.println("reviewpagecontroller =" + root);
		VBox mainvbox = (VBox)root.lookup("#MainVBox");
		System.out.println(mainvbox);
		mainvbox.getChildren().clear();
	}
}
