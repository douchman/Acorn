package Reviewpage.Service;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

public class WriterReviewServiceImpl implements WriterReviewService{
	public WriterReviewServiceImpl(){
		
	}
	@Override
	public String getLabel(Parent form, String fxId) {
		Label lbl = (Label)form.lookup(fxId);
		return lbl.getText();
	}
	@Override
	public String getTA(Parent form, String fxId) {
		TextArea ta = (TextArea)form.lookup(fxId);
		return ta.getText();
	}
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
}
