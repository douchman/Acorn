package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.MyDBService;
import Reviewpage.Service.MyDBServiceImpl;
import Reviewpage.Service.ReviewListService;
import Reviewpage.Service.ReviewListServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class ReviewListController extends Controller implements Initializable{
	Parent root;
	ReviewListService rvlist;
	MyDBService dbserv;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rvlist = new ReviewListServiceImpl();
		dbserv = new MyDBServiceImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void DeleteReviewProc(ActionEvent e) {
		dbserv.DeleteReview(rvlist.getWriteday(e));
	}
}
