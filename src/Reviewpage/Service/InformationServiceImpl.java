package Reviewpage.Service;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;

public class InformationServiceImpl implements InformationService{
	MyDBService dbserv;
	Parent form;
	ToggleButton bookmarkBtn;
	public InformationServiceImpl(){
		dbserv = new MyDBServiceImpl();
	}
	//라벨만들기
	private void ShowLabel(String fxId, String labeltxt) {
		Label lbl = (Label)form.lookup(fxId);
		lbl.setText(labeltxt);
	}
	
	//db평균 
	private float getDBgrade(String shopId) {
		return Float.parseFloat(dbserv.selectDB(dbserv.InformationSQL(shopId), "avg(grade)").get(0));
	}//별평점
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
		ShowLabel("#TitleStarLbl", star);
	}
	//평점
	private void ShowGradeLabel(String shopId) {
		String str = "(";
		str += String.format("%.1f", getDBgrade(shopId)) + "/5.0)";
		ShowLabel("#TitleStarNumLbl", str);
	}
	public void BookmarkSys(Parent form, String shopId, String userId, boolean mark) {
		bookmarkBtn = (ToggleButton)form.lookup("#TitleBookmarkBtn");
		if(bookmarkBtn.isSelected() || mark) {
			if(mark)	bookmarkBtn.setSelected(true);
			bookmarkBtn.setEffect(new ImageInput(new Image("/Reviewpage/image/bookmark2.PNG")));
			dbserv.insertBookmark(shopId, userId);
		}else {
			bookmarkBtn.setEffect(new ImageInput(new Image("/Reviewpage/image/bookmark.PNG")));
			dbserv.deleteBookmark(shopId, userId);
		}
	}
	//음식점 타이틀 출력
	@Override
	public void TopInformation(Parent form, String shopId, String userId) {
		this.form = form;
		dbserv.insertView(shopId);	//조회수 증가("식당식별자")
		ShowLabel("#TitleLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "shop_name").get(0));
		String bookmark = dbserv.selectDB(dbserv.BookmarkSQL(userId), "user_id").get(0);
		if(bookmark==null) 
			BookmarkSys(form, shopId, userId, false);
		else 
			BookmarkSys(form, shopId, userId, true);
		
		ShowGradeLabel(shopId);
		ShowStar(shopId);
		ShowLabel("#TitleAddressLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "shop_address1").get(0));
		ShowLabel("#TitleAddressLbl2", dbserv.selectDB(dbserv.InformationSQL(shopId), "shop_address2").get(0));
		ShowLabel("#TitleKindfoodLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "shop_kind").get(0));
		ShowLabel("#TitleViewLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "shop_view").get(0));
	}
	
	
}
