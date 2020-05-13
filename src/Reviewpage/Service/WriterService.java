package Reviewpage.Service;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface WriterService {
	public String getTA(Parent form, String fxId);
	public Integer ToggleStar(Parent form, String number);
	public int getGrade(Parent form);
	public void ImgUploadBtnServ(Stage stage, Parent form);
	public void submitBtnServ(Parent form, String shopId, String userId);
	public void EditBtnServ(Parent form);
}
