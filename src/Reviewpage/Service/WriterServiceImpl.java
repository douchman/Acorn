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

//리뷰 작성과 리뷰 수정 서비스
public class WriterServiceImpl implements WriterService{
	MyDBService dbserv;
	CommonService comserv;
	List<ToggleButton> lstTBtn;
	
	public WriterServiceImpl(){
		dbserv = new MyDBServiceImpl();
		comserv = new CommonServiceImpl();
	}
	//리뷰 작성)TextArea 출력
	@Override
	public String getTA(Parent form, String fxId) {
		TextArea ta = (TextArea)form.lookup(fxId);
		return ta.getText();
	}
	//리뷰 작성)별(점수) 버튼 클릭시 이미지 바꾸기  
	private void ToggleImg(ToggleButton tbtn) {
		if(tbtn.isSelected())
			tbtn.setEffect(new ImageInput(new Image("/Reviewpage/image/white_star2.png")));
		else
			tbtn.setEffect(new ImageInput(new Image("/Reviewpage/image/white_star1.png")));
	}
	//리뷰 작성)별 버튼 5개 서비스
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
	
	//리뷰 작성)별 버튼 가져오고 개수 파악
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
	//이미지 업로드 서비스
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
	//리뷰 작성)작성 버튼 서비스
	@Override
	public void submitBtnServ(Parent form, String shopId, String userId) {
		int grade = getGrade(form);
		String text = getTA(form, "#WriteContentsTA");
		String imgurl = comserv.getLabel(form, "#WriteReviewImgURLLbl");
		if(imgurl.equals("사진없음")) 
			imgurl = null;
		dbserv.writeReview(shopId, userId, grade, text, imgurl);
		
	}
	//리뷰 수정)수정 버튼 서비스(미완)
	@Override
	public void EditBtnServ(Parent form, String reviewId) {
		String text = getTA(form, "#WriteContentsTA");
		String imgurl = comserv.getLabel(form, "#WriteReviewImgURLLbl");
		if(imgurl.equals("사진없음")) 
			imgurl = null;
		dbserv.EditReview(reviewId, text, imgurl);
	}
}