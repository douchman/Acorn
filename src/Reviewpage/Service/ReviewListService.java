package Reviewpage.Service;

import Reviewpage.Review.ReviewListController;
import Reviewpage.Review.ReviewPageController;
import javafx.scene.Parent;

public interface ReviewListService {
	public String findWriteday(Parent form);
	public String findReviewId(String userId, String writeday);
	public void DeleteReviewServ(Parent form, String userId);
	public void UpdateReviewServ(Parent form, String shopId, String userId, String reviewId, ReviewListController rvlistCtrl, ReviewPageController rvpageCtrl);
}
