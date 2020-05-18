package Admin.AdminLogin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public class Controller extends Controller2 implements Initializable{
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
    
    FXMLLoader loader;
    Parent root2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// TODO Auto-generated method stub
    	
    }
    public void Login(ActionEvent event) throws Exception{
    	
        if(txtUserName.getText().equals("admin") && txtPassword.getText().equals("1234")){ 
            lblStatus.setText("Admin Login Success");
            Stage primaryStage = new Stage();
            //Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
            loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            root2 = loader.load();
            Controller con = loader.getController();
            System.out.println(con);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }else{
            lblStatus.setText("Admin Login Failed");
        }
    }
    public void BobManageBtn(ActionEvent event) throws Exception {
    Stage primaryStage = new Stage();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("BobManagePage.fxml"));
    Parent root = loader.load();
    //Parent root = FXMLLoader.load(getClass().getResource("BobManagePage.fxml"));
    MembershipController memCon = loader.getController();
    memCon.setRoot(root);
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    
    public void AddBob(ActionEvent event) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBobPage.fxml"));
    	Parent root = loader.load();
    	Stage primaryStage = new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("AddBobPage.fxml"));
    	MembershipController memCon = loader.getController();
    	//memCon.setRoot(root);
    	memCon.setRoot(root);
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
