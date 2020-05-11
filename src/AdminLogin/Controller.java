package AdminLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public  class Controller {
	
 
    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField txtUserName;
    
    @FXML
    private TextField txtPassword;
    
    public void Login(ActionEvent event) throws Exception{
        if(txtUserName.getText().equals("admin") && txtPassword.getText().equals("1234")){ 
            lblStatus.setText("Admin Login Success");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/AdminLogin/AdminPage.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }else{
            lblStatus.setText("Admin Login Failed");
        }
    }
    public void BobManageBtn(ActionEvent event) throws Exception {
    Stage primaryStage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/AdminLogin/BobManagePage.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    public void AddBob(ActionEvent event) throws Exception{
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminLogin/AddBobPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
    public void MemberManageBtn(ActionEvent event) throws Exception{
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/AdminLogin/MemberManagePage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
    public void ResetBtn(ActionEvent event) {
    	
    	
    }
    public void CancelBtn(ActionEvent event) {
    	
    }
}
    
