package Reviewpage.Service;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface InformationService {
	public void TopInformation(Parent form, String ShopId, String userId);
	public void BookmarkImg(String shopId, String userId);
	public void BookmarkServ(String shopId, String userId);
	public void LinkServ(Stage stage, Parent form, String shopId);
}