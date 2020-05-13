package Reviewpage.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Reviewpage.Review.ReviewListController;
import Reviewpage.Review.ReviewPageController;
import Reviewpage.Review.WriteReviewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TabServiceImpl implements TabService{
	MyDBService dbserv;
	CommonService comserv;
	ReviewListController reviewctr;
	List<Parent> lstmenuPane;
	String shopid;
	
	public TabServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	//�̹������(�̻��)
	private void ReviewImgview(Parent form, String imgURL) {
		ImageView imgview = (ImageView)form.lookup("#TabReviewImgview");
		imgview.setImage(new Image(imgURL));
	}
	//�޴� �� �����ֱ�
	@Override
	public void TabMenu(Parent form, String shopId) {	
		Map<Integer, String> Mapmenu = dbserv.selectDB(dbserv.MenuSQL(shopId), "shop_menu");
		VBox menulstvbox = (VBox)form.lookup("#TabMenuListVBox");
		menulstvbox.getChildren().clear();
		for(Integer i : Mapmenu.keySet()) {
			Parent menulstform = comserv.ListForm("/ReviewPackage/MenuList.fxml", false);
			comserv.ShowLabel(menulstform, "#TabMenuName", dbserv.selectDB(dbserv.MenuSQL(shopId), "shop_menu").get(i));
			comserv.ShowLabel(menulstform, "#TabMenuPrice", dbserv.selectDB(dbserv.MenuSQL(shopId), "price").get(i));
			menulstvbox.getChildren().add(menulstform);
		}
	}
	//������ �۸� ����,���� ��ư Ȱ��ȭ
	private void editBtn(Parent form, String fxId) {
		Button btn = (Button)form.lookup(fxId);
		btn.setDisable(false);
		btn.setOpacity(1);
	}
	//������ ���� �� �����ֱ�
	private String gradeStar(int grade) {
		String star = "";
		for(int s=0;s<grade;s++) {
			star += "��";
		}
		for(int s=0;s<5-grade;s++) {
			star += "��";
		}
		return star;
	}
	//�̸��Ͼ��̵� �Ϻ� �����
	private String hideEmail(String email) {
		int elen = email.substring(3).length();
		email = email.substring(0,3);
		for(int i=0;i<elen;i++)	email += "*";
		return email;
	}
	//���� �� �����ֱ�
	@Override
	public void TabReview(Parent form, String shopId, String userId, ReviewPageController reviewCon) {
		Map<Integer, String> Maprevw = dbserv.selectDB(dbserv.ReviewSQL(shopId), "email");
		VBox revwlstvbox = (VBox)form.lookup("#TabReviewListVBox");
		revwlstvbox.getChildren().clear();
		for(Integer i : Maprevw.keySet()) {
			Parent revwlstform = comserv.ListForm("/ReviewPackage/ReviewList.fxml", true);
			String email = hideEmail(dbserv.selectDB(dbserv.EmailSQL(shopId), "substr(email, 1, instr(email,'@')-1)").get(i));
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
			if(Maprevw.get(i).equals(userId)) {	
				editBtn(revwlstform, "#TabReviewEditBtn");
				editBtn(revwlstform, "#TabReviewDeleteBtn");
			}
			revwlstvbox.getChildren().add(revwlstform);
			
		}
	}
	//���� ���� ��ư
	@Override
	public void WriteReviewServ(Stage stage, Parent form, String userId , String shopId, ReviewPageController rvCon) {
		stage = new Stage();
		FXMLLoader loader= comserv.OpenReviewPage(stage, "/ReviewPackage/WriteReview.fxml", "Write Review");
		form = comserv.getRoot();
		WriteReviewController reviewCon = loader.getController();
		reviewCon.setShopID(shopId);
		reviewCon.setUserID(userId);
		
		// �̷��� ��Ʈ�ѷ��� ��ü�� �Ѱ���ϴ�.
		// ����Ʈ������Ʈ�ѷ��� �߰��� setReviewCon �޼��带 ���� 
		// ����Ʈ���伭�� �޼��尡 ȣ��ɶ� �Ѱܹ��� ������������Ʈ�ѷ� ��ü�� �Ѱ�����ϴ�.
		reviewCon.setReviewCon(rvCon);
		
		Label lbl = (Label)form.lookup("#WriterNameLbl");
		String email = dbserv.selectDB(dbserv.EmailSQL(shopId), "substr(email, 1, instr(email,'@')-1)").get(0);
		lbl.setText(dbserv.selectDB(dbserv.WriteSQL(userId), "name").get(0)+"("+ email +")");
	}
	
}
