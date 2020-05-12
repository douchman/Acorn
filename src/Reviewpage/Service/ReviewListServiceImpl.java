package Reviewpage.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class ReviewListServiceImpl implements ReviewListService{

	public ReviewListServiceImpl(){
		
	}
	@Override
	public String getWriteday(ActionEvent e) {
		Parent form = (Parent)e.getSource();
		System.out.println(form);
		Label lbl = (Label)form.lookup("#TabReviewWriteDate");
		
		return lbl.getText();
	}
	
}
