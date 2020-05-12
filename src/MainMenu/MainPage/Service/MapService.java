package MainMenu.MainPage.Service;

import javafx.scene.Parent;

public interface MapService {
	public void creatpin(Parent root);
	public void creatpin(Parent root, int m);
	public void creatpin(Parent root, String txt);
	public void resetpin(Parent root);
	public void clear(Parent root);
}
