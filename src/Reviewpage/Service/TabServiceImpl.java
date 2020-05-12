package Reviewpage.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TabServiceImpl implements TabService{
	MyDBService dbserv;
	CommonService comserv;
	Map<Integer, String> Mapmenu;
	Map<Integer, String> Maprevw;
	List<Parent> lstmenuPane;
	public TabServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
		Mapmenu = new TreeMap<Integer, String>();
		Maprevw = new TreeMap<Integer, String>();
	}
	//라벨출력
	private void ShowLabel(Parent form, String fxId, String labeltxt) {
		Label lbl = (Label)form.lookup(fxId);
		lbl.setText(labeltxt);
	}
	private void ReviewImgview(Parent form, String imgURL) {
		ImageView imgview = (ImageView)form.lookup("#TabReviewImgview");
		imgview.setImage(new Image(imgURL));
	}
	//메뉴 탭
	@Override
	public void TabMenu(Parent form, String shopId) {
		Mapmenu = dbserv.selectDB(dbserv.MenuSQL(shopId), "menu");
		VBox menulstvbox = (VBox)form.lookup("#TabMenuListVBox");
		for(Integer i : Mapmenu.keySet()) {
			Parent menulstform = comserv.ListForm("../Review/MenuList.fxml");
			ShowLabel(menulstform, "#TabMenuName", dbserv.selectDB(dbserv.MenuSQL(shopId), "menu").get(i));
			ShowLabel(menulstform, "#TabMenuPrice", dbserv.selectDB(dbserv.MenuSQL(shopId), "price").get(i));
			menulstvbox.getChildren().add(menulstform);
		}
	}
	private void editBtn(Parent form, String fxId) {
		Button btn = (Button)form.lookup(fxId);
		btn.setDisable(false);
		btn.setOpacity(1);
	}
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
	private String hideEmail(String email) {
		int elen = email.substring(3).length();
		email = email.substring(0,3);
		for(int i=0;i<elen;i++)	email += "*";
		return email;
	}
	@Override
	public void TabReview(Parent form, String shopId, String userId) {
		Maprevw = dbserv.selectDB(dbserv.ReviewSQL(shopId), "user_id");
		VBox revwlstvbox = (VBox)form.lookup("#TabReviewListVBox");
		for(Integer i : Maprevw.keySet()) {
			Parent revwlstform = comserv.ListForm("../review/ReviewList.fxml");
			
			String email = hideEmail(dbserv.selectDB(dbserv.EmailSQL(shopId), "substr(user_email, 1, instr(user_email,'@')-1)").get(i));
			ShowLabel(revwlstform, "#TabReviewWriter", dbserv.selectDB(dbserv.ReviewSQL(shopId), "user_name").get(i)+"("+email+")");
			
			String star = gradeStar((int)Float.parseFloat(dbserv.selectDB(dbserv.ReviewSQL(shopId), "grade").get(i)));
			ShowLabel(revwlstform, "#TabReviewStar", star);
			
			String imgURL = dbserv.selectDB(dbserv.ReviewSQL(shopId), "imgURL").get(i);
			System.out.println("imgURL"+i+" : "+imgURL);
			if(imgURL==null)	
				;
			else
				;
				//ReviewImgview(revwlstform, imgURL);
			
			ShowLabel(revwlstform, "#TabReviewWriteDate", dbserv.selectDB(dbserv.ReviewSQL(shopId), "writeday").get(i));
			ShowLabel(revwlstform, "#TabReviewContents", dbserv.selectDB(dbserv.ReviewSQL(shopId), "review").get(i));
			if(Maprevw.get(i).equals(userId)) {	
				editBtn(revwlstform, "#TabReviewEditBtn");
				editBtn(revwlstform, "#TabReviewDeleteBtn");
			}
			revwlstvbox.getChildren().add(revwlstform);
			
		}
	}

}
