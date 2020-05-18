package Reviewpage.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

//���� �ۼ��� ���� ���� ����
public class WriterServiceImpl implements WriterService{
	MyDBService dbserv;
	CommonService comserv;
	List<ToggleButton> lstTBtn;
	
	public WriterServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	//���� �ۼ�)TextArea ���
	@Override
	public String getTA(Parent form, String fxId) {
		TextArea ta = (TextArea)form.lookup(fxId);
		return ta.getText();
	}
	//���� �ۼ�)��(����) ��ư Ŭ���� �̹��� �ٲٱ�  
	private void ToggleImg(ToggleButton tbtn) {
		if(tbtn.isSelected())
			tbtn.setEffect(new ImageInput(new Image("/Reviewpage/image/white_star2.png")));
		else
			tbtn.setEffect(new ImageInput(new Image("/Reviewpage/image/white_star1.png")));
	}
	//���� �ۼ�)�� ��ư 5�� ����
	@Override
	public Integer ToggleStar(Parent form, String number) {
		lstTBtn = new ArrayList<ToggleButton>();
		int num = Integer.parseInt(number);
		for(int i=1;i<=5;i++) 
			lstTBtn.add((ToggleButton)form.lookup("#WriteStarBtn"+i));
		
		for(int i=0;i<=num-1;i++)
			lstTBtn.get(i).setSelected(true);
		for(int i=num;i<=4;i++)
			lstTBtn.get(i).setSelected(false);
		for(int i=0;i<=4;i++)
			ToggleImg(lstTBtn.get(i));
		return num;
	}
	
	//���� �ۼ�)�� ��ư �������� ���� �ľ�
	@Override
	public int getGrade(Parent form) {
		int grade = 0;
		List<ToggleButton> lstTBtn = new ArrayList<ToggleButton>();
		for(int i=1;i<=5;i++) 
			lstTBtn.add((ToggleButton)form.lookup("#WriteStarBtn"+i));
		for(int i=0;i<5;i++)
			if(lstTBtn.get(i).isSelected())
				grade++;
		return grade;
	}
	//�̹��� ���ε� ����
	@Override
	public void ImgUploadBtnServ(Stage stage, Parent form) {
		stage = (Stage)form.getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = filechooser.showOpenDialog(stage);
		String selectedFilePath = selectedFile.getPath();
		Label ImgURLLbl = (Label)form.lookup("#WriteReviewImgURLLbl");
		ImgURLLbl.setText(selectedFilePath);
	}
	//���� �ۼ�)�ۼ� ��ư ����
	@Override
	public void submitBtnServ(Parent form, String shopId, String userId) {
		int grade = getGrade(form);
		String text = getTA(form, "#WriteContentsTA");
		String imgurl = comserv.getLabel(form, "#WriteReviewImgURLLbl");
		if(imgurl.equals("��������")) 
			imgurl = null;
		dbserv.writeReview(shopId, userId, grade, text, imgurl);
		
	}
	//���� ����)���� ��ư ����(�̿�)
	@Override
	public void EditBtnServ(Parent form, String reviewId) {
		String text = getTA(form, "#WriteContentsTA");
		String imgurl = comserv.getLabel(form, "#WriteReviewImgURLLbl");
		if(imgurl.equals("��������")) 
			imgurl = null;
		dbserv.EditReview(reviewId, text, imgurl);
	}
}