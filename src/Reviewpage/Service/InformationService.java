package Reviewpage.Service;

import javafx.scene.Parent;

public interface InformationService {
	public void TopInformation(Parent form, String ShopId, String userId);
	public void BookmarkSys(Parent form, String shopId, String userId, boolean mark);
}
