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
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable {
	private Parent root;
	private FirstPageService service;
	private LoginService loginServ;
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		setTextProperty();
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
			Stage mainStage = new Stage();
			Parent mainPageRoot;
			Scene sc;
			//service.showWindow(mainStage, "../../MainPage/MainPage.fxml", "../../MainPage/MainPage.css");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage/MainPage.fxml"));
			
			// 로그인 성공시 메인페이지 오픈
			try {
				mainPageRoot = loader.load();
				MainPageController mainCon = loader.getController();
				mainCon.setRoot(mainPageRoot);
				mainCon.setUsrID(usrID);
				sc = new Scene(mainPageRoot);
				sc.getStylesheets().add(getClass().getResource("../MainPage/MainPage.css").toString());
				mainStage.setScene(sc);
				mainStage.show();
				
				// 현재 창 닫기
				Stage stg = (Stage)root.getScene().getWindow();
				stg.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
