package Reviewpage.Service;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ReviewListServiceImpl implements ReviewListService{
	MyDBService dbserv;
	CommonService comserv;
	
	public ReviewListServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	public String findWriteday(Parent form) {
		return comserv.getLabel(form, "#TabReviewWriteDate");
	}
	public String findReviewId(String userId, String writeday) {
		return dbserv.FindEditReview(writeday, userId);
	}
	@Override
	public void DeleteReviewServ(Parent form, String userId) {
		// TODO Auto-generated method stub
		System.out.println(userId);
		dbserv.DeleteReview(dbserv.FindEditReview(findWriteday(form), userId));
	}

	private void FixGradeStar(Label lbl, int grade) {
		switch(grade) {
		case 1:	lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar01.PNG")); break;
		case 2: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar02.PNG")); break;
		case 3: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar03.PNG")); break;
		case 4: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar04.PNG")); break;
		case 5: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar05.PNG")); break;
		}
	}
	@Override
	public void UpdateReviewServ(Parent form, String userId, String reviewId) {
		String email = dbserv.selectDB(dbserv.EditSQL(reviewId), "substr(email, 1, instr(email,'@')-1)").get(0);
		comserv.ShowLabel(form, "#WriterNameLbl", dbserv.selectDB(dbserv.EditSQL(reviewId), "name").get(0)+"("+ email +")");
		
		Label starlbl = (Label)form.lookup("#WriteStarLbl");
		int grade = (int)Float.parseFloat(dbserv.selectDB(dbserv.EditSQL(reviewId), "grade").get(0));
		//System.out.println(grade);
		FixGradeStar(starlbl, grade);
	}
	
}
