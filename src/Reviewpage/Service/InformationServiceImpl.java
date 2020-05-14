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
	//�� ���������� ���
	private float getDBgrade(String shopId) {
		return Float.parseFloat(dbserv.selectDB(dbserv.InformationSQL(shopId), "avg(grade)").get(0));
	}
	//������ �� �׸���
	private void ShowStar(String shopId) {
		int grade = (int)getDBgrade(shopId);
		String star = "[";
		for(int i=0;i<grade;i++) {
			star += "��";
		}
		for(int i=0;i<5-grade;i++) {
			star += "��";
		}
		star += "]";
		comserv.ShowLabel(form, "#TitleStarLbl", star);
	}
	//�� ���� ǥ��
	private void ShowGradeLabel(String shopId) {
		String str = "(";
		str += String.format("%.1f", getDBgrade(shopId)) + "/5.0)";
		comserv.ShowLabel(form, "#TitleStarNumLbl", str);
	}
	//�ϸ�ũ �ʱ⼳��
	private void isBookmark(Parent form, String shopId, String userId) {
		String bookmark = dbserv.selectDB(dbserv.BookmarkSQL(shopId, userId), "email").get(0);
		bookmarkBtn = (ToggleButton)form.lookup("#TitleBookmarkBtn");
		if(userId.contentEquals("guest"))
			bookmarkBtn.setVisible(false);
		else
			bookmarkBtn.setVisible(true);
		
		if(bookmark==null) {
			bookmarkBtn.setSelected(false);
		}else {
			bookmarkBtn.setSelected(true);
		}
	}
	//�ϸ�ũ üũ���ο� ���� �̹��� ��ȭ
	@Override
	public void BookmarkImg(String shopId, String userId) {
		if(bookmarkBtn.isSelected()) {
			bookmarkBtn.setEffect(new ImageInput(new Image("/Reviewpage/image/bookmark2.PNG")));
		}else {
			bookmarkBtn.setEffect(new ImageInput(new Image("/Reviewpage/image/bookmark.PNG")));
		}
	}
	//�ϸ�ũ ����
	@Override
	public void BookmarkServ(String shopId, String userId) {
		if(bookmarkBtn.isSelected()) {
			dbserv.insertBookmark(shopId, userId);
		}else {
			dbserv.deleteBookmark(shopId, userId);
		}
	}
	//��ũ ����
	@Override
	public void LinkServ(Stage stage, Parent form, String shopId) {
		stage = new Stage();
		comserv.OpenWindow(stage, "/ReviewPage/Review/LinkPage.fxml", "Link");
		form = comserv.getRoot();
		Label lbl = (Label)form.lookup("#LinkShopNameLbl");
		TextField tf = (TextField)form.lookup("#LinkInformationTF");
		lbl.setText(dbserv.selectDB(dbserv.InformationSQL(shopId), "name").get(0));
		tf.setText(dbserv.selectDB(dbserv.InformationSQL(shopId), "address1").get(0)+ " " +
				dbserv.selectDB(dbserv.InformationSQL(shopId), "address2").get(0));
	}
	//������ Ÿ��Ʋ ���
	@Override
	public void TopInformation(Parent form, String shopId, String userId) {
		this.form = form;
		dbserv.insertView(shopId);	//��ȸ�� ����("�Ĵ�ĺ���")
		comserv.ShowLabel(form, "#TitleLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "name").get(0));
		isBookmark(form, shopId, userId);
		BookmarkImg(shopId, userId);

		ShowGradeLabel(shopId);
		ShowStar(shopId);
		comserv.ShowLabel(form, "#TitleAddressLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "address1").get(0));
		comserv.ShowLabel(form, "#TitleAddressLbl2", dbserv.selectDB(dbserv.InformationSQL(shopId), "address2").get(0));
		comserv.ShowLabel(form, "#TitleKindfoodLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "category").get(0));
		comserv.ShowLabel(form, "#TitleViewLbl", dbserv.selectDB(dbserv.InformationSQL(shopId), "view").get(0));
	}
}