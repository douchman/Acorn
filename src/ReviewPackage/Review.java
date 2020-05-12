package ReviewPackage;

public class Review {

	String reviewID, restaurantID, 
		   userID, resScore, 
	       reviewContents, imgURL,date;
	
	
	// 리뷰글 자체에대한 iD
	// 식당 ID
	// 작성한 유저 ID
	// 식당에대한 점수
	// 리뷰 작성 내용
	// 이미지 URL
	// 날짜
	
	public Review(String reviewID, String restaurantID, 
			String userID, String resScore, 
			String reviewContents,String imgURL,String date) {
		this.reviewID = reviewID; 
		this.restaurantID = restaurantID;
		this.userID = userID;
		this.resScore =resScore;
		this.reviewContents = reviewContents;
		this.imgURL = imgURL;
		this.date = date;
	}
	
	
	public String getReviewID() {
		return reviewID;
	}


	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}


	public String getRestaurantID() {
		return restaurantID;
	}


	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getResScore() {
		return resScore;
	}


	
	

	public void setResScore(String resScore) {
		this.resScore = resScore;
	}


	public String getReviewContents() {
		return reviewContents;
	}


	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}


	public String getImgURL() {
		return imgURL;
	}


	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	
}
