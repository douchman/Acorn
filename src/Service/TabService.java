package Service;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface TabService {
	public void TabMenu(Parent form, String shopId);
	public void TabReview(Parent form, String shopId, String userId);
	public void WriteReviewServ(Stage stage, Parent form, String userId);
}
