package Reviewpage.Review;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.MyDBService;
import Reviewpage.Service.MyDBServiceImpl;
import Reviewpage.Service.WriterReviewService;
import Reviewpage.Service.WriterReviewServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class WriteReviewController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "1";
	private Parent root;
	CommonService comserv;
	MyDBService dbserv;
	WriterReviewService writerserv;
	StartPageController starctr;
	ToggleButton TBtn;
	List<ToggleButton> lstTBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		dbserv = new MyDBServiceImpl();
		writerserv = new WriterReviewServiceImpl();
		starctr = new StartPageController();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	private void ToggleImg(ToggleButton tbtn) {
		if(tbtn.isSelected())
			tbtn.setEffect(new ImageInput(new Image("/image/white_star2.png")));
		else
			tbtn.setEffect(new ImageInput(new Image("/image/white_star1.png")));
	}
	private Integer ToggleStar(String number) {
		lstTBtn = new ArrayList<ToggleButton>();
		int num = Integer.parseInt(number);
		for(int i=1;i<=5;i++) 
			lstTBtn.add((ToggleButton)root.lookup("#WriteStarBtn"+i));
		
		for(int i=0;i<=num-1;i++)
			lstTBtn.get(i).setSelected(true);
		for(int i=num;i<=4;i++)
			lstTBtn.get(i).setSelected(false);
		for(int i=0;i<=4;i++)
			ToggleImg(lstTBtn.get(i));
		System.out.println(lstTBtn.get(num-1).isSelected());
		return num;
				
	}
	public Integer star1(ActionEvent e) {
		return ToggleStar("1");
	}
	public Integer star2(ActionEvent e) {
		return ToggleStar("2");
	}
	public Integer star3(ActionEvent e) {
		return ToggleStar("3");
	}
	public Integer star4(ActionEvent e) {
		return ToggleStar("4");
	}
	public Integer star5(ActionEvent e) {
		return ToggleStar("5");
	}
	public void ImgUploadBtnProc(ActionEvent e) {
		Stage stage = (Stage)root.getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = filechooser.showOpenDialog(stage);
		String selectedFilePath = selectedFile.getPath();
		Label ImgURLLbl = (Label)root.lookup("#WriteReviewImgURLLbl");
		ImgURLLbl.setText(selectedFilePath);
	}
	public void CancelBtnProc(ActionEvent e) {
		comserv.CloseWindow(e);
	}
	public void submitBtnProc(ActionEvent e) {
		int grade = writerserv.getGrade(root);
		String text = writerserv.getTA(root, "#WriteContentsTA");
		String imgurl = writerserv.getLabel(root, "#WriteReviewImgURLLbl");
		if(imgurl.equals("사진없음")) 
			imgurl = null;
		dbserv.writeReview(SHOPID, USERID, grade, text, imgurl);
		comserv.CloseWindow(e);
		//starctr.GoMain(e);
	}
}
