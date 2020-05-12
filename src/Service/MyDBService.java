package Service;

import java.util.Map;

public interface MyDBService {
	public Map<Integer, String> selectDB(String SQL, String fieldname);
	public void insertView(String shopId);
	public String InformationSQL(String shopId);
	public String MenuSQL(String shopId);
	public String ReviewSQL(String shopId);
	public String BookmarkSQL(String userId);
	public String WriteSQL(String userId);
	public String EditSQL(String reviewId);
	public String EmailSQL(String shopId);
	public void insertBookmark(String shopId, String userId);
	public void deleteBookmark(String userId);
	public void writeReview(String shopId, String userId, float grade, String text, String imgurl);
	public String FindEditReview(String writeday, String userId);
	public void EditReview(String reviewId, String text, String imgurl);
	public void DeleteReview(String reviewId);
}
