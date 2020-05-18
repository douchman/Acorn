package MainMenu.FirstPage;

import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FirstPageController extends Controller implements Initializable {
	@SuppressWarnings("unused")
	private Parent root;
	private FirstPageService service;

	// �ڻ��� ����
		private Stage firstPage;
		private Stage mainPage;
		private Stage loginStg;
		// �� �۽�Ʈ ������ ��Ʈ�ѷ����� ������Ʈ�ѷ��� ������ٶ�
		// ������Ʈ�ѷ����� �ڷΰ��� ó�� �����ϰ� �������Ѵ�.
		// ������ -> �ڷ� ����� -> ���ο� firstPage�� ������ �ʰ� ���������� �޾ƿ;� ��
		// �� �������� firstPage �� stage ��ü�� �޾ƿ;��Ѵ�.
		
		
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new FirstPageServiceImpl();
		mainPage = service.getMainPage
				("../../MainPage/MainPage2.fxml", "../../MainPage/MainPage.css",firstPage);
		loginStg = service.getLoginPage
				("../../Login/login.fxml", "../../Login/login.css",mainPage);
		System.out.println("�۽�Ʈ������ ��Ʈ�ѷ� ���� ������  ! : " +  mainPage);

	}
	
	// �ڻ��� ����
		public void setFirstPage(Stage firstPage) {
			// ���ο��� ����ɶ� �۽�Ʈ������ ��
			// �ʱ�ȭ���� Stage���� �����´�.
			this.firstPage = firstPage;
		}
		
		public void openMainPage() {
			// ���⼭ ���� �������� �������ٶ� �ݵ��
			// �ʱ�ȭ���� ��ü�� �־��־���Ѵ�.
			mainPage.show();
		}
		
		
	public void gotoMain(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../MainPage/MainPage.fxml", "../../MainPage/MainPage.css");
		service.WindowClose(e);

	}
	
	public void login() {
		//Stage mstage = new Stage();
		//service.showWindow(mstage, "../../Login/login.fxml", "../../Login/login.css");
		//Stage loginStg = service.getLoginPage("../../Login/login.fxml", "../../Login/login.css",mainPage);
		// �ϴ��� �׳� �ݴ´�
		//Stage stg = (Stage)root.getScene().getWindow();
		//stg.close();
		firstPage.close();
				
		loginStg.show();
	}
	
}
