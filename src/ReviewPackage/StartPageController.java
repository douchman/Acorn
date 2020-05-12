package ReviewPackage;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonServiceImpl;
import Service.CommonService;
import Service.InformationService;
import Service.InformationServiceImpl;
import Service.TabService;
import Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	
	String shopID;
	//String SHOPID;
	//String USERID;
	private Parent root;
	CommonService comserv;
	InformationService inforserv;	//���� ������ Ÿ��Ʋ ����
	TabService tabserv;				//�� ����
	Management manage;
	
	TextField tmpField;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		inforserv = new InformationServiceImpl();
		tabserv = new TabServiceImpl();
		manage = new Management();
		
		//SHOPID = manage.getShopId();
		//USERID = manage.getUserId();
	}
	//���� �������� �̵�
	public void GoMain(ActionEvent e) {
		tmpField = (TextField)root.lookup("#InsertEmailTF");
		shopID = tmpField.getText();
		Stage stage = new Stage();
		//Title(TopInformation)
		comserv.OpenWindow(stage, "/ReviewPackage/ReviewPage.fxml", "ReviewPage");	//�Ĵ������� ���
		root = comserv.getRoot();	//OpenWindow�� ��Ʈ�� ������
		
		//inforserv.TopInformation(root, SHOPID, USERID);	//�Ĵ������� �������("�Ĵ�ĺ���")
		inforserv.TopInformation(root, shopID, USERID);	//�Ĵ������� �������("�Ĵ�ĺ���")
		
		
		/*
		 * 
		//MENU
		tabserv.TabMenu(root, SHOPID);
		//REVIEW
		tabserv.TabReview(root, SHOPID, USERID);
		
		*/
		
		//MENU
		tabserv.TabMenu(root, shopID);
		//REVIEW
		tabserv.TabReview(root, shopID, USERID);
		
		comserv.CloseWindow(e);
	}
}
