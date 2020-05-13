package Admin.AdminLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public  class Controller extends Controller2{
	private Parent root;
 
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
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
            Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }else{
            lblStatus.setText("Admin Login Failed");
        }
    }
    public void BobManageBtn(ActionEvent event) throws Exception {
    Stage primaryStage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("BobManagePage.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    public void AddBob(ActionEvent event) throws Exception{
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddBobPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
    public void MemberManageBtn(ActionEvent event) throws Exception{
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MemberManagePage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
    public void AddAddressBtn(ActionEvent event) throws Exception{
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddAdress.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
    
    public void ResetBtn(ActionEvent event) {
    	
    	
    }
    public void CancelBtn(ActionEvent e) {
		Parent root = (Parent)e.getSource();
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
}
}
