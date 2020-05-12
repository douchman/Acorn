package MainMenu.Login.Service;

import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public interface CommonService {
	
	public Map<String, TextField> getTextFieldInfo(Parent membershipForm, String [] txtFldIdArr);
	public boolean isEmpty(Map<String, TextField> txtFldMap, String [] txtFldIdArr);
	public void ErrorMsg(String title, String headerStr, String ContentTxt);
	public void ErrorMsg(String headerStr, String ContentTxt);
	public void ErrorMsg(String ContentTxt);
}
