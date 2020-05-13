package Reviewpage.Service;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface InformationService {
	public void TopInformation(Parent form, String ShopId, String userId);
	public void BookmarkServ(Parent form, String shopId, String userId, boolean mark);
	public void LinkServ(Stage stage, Parent form, String shopId);
}
