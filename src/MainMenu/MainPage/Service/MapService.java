package MainMenu.MainPage.Service;

import javafx.scene.Parent;

public interface MapService {
	public void creatpin(Parent root, String usrID);
	public void creatpin(Parent root, int m, String usrID);
	public void creatpin(Parent root, String txt, String usrID);
	public void resetpin(Parent root);
	public void clear(Parent root);
}
