package Reviewpage.Service;


import Reviewpage.Review.ReviewPageController;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface TabService {
	public void TabMenu(Parent form, String shopId);
	public void TabReview(Parent form, String shopId, String userId,ReviewPageController reviewCon);
	public void WriteReviewServ(Stage stage, Parent form, String userId,String shopid, ReviewPageController rvCon);
}
