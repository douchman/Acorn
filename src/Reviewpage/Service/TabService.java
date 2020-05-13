package Reviewpage.Service;


import Reviewpage.Review.ReviewPageController;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface TabService {
	public void TabMenu(Parent form, String shopId);
	
	
	// 박상현 수정  ReviewPageController reviewCon 추가
	public void TabReview(Parent form, String shopId, String userId,ReviewPageController reviewCon);
	public void WriteReviewServ(Stage stage, Parent form, String userId,String shopid, ReviewPageController rvCon);
}
