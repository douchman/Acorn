package MainMenu.FirstPage.Service;

import java.io.IOException;

import Admin.AdminLogin.AdminController;
import MainMenu.FirstPage.Controller;
import MainMenu.Login.LoginController;
import MainMenu.MainPage.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPageServiceImpl implements FirstPageService {

	
	// 박상현 추가
	private Stage mainStage;
	private MainPageController MainPageCon;
	
	
	// 박상현 수정
		@Override
		public Stage getMainPage(String formPath, String css,Stage  firstPage) {
			// 이 메서드는 메인페이지를 만들고 그 스테이지를 리턴해주는 메서드다
			// 초기페이지의 initilize 가 호출될때 한번만 호출되어서 해당 페이지를 객체로 만들어준다.
			FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
			Parent root = null;
			Scene scene;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 초기 진입을 안하면 페이지가 없네?
			MainPageCon  = loader.getController();
			MainPageCon.setRoot(root);
			MainPageCon.getFirstPage(firstPage);
			
			mainStage = new Stage();
			scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource(css).toString());
			mainStage.setScene(scene);
			
			return mainStage;
		}
		
		
		@Override
		public Stage getLoginPage(String formPath, String css, Stage firstPage) {
			// 이 메서드는 로그인페이지를 만들고 그 스테이지를 리턴해주는 메서드다
			// 초기페이지의 initilize 가 호출될때 한번만 호출되어서 해당 페이지를 객체로 만들어준다.
			FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
			Parent root = null;
			Stage LoginPage;
			Scene scene;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LoginController loginCon  = loader.getController();
			loginCon.setRoot(root);
			loginCon.getMainStage(mainStage);
			loginCon.getMainCon(MainPageCon);
			LoginPage = new Stage();
			scene = new Scene(root);
			scene.getStylesheets().add(
							getClass().getResource(css).toString());
			LoginPage.setScene(scene);
					
			return LoginPage;
		}
		
		
		
	@Override
	public Parent showWindow(Stage s, String formPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(s);
		Controller ctrler = loader.getController();
		ctrler.setRoot(root);
		s.setResizable(false);
		s.show();
		
		return root;
	}
	@Override
	public Parent showAdminPage(Stage s, String formPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(s);
		AdminController ctrler = loader.getController();
		ctrler.setRoot(root);
		s.setResizable(false);
		s.show();
		
		return root;
	}

	@Override
	public Parent showWindow(Stage s, String formPath, String css) {
		// 메인페이지 불러오는 메서드
		// 로그인페이지 불러오는 메서드
		// 메인페이지에서 뒤로 가기 했을때 새로 창을 만들어주는 메서드
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(css).toString());
			s.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Controller ctrler = loader.getController();
		ctrler.setRoot(root);
		s.setResizable(false);
		s.show();
		
		return root;
	}
	
	

	public void WindowClose(ActionEvent event) {
		Parent root = (Parent)event.getSource();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	public void WindowClose(Parent root) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	
}
