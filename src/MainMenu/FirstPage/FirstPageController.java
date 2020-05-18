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

	// 박상현 수정
		private Stage firstPage;
		private Stage mainPage;
		private Stage loginStg;
		// 이 퍼스트 페이지 컨트롤러에서 메인컨트롤러를 만들어줄때
		// 메인컨트롤러에서 뒤로가기 처럼 동작하게 만들어야한다.
		// 메인컨 -> 뒤로 가기시 -> 새로운 firstPage를 만들지 않고 스테이지를 받아와야 함
		// 즉 메인컨에 firstPage 의 stage 객체를 받아와야한다.
		
		
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
		System.out.println("퍼스트페이지 컨트롤러 메인 페이지  ! : " +  mainPage);

	}
	
	// 박상현 수정
		public void setFirstPage(Stage firstPage) {
			// 메인에서 실행될때 퍼스트페이지 즉
			// 초기화면의 Stage값을 가져온다.
			this.firstPage = firstPage;
		}
		
		public void openMainPage() {
			// 여기서 메인 페이지를 구성해줄때 반드시
			// 초기화면의 객체를 넣어주어야한다.
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
		// 일단은 그냥 닫는다
		//Stage stg = (Stage)root.getScene().getWindow();
		//stg.close();
		firstPage.close();
				
		loginStg.show();
	}
	
}
