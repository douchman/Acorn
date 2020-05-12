package Service;

import javafx.scene.Parent;

public interface ReviewListService {
	public String findWriteday(Parent form);
	public String findReviewId(String userId, String writeday);
	public void DeleteReviewServ(Parent form, String userId);
	public void UpdateReviewServ(Parent form, String userId, String reviewId);
}
