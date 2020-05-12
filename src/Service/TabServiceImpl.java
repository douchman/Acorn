package Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ReviewPackage.ReviewListController;
import ReviewPackage.WriteReviewController;
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
	Map<Integer, String> Mapmenu;
	Map<Integer, String> Maprevw;
	ReviewListController reviewctr;
	List<Parent> lstmenuPane;
	String shopid;
	
	public TabServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
		Mapmenu = new TreeMap<Integer, String>();
		Maprevw = new TreeMap<Integer, String>();
	}
	//�̹������(�̻��)
	private void ReviewImgview(Parent form, String imgURL) {
		ImageView imgview = (ImageView)form.lookup("#TabReviewImgview");
		imgview.setImage(new Image(imgURL));
	}
	//�޴� �� �����ֱ�
	@Override
	public void TabMenu(Parent form, String shopId) {
		System.out.println("�Ѱܹ��� shopID: " + shopId);
		this.shopid = shopId;
		System.out.println(" �Էµ� shopID: " + this.shopid);
		Mapmenu = dbserv.selectDB(dbserv.MenuSQL(shopId), "menu");
		VBox menulstvbox = (VBox)form.lookup("#TabMenuListVBox");
		for(Integer i : Mapmenu.keySet()) {
			Parent menulstform = comserv.ListForm("/ReviewPackage/MenuList.fxml", false);
			comserv.ShowLabel(menulstform, "#TabMenuName", dbserv.selectDB(dbserv.MenuSQL(shopId), "menu").get(i));
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
	public void TabReview(Parent form, String shopId, String userId) {
		Maprevw = dbserv.selectDB(dbserv.ReviewSQL(shopId), "user_id");
		VBox revwlstvbox = (VBox)form.lookup("#TabReviewListVBox");
		for(Integer i : Maprevw.keySet()) {
			Parent revwlstform = comserv.ListForm("/ReviewPackage/ReviewList.fxml", true);
			String email = hideEmail(dbserv.selectDB(dbserv.EmailSQL(shopId), "substr(user_email, 1, instr(user_email,'@')-1)").get(i));
			comserv.ShowLabel(revwlstform, "#TabReviewWriter", dbserv.selectDB(dbserv.ReviewSQL(shopId), "user_name").get(i)+"("+email+")");
			
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
	public void WriteReviewServ(Stage stage, Parent form, String userId) {
		stage = new Stage();
		FXMLLoader loader= comserv.OpenReviewPage(stage, "/ReviewPackage/WriteReview.fxml", "Write Review");
		form = comserv.getRoot();
		WriteReviewController reviewCon = loader.getController();
		System.out.println("������ �ѱ�� �� shopid : " + this.shopid);
		reviewCon.setShopID(this.shopid);
		
		Label lbl = (Label)form.lookup("#WriterNameLbl");
		String email = dbserv.selectDB(dbserv.EmailSQL(userId), "substr(user_email, 1, instr(user_email,'@')-1)").get(0);
		lbl.setText(dbserv.selectDB(dbserv.WriteSQL(userId), "user_name").get(0)+"("+ email +")");
	}
	
}
