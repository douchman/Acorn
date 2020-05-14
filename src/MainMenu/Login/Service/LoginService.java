package MainMenu.Login.Service;

import javafx.scene.Parent;

public interface LoginService {
	public void setTextFieldProperty(Parent root);
	public String LoginProc(Parent root);
	public void AdminLogin(Parent root);
}
