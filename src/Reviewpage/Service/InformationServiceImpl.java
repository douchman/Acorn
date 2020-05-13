package Reviewpage.Service;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InformationServiceImpl implements InformationService{
	MyDBService dbserv;
	CommonService comserv;
	Parent form;
	ToggleButton bookmarkBtn;
	
	public InformationServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	//총 리뷰점수의 평균
	private float getDBgrade(String shopId) {
		return Float.parseFloat(dbserv.selectDB(dbserv.InformationSQL(shopId), "avg(grade)").get(0));
	}//점수로 별 그리기
	private void ShowStar(String shopId) {
		int grade = (int)getDBgrade(shopId);
		String star = "[";
		for(int i=0;i<grade;i++) {
			star += "★";
		}
		for(int i=0;i<5-grade;i++) {
			star += "☆";
		}
		star += "]";
		comserv.ShowLabel(form, "#TitleStarLbl", star);
	}
	//총 평점 표현
	private void ShowGradeLabel(String shopId) {
		String str = "(";
		str += String.format("%.1f", getDBgrade(shopId)) + "/5.0)";
		comserv.ShowLabel(form, "#TitleStarNumLbl", str);
	}
	//북마크 서비스
	public void BookmarkServ(Parent form, String shopId, String userId, boolean mark) {
		bookmarkBtn = (ToggleButton)form.lookup("#TitleBookmarkBtn");
		if(bookmarkBtn.isSelected() || mark) {
			if(mark)	
				bookmarkBtn.setSelected(true);
			bookmarkBtn.setEffect(new ImageInput(new Image("/image/bookmark2.PNG")));
			dbserv.insertBookmark(shopId, userId);
		}else {
			bookmarkBtn.setEffect(new ImageInput(new Image("/image/bookmark.PNG")));
			dbserv.deleteBookmark(userId);
		}
	}
	//링크 서비스
	@Override
	public void LinkServ(Stage stage, Parent form, String shopId) {
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPackage/LinkPage.fxml", "Link");
		form = comserv.getRoot();
		Label lbl = (Label)form.lookup("#LinkShopNameLbl");
		TextField tf = (TextField)form.lookup("#LinkInformationTF");
		lbl.setText(dbserv.selectDB(dbserv.InformationSQL(shopId), "name").get(0));
		tf.setText(dbserv.selectDB(dbserv.InformationSQL(shopId), "address1").get(0)+ " " +
				dbserv.selectDB(dbserv.InformationSQL(shopId), "address2").get(0));
	}
	//음식점 타이틀 출력
	@Override
	public void TopInformation(Parent form, String shopId, String userId) {
		this.form = form;
		dbserv.insertView(shopId);	//조회수 증가("식당식별자")
		comserv.ShowLabel(form, "#TitleLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "name").get(0));
		String bookmark = dbserv.selectDB(dbserv.BookmarkSQL(userId), "email").get(0);
		if(bookmark==null) 
			BookmarkServ(form, shopId, userId, false);
		else 
			BookmarkServ(form, shopId, userId, true);
		
		ShowGradeLabel(shopId);
		ShowStar(shopId);
		comserv.ShowLabel(form, "#TitleAddressLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "address1").get(0));
		comserv.ShowLabel(form, "#TitleAddressLbl2", dbserv.selectDB(dbserv.InformationSQL(shopId), "address2").get(0));
		comserv.ShowLabel(form, "#TitleKindfoodLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "category").get(0));
		comserv.ShowLabel(form, "#TitleViewLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "view").get(0));
	}
	
	
	
}
