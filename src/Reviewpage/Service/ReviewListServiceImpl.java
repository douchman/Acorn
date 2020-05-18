package Reviewpage.Service;

import Reviewpage.Review.EditReviewController;
import Reviewpage.Review.ReviewListController;
import Reviewpage.Review.ReviewPageController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ReviewListServiceImpl implements ReviewListService{
	MyDBService dbserv;
	CommonService comserv;
	public ReviewListServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	//�� ������ ��¥ ���� string���� ��ȯ
	@Override
	public String findWriteday(Parent form) {
		return comserv.getLabel(form, "#TabReviewWriteDate");
	}
	//writeday�� review_id�� ��ȯ�ϴ� �޼ҵ�
	@Override
	public String findReviewId(String userId, String writeday) {
		return dbserv.FindEditReview(writeday, userId);
	}
	//������� ����
	@Override
	public void DeleteReviewServ(Parent form, String userId) {
		dbserv.DeleteReview(dbserv.FindEditReview(findWriteday(form), userId));
	}
	//������ ���� �̹��� ��ȭ
	private void FixGradeStar(Label lbl, int grade) {
		switch(grade) {
		case 1:	lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar01.PNG")); break;
		case 2: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar02.PNG")); break;
		case 3: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar03.PNG")); break;
		case 4: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar04.PNG")); break;
		case 5: lbl.setGraphic(new ImageView("/Reviewpage/image/GoldStar05.PNG")); break;
		}
	}
	//������� ����(�̿�)
	@Override
	public void UpdateReviewServ(Parent form, String shopId, String userId, String reviewId, ReviewListController rvlistCtrl, ReviewPageController rvpageCtrl) {
		Stage stage = new Stage();
		comserv.OpenWindow(stage, "/Reviewpage/Review/EditReview.fxml", "Edit Review");	//OpenWindow�� ��� �ʿ�
		form = comserv.getRoot();
		EditReviewController editCtrl = comserv.getLoaderListForm().getController();
		editCtrl.setReviewID(reviewId);
		editCtrl.setReviewpageCtrl(rvpageCtrl);
		editCtrl.setReviewCtrler(rvlistCtrl);
		
		String email = dbserv.selectDB(dbserv.EditSQL(reviewId), "substr(email, 1, instr(email,'@')-1)").get(0);
		comserv.ShowLabel(form, "#WriterNameLbl", dbserv.selectDB(dbserv.EditSQL(reviewId), "name").get(0)+"("+ email +")");
		
		Label starlbl = (Label)form.lookup("#WriteStarLbl");
		int grade = (int)Float.parseFloat(dbserv.selectDB(dbserv.EditSQL(reviewId), "grade").get(0));
		FixGradeStar(starlbl, grade);
		TextArea textarea = (TextArea)form.lookup("#WriteContentsTA");
		textarea.setText(dbserv.selectDB(dbserv.EditSQL(reviewId), "review").get(0));
		comserv.ShowLabel(form, "#WriteReviewImgURLLbl", dbserv.selectDB(dbserv.EditSQL(reviewId), "imgURL").get(0));
	}
	
}