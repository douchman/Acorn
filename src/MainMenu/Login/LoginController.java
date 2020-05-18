package MainMenu.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainMenu.FirstPage.Controller;
import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import MainMenu.Login.Service.LoginService;
import MainMenu.Login.Service.LoginServiceImpl;
import MainMenu.MainPage.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable {
	private Parent root;
	private FirstPageService service;
	private LoginService loginServ;
	
	// 박상현 추가
		private Stage mainStage;
		private MainPageController MainPageCon;
		
		
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		setTextProperty();
	}

	public void getMainStage(Stage mainStage){
		this.mainStage = mainStage;
	}
	
	public void getMainCon(MainPageController MainPageCon) {
		this.MainPageCon = MainPageCon;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new FirstPageServiceImpl();
		loginServ = new LoginServiceImpl();
		
	}
	public void CreatAccount(ActionEvent e) {
		Stage mstage = new Stage();
		service.showWindow(mstage, "../../Creat/Creat.fxml", "../../Creat/Creat.css");
		service.WindowClose(e);
	}

	public void LoginBtnPressed(ActionEvent e) {
		// 수정
		String usrID = loginServ.LoginProc(root);
		if (usrID!=null){
			//Stage mainStage = new Stage();
			//Parent mainPageRoot;
			//Scene sc;
			//service.showWindow(mainStage, "../../MainPage/MainPage.fxml", "../../MainPage/MainPage.css");
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage/MainPage.fxml"));
			
			//mainPageRoot = loader.load();
			//MainPageController mainCon = loader.getController();
			//mainCon.setRoot(mainPageRoot);
			//mainCon.setUsrID(usrID);
			//sc = new Scene(mainPageRoot);
			//sc.getStylesheets().add(getClass().getResource("../MainPage/MainPage.css").toString());
			//mainStage.setScene(sc);
			Stage loginPage = (Stage)root.getScene().getWindow();
			//System.out.println(MainPageCon);
			TextField idField = (TextField)root.lookup("#emailTxtF");
			TextField pwField = (TextField)root.lookup("#pwField");
			
			idField.clear();
			pwField.clear();
			MainPageCon.setUsrID(usrID);
			MainPageCon.setLogoutBtn();
			MainPageCon.setLoginPage(loginPage);
			mainStage.show();
			
			// 현재 창 닫기
			Stage stg = (Stage)root.getScene().getWindow();
			stg.close();
			
		}
	}
	public void setTextProperty() {
		loginServ.setTextFieldProperty(root);
	}

	public void AdminLogin(ActionEvent e) {
		loginServ.AdminLogin(root);
		service.WindowClose(e);
	}
}
