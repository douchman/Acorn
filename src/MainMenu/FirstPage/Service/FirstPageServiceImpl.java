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

	
	// �ڻ��� �߰�
	private Stage mainStage;
	private MainPageController MainPageCon;
	
	
	// �ڻ��� ����
		@Override
		public Stage getMainPage(String formPath, String css,Stage  firstPage) {
			// �� �޼���� ������������ ����� �� ���������� �������ִ� �޼����
			// �ʱ��������� initilize �� ȣ��ɶ� �ѹ��� ȣ��Ǿ �ش� �������� ��ü�� ������ش�.
			FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
			Parent root = null;
			Scene scene;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �ʱ� ������ ���ϸ� �������� ����?
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
			// �� �޼���� �α����������� ����� �� ���������� �������ִ� �޼����
			// �ʱ��������� initilize �� ȣ��ɶ� �ѹ��� ȣ��Ǿ �ش� �������� ��ü�� ������ش�.
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
		// ���������� �ҷ����� �޼���
		// �α��������� �ҷ����� �޼���
		// �������������� �ڷ� ���� ������ ���� â�� ������ִ� �޼���
		
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
