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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
 * 
 * ���������� ��ü���� �����ϴ� ��û�� ó���ϴ� ��Ʈ�ѷ�
 * 
 * 
 * 
 * */
public class ReviewPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "aa1@mail.com";
	private Parent root, testRoot;
	Stage stage;
	InformationService inforserv;	//���� ������ Ÿ��Ʋ ����
	CommonService comserv;
	TabService tabserv;				//�� ����	
	boolean mark;
	
	// �ڻ��� �߰� �� ����
	private String shopID, userID;
	
	@Override
	public void setRoot(Parent root) {
		// ���� �̺κе� ����� �޾Ƴ��� �����ʴ°� ���ƿ�?
		// ���� ���񽺿����� ���� root�� �������ִ°� �ִ���
		// ���� Ȯ���� �� �Ǿ Ȯ�������ʽ��ϴ�.
		this.root = root;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inforserv = new InformationServiceImpl();
		comserv = new CommonServiceImpl();
		tabserv = new TabServiceImpl();
		// �ϴ� �ʱ�ȭ �޼��忡�� �Ǽ���  ���
		// ���� ������ �����ϴµ� �־ �ʿ��� ���񽺸� ���� �Ҵ���
		mark = false;
	}
	
	
	// �ڻ��� �߰�
	public void TmporaryTestPageSetRoot(Parent testRoot) {
		// ��ŸƮ�������� ���ֹ����� �������� �����Ų �� �̱⶧���� �ӽ������� 
		// �׽�Ʈ��  �ӽ÷�Ʈ ������ ���� �޼��� �Դϴ�.
		// �׽�Ʈ ��Ʈ�� Ŭ���� �ֻ�� �ν��Ͻ��� �����߽��ϴ�.
		this.testRoot = testRoot;
		
		// ��Ʈ�ѷ��� ���� ���� �ʾұ⶧���� ��ư ���� ã�Ƽ� �̺�Ʈ �����߽��ϴ�.
		Button goBtn = (Button)testRoot.lookup("#GoPage");
		
		
		goBtn.setOnAction(e->{
			TemporaryRunMethod(e);
		});
	}
	
	public void TemporaryRunMethod(ActionEvent e) {
		/*
		 ���ʿ��� ��ŸƮ������ ��Ʈ�ѷ��� ���ֹ�����
		 ��ŸƮ������������ ���� startpage.fxml���� �����ϴ� ��Ʈ�ѷ��� �������ϴ�.
		 ��Ʈ�ѷ� ���������� fxml�� ������ؾ��ϴµ� ���� ��Ʈ�ѷ��� �����ʰ� ���� ��Ʈ�ѷ��� 
		 �׽�Ʈ �޼��带 ���� ������ Ȯ�� �� �� �̶� �������� �����ϵ��� �����߽��ϴ�.
		 
		 ��ǻ� ��ŸƮ������ ��Ʈ�ѷ� ������ ��ü�� �ʿ䰡�����Ӵ��� 
		 ��ŸƮ������ ��Ʈ�ѷ��� ���� ���Ŀ��ԵǸ鼭 �ش� ��Ʈ�ѷ����� ������ �� ����������
		 �ʼ������͸� �޾ƹ����ϱ� ������ �� �������ϴ�. ������ �ܼ��� �������� ���������ε� ū ������ �����µ�
		 �׽�Ʈ������ ������ Ȯ���Ϸ��ٺ��ϱ� ������ ���� ���Ű� ���׿�.
		 
		 ������ ���ڸ�.. 
		 - ���� �ӽ����� �κ�(����������) �� �ʹ� ũ�� �Ű澲�� �� �����ϴ�.
		 - �������� ���������� ��ü�� �����ϴ� ���� ������ ��Ʈ�ѷ��� ���� �� ReviewPageController ����?
		 - �ᱹ ���������� ������ ���� �����ʹ� �� �ΰ� 1. ��������ID_num 2. �Ĵ� ����ID_num �Դϴ�.
		 - �� �ΰ��� �����͸� �̿��Ͽ� ��ǻ� ��ü�� �� ��Ʈ�ѷ����� ó���� ���ִ°� �´� �� ���ƿ�.
		 - ���� ��ó�� �ӽ� ���� �޼��带 ���� �� �޼��忡��, 
		 - �ʱ�ȭ���� Textfield ���� ���� ���� GoMain ��ư�� �������� �̺�Ʈ�� ó���ϰڽ��ϴ�.
		 - �ؽ�Ʈ �ʵ忡���� ���� ������ ��Ȳ�� �����Ͽ� ������ ������ �ʿ��� ������ userID �� resID �� �ް� ���۽�ŵ�ϴ�.
		 - �߰� �ڸ�Ʈ��, �ϴ��� ��� ������ ������ ���� �޼��忡�� ������ �����ߴ�
		 
		 # �ν��Ͻ� 2�� #
		 SHOPID = "1";
		 USERID = "1";
		 
		 �� �ν��Ͻ��� ��� ���� �ۼ��� �ӽ� �ν��Ͻ��� �Ѱ��ְ� ȣ���߽��ϴ�.
		 
		 
		 ���������� writeReviewProc ���� 
		 - �������� userID �� shopID�� ���� �� ���� ��������Ʈ�ѷ����� ȣ���� �Ǳ⶧����
		 - �Ѱܹ޾Ҵ� uesrID�� shopID�� �Ѱ��ֵ��� �����߽��ϴ�
		 
		 ���� writeReviewProc �޼������ �Ǽ����� �����غ��ø� �Ѱܹ��� ������ �����ϵ��� �����߽��ϴ�.
		 
		 *** �߰� ***
		 - �ȵ� �� �˾Ҵ� ���ΰ�ħ�� ������� ������ �˴ϴ�.
		 - ���ϴܿ� refresh() �޼��带 ���� ������µ���.
		 - ���� �̹� �����Ǿ������ϱ� ���� �����Ǵ� �����͵��� ���� �ٽ� �ҷ��ͼ� ��������
		 - �ܼ��ϰ� �׳� ������ �ҷ����� �κ��� ��ȣ�� �߽��ϴ�.
		 - �켱 �̺κ��� ���۽�Ű���� �� ������ ������ �ʿ��� �����ʹ� �� ��Ʈ�ѷ����� ó���� �ϴ°� ���Ƽ�
		 - �� ��Ʈ�ѷ����� ���ΰ�ħ�� ���ִ°� �´ٰ�(?), �����ٰ� �Ǵ��߽��ϴ�.
		 - �׷��� ���侲�� ��ư�� �������� �����ۼ��� ���� ���� ���񽺵��� ���İ��� �� ���Ƽ�
		 - ���ʷ� ���� �ۼ� ��ư�� ������� this�� �̿��ؼ� �ڱ� �ڽ��� ��ü�� �ѱ��
		 - �ۼ��� �����ڿ� DB�� ������ �����̳����� �� ��Ʈ�ѷ��� ������   refresh() �� ȣ���ϴ� ������� �����߽��ϴ�.
		 
		 �ٵ� �����̶� ���� �κ��� fxml �ε� ����� �ٸ� ��Ʈ�ѷ� ���񽺶� �ٸ��� �����Ǿ��־ �׺κ��� ���ΰ�ħ ���� �ȉϴϴ�.
		 ���� ���� �غ��ž��ҵ�!
		 */
		
		// �� ���������� �������� startPageController ���� �������µ� 
		// �� �޼��尡 �ᱹ���� startPageController �� ��ü�ϴ� �ӽ� �޼����� ���̶�
		//  ���ʿ��� ���������� ���ι޾� ��������ϴ�.
		// ���߿� �ϼ��� �����ϽǶ� �����ؼ� �����ø� �� �ſ���.
		
		// �� �������� �ȸ�����༭ 1�ð����� �ظ���ϴ�. 
		// ���������� �ȸ��� Tap���񽺿��� VBox�� ��ã�Ƽ� �ֱ׷��ǰ� �ϰ� ��~~~�� ����߽��ϴ�.
		// ���� ���� �ٸ� �޼��忡 �������� ����ִ��پ˰� �־��׿�.
		// �ۼ��ϽǶ� �ڵ������ϸ鼭 �ּ� �޸� ���� ���ٰ� �����մϴ�.
		// �ڵ� ������ ó���� �����ϰ� �� ����� �ƴ϶� ���̸� �����ϰ� ¥���� �ߴµ� �� ������ Ʋ�ȳ׿� . �� ¥�� �� �����ϴ�.
		// � ������.
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		
		TextField usrID = (TextField)testRoot.lookup("#txtuserID");
		TextField restaurantID = (TextField)testRoot.lookup("#InsertEmailTF");
		
		
		System.out.println("restaurantID : "+restaurantID.getText() + ", userID : " + usrID.getText() );
		this.shopID = restaurantID.getText();
		this.userID = usrID.getText();
		
		stage.show();
		
		// �� �ϴ� �κп��� ������ �ٸ��� userID �� shopID ���Դϴ�. ��� �ڵ� õõ�� ���캸�ø�
		// �ݹ� �˾������� �ſ���.
		inforserv.TopInformation(root, shopID, userID );
		tabserv.TabMenu(root, shopID);
		tabserv.TabReview(root, shopID, userID, this);
		comserv.CloseWindow(e);
		
		// �켱 ���� ��������������  "" ���丮 �������������� �ش� ��ü�� ������ ������ "" �������� 
		// �ذ��Ѱ��̶� �ٸ� ������ �߻��Ǹ� ������ ��ġ�ž��ҰͰ����ϴ�. ���� �Ұ� ���ٱ���
		
		/*
		 * ###########      �׽�Ʈ ���      ############# 
		 *  
		 *  - �ӽ� ���������� ����������ȣ�� �Ĵ������ȣ �Է½� ���� ����
		 *  - ���侲�� ��ư �̺�Ʈ ���� ����
		 *  - ���並 �ۼ��ϰ� ��Ͻ� DB�� insert �̺�Ʈ ���� ����
		 *  
		 * #########################################
		 *  
		 */
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
		inforserv.BookmarkServ(root, shopID, userID, false);
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
		tabserv.WriteReviewServ(stage, root, userID, shopID, this);
		//root.setVisible(true);
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
