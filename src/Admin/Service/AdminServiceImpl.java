package Admin.Service;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class AdminServiceImpl implements AdminService{
	
		@Override
	public Parent OpenBobManageForm() {
		CommonService comServ = new CommonServiceImpl();
		Stage membershipForm = new Stage();
		Parent form =  comServ.showWindow(membershipForm, "../BobManagePage.fxml");
		
		return form;
	}
	
	
	
	
	
}








