package Reviewpage.Service;

import java.util.Map;

public interface MyDBService {
	public Map<Integer, String> selectDB(String SQL, String fieldname);
	public void insertView(String shopId);
	public String InformationSQL(String shopId);
	public String MenuSQL(String shopId);
	public String ReviewSQL(String shopId);
	public String BookmarkSQL(String shopId, String userId);
	public String WriteSQL(String userId);
	public String EditSQL(String reviewId);
	public String shopEmailSQL(String shopId);
	public String userEmailSQL(String userId);
	public String AdminSQL(String userId);
	public void insertBookmark(String shopId, String userId);
	public void deleteBookmark(String shopId, String userId);
	public void writeReview(String shopId, String userId, float grade, String text, String imgurl);
	public String FindEditReview(String writeday, String userId);
	public void EditReview(String reviewId, String text, String imgurl);
	public void DeleteReview(String reviewId);
	public String userWriteSQL(String userId);
}