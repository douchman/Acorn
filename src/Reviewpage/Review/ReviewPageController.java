package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.InformationService;
import Reviewpage.Service.InformationServiceImpl;
import Reviewpage.Service.TabService;
import Reviewpage.Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

//���������� ��ü���� �����ϴ� ��û�� ó���ϴ� ��Ʈ�ѷ�
public class ReviewPageController extends Controller implements Initializable{
	private String shopID, userID;
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
	}
	public void setId(int shopid, String userid) {
	      this.shopID = Integer.toString(shopid);
	      this.userID = userid;
	   }
	public void Reviewstart() {
		tabserv = new TabServiceImpl();
	      inforserv.TopInformation(root, shopID, userID );
	      tabserv.TabMenu(root, shopID);
	      tabserv.TabReview(root, shopID, userID, this);
	      //comserv.CloseWindow(e);
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
		inforserv.BookmarkImg(shopID, userID);
		inforserv.BookmarkServ(shopID, userID);
	}
	//��ũ��ư
	public void LinkProc() {
		inforserv.LinkServ(stage, root, shopID);
	}
	//�� ����
	 //�� ������ - �޴�
	
	 //�� ������ - ����
	  //���� ����
	public void WriteReviewProc() {
		if(userID.contentEquals("guest")) {
			comserv.Msgbox("�α����ϰ� �̿��ϼ���.");
			return;
		}
		tabserv.WriteReviewServ(stage, root, userID, shopID, this);
	}
	 //�� ������ - ����
	 //�� ������ - ã�ư���
	
	// �ڻ��� ����
	public void refresh() {
		inforserv.TopInformation(root, shopID, userID );
		tabserv.TabMenu(root, shopID);
		tabserv.TabReview(root, shopID, userID, this);
	}
}