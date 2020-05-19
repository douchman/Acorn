package Reviewpage.Service;

import java.util.List;
import java.util.Map;

import Reviewpage.Review.ReviewListController;
import Reviewpage.Review.ReviewPageController;
import Reviewpage.Review.WriteReviewController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/*
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
*/
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TabServiceImpl implements TabService{
	MyDBService dbserv;
	CommonService comserv;
	ReviewListController rvlistCtrl;
	WriteReviewController writeCtrl;
	List<Parent> lstmenuPane;
	
	public TabServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	/*
	//이미지뷰어(미사용)
	private void ReviewImgview(Parent form, String imgURL) {
		ImageView imgview = (ImageView)form.lookup("#TabReviewImgview");
		imgview.setImage(new Image(imgURL));
	}
	*/
	//메뉴 탭 보여주기
	@Override
	public void TabMenu(Parent form, String shopId) {	
		Map<Integer, String> Mapmenu = dbserv.selectDB(dbserv.MenuSQL(shopId), "shop_menu");
		VBox menulstvbox = (VBox)form.lookup("#TabMenuListVBox");
		menulstvbox.getChildren().clear();
		for(Integer i : Mapmenu.keySet()) {
			Parent menulstform = comserv.ListForm("/Reviewpage/Review/MenuList.fxml", false);
			comserv.ShowLabel(menulstform, "#TabMenuName", dbserv.selectDB(dbserv.MenuSQL(shopId), "shop_menu").get(i));
			comserv.ShowLabel(menulstform, "#TabMenuPrice", dbserv.selectDB(dbserv.MenuSQL(shopId), "price").get(i));
			menulstvbox.getChildren().add(menulstform);
		}
	}
	//본인의 글만 수정,삭제 버튼 활성화
	private void editBtn(Parent form, String fxId) {
		Button btn = (Button)form.lookup(fxId);
		btn.setDisable(false);
		btn.setOpacity(1);
	}
	//점수에 따른 별 보여주기
	private String gradeStar(int grade) {
		String star = "";
		for(int s=0;s<grade;s++) {
			star += "★";
		}
		for(int s=0;s<5-grade;s++) {
			star += "☆";
		}
		return star;
	}
	//이메일아이디 일부 숨기기
	private String hideEmail(String email) {
		int elen = email.substring(3).length();
		email = email.substring(0,3);
		for(int i=0;i<elen;i++)	email += "*";
		return email;
	}
	private String isAdmin(String userId) {
		return dbserv.selectDB(dbserv.AdminSQL(userId), "admin_set").get(0);
	}
	//리뷰 탭 보여주기
	@Override
	public void TabReview(Parent form, String shopId, String userId, ReviewPageController reviewCon) {
		Map<Integer, String> Maprevw = dbserv.selectDB(dbserv.ReviewSQL(shopId), "email");
		VBox revwlstvbox = (VBox)form.lookup("#TabReviewListVBox");
		revwlstvbox.getChildren().clear();
		for(Integer i : Maprevw.keySet()) {
			Parent revwlstform = comserv.ListForm("/Reviewpage/Review/ReviewList.fxml", true);
			rvlistCtrl = comserv.getLoaderListForm().getController();
			rvlistCtrl.setShopID(shopId);
			rvlistCtrl.setUserID(userId);
			rvlistCtrl.setReviewCtrler(reviewCon);
			
			String email = hideEmail(dbserv.selectDB(dbserv.shopEmailSQL(shopId), "substr(email, 1, instr(email,'@')-1)").get(i));
			comserv.ShowLabel(revwlstform, "#TabReviewWriter", dbserv.selectDB(dbserv.ReviewSQL(shopId), "name").get(i)+"("+email+")");
			
			String star = gradeStar((int)Float.parseFloat(dbserv.selectDB(dbserv.ReviewSQL(shopId), "grade").get(i)));
			comserv.ShowLabel(revwlstform, "#TabReviewStar", star);
			
			String imgURL = dbserv.selectDB(dbserv.ReviewSQL(shopId), "imgURL").get(i);
			if(imgURL==null)	
				;
			else
				;
				//ReviewImgview(revwlstform, imgURL);
			
			comserv.ShowLabel(revwlstform, "#TabReviewWriteDate", dbserv.selectDB(dbserv.ReviewSQL(shopId), "writeday").get(i));
			comserv.ShowLabel(revwlstform, "#TabReviewContents", dbserv.selectDB(dbserv.ReviewSQL(shopId), "review").get(i));
			String admin = isAdmin(userId);
			if(admin == null)
				admin = "null";
			if(Maprevw.get(i).equals(userId) || admin.equals("1")) {	
				editBtn(revwlstform, "#TabReviewEditBtn");	//수정 버튼
				editBtn(revwlstform, "#TabReviewDeleteBtn");	//삭제 버튼
			}
			revwlstvbox.getChildren().add(revwlstform);
			
		}
	}
	//리뷰 쓰기 버튼
	@Override
	public void WriteReviewServ(Stage stage, Parent form, String userId , String shopId, ReviewPageController rvCon) {
		
		stage = new Stage();
		comserv.OpenWindow(stage, "/Reviewpage/Review/WriteReview.fxml", "Write Review");
		form = comserv.getRoot();
		writeCtrl = comserv.getLoaderListForm().getController();
		writeCtrl.setShopID(shopId);
		writeCtrl.setUserID(userId);
		writeCtrl.setReviewCtrler(rvCon);
		
		Label lbl = (Label)form.lookup("#WriterNameLbl");
		String email = dbserv.selectDB(dbserv.userEmailSQL(userId), "substr(email, 1, instr(email,'@')-1)").get(0);
		lbl.setText(dbserv.selectDB(dbserv.userWriteSQL(userId), "name").get(0)+"("+ email +")");
	}
	
}