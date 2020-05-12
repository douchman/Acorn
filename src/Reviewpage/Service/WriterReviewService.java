package Reviewpage.Service;

import javafx.scene.Parent;

public interface WriterReviewService {
	public String getLabel(Parent form, String fxId);
	public String getTA(Parent form, String fxId);
	public int getGrade(Parent form);
}
